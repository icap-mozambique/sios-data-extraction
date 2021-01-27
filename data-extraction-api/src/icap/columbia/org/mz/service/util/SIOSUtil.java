/**
 * 
 */
package icap.columbia.org.mz.service.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import icap.columbia.org.mz.mapper.Data;
import icap.columbia.org.mz.mapper.DataElement;
import icap.columbia.org.mz.mapper.OrganizationUnity;
import icap.columbia.org.mz.mapper.Paging;
import icap.columbia.org.mz.service.SiosService;

/**
 * this utility class is used to call a predefine services
 * 
 * @author Simone Fernando, 21/01/2021
 *
 */
public class SIOSUtil {

	static SiosService siosService = null;

	public static void authenticate(String username, String password) {
		siosService = new SiosService();
		try {
			siosService.register(username, password);
		} catch (UnsupportedEncodingException e) {
			siosService = null;
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id - Id of Organization Unity
	 * @return OrganizationUnity Object {id:displayName:displayFormName} with the
	 *         given identifier
	 */
	public static OrganizationUnity getOrganisationUnityById(String id) {
		if (siosService != null) {
			siosService.build(SiosService.SIOS_ORGANIZATION_UNITY_PATH.concat(id).concat(".json"));
			OrganizationUnity organizationUnity;
			try {
				organizationUnity = (OrganizationUnity) siosService.invoke(OrganizationUnity.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
			return organizationUnity;
		} else
			return null;
	}

	/**
	 * 
	 * @param level - level of Organization Unity
	 * @return OrganizationUnity Object {id:displayName:displayFormName} with the
	 *         given level
	 */
	public static List<OrganizationUnity> getOrganisationUnityByLevel(int level) {
		List<OrganizationUnity> organizationUnities = new ArrayList<OrganizationUnity>();
		if (siosService != null) {
			String uri = StringUtils.chop(SiosService.SIOS_ORGANIZATION_UNITY_PATH).concat(".json")
					.concat("?paging=false&level=" + level);
			try {
				siosService.build(uri);
				Data data = (Data) siosService.invoke(Data.class);

				if (data.getOrganisationUnits() != null)
					organizationUnities.addAll(data.getOrganisationUnits());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}

		}
		return organizationUnities;
	}

	/**
	 * @param organisationUnityId - the organisation unity id
	 * @param level               - level of organisation unit
	 * @return list all organisation unity children whith the level passed as
	 *         parameter and parent organisation unity id - if you pass (Privince as
	 *         Organisation unity ID and LEVEL=2 the result is a List of Health
	 *         facility) - if you pass (Privince as Organisation unity ID and
	 *         LEVEL=1 the result is a List of District) - if you pass (Privince as
	 *         Organisation unity ID and LEVEL=0 the result is a Province)
	 */
	public static List<OrganizationUnity> getOrganisationUnityByIdAndLevel(String organisationUnityId, int level) {
		List<OrganizationUnity> organizationUnities = new ArrayList<OrganizationUnity>();
		if (siosService != null) {
			String uri = SiosService.SIOS_ORGANIZATION_UNITY_PATH.concat(organisationUnityId).concat(".json")
					.concat("?paging=false&level=" + level);
			try {
				siosService.build(uri);
				Data data = (Data) siosService.invoke(Data.class);

				if (data.getOrganisationUnits() != null)
					organizationUnities.addAll(data.getOrganisationUnits());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}

		}
		return organizationUnities;
	}

	/**
	 * 
	 * @param id - Id of DataElement
	 * @return DataElement Object {id:formName:valueType}
	 */
	public static DataElement getDataElementById(String id) {
		if (siosService != null) {
			siosService.build(SiosService.SIOS_DATA_ELEMENT_BY_ID_PATH.concat(id).concat(".json"));
			DataElement dataElement;
			try {
				dataElement = (DataElement) siosService.invoke(DataElement.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
			return dataElement;
		} else
			return null;
	}

	/**
	 * 
	 * @param organizationUnityId
	 * @param programId
	 * @param startDate           (format is yyyy-MM-dd)
	 * @param endDate             (format is yyyy-MM-dd)
	 * @param ignoreFilters       (TRUE|FALSE)
	 * @return Data <Mapped Class>
	 */
	public static Data getSIOSData(String organizationUnityIds[], String programId,
			String programStartDate, String programEndDate, String filters) {

		StringBuilder uri = new StringBuilder(SiosService.SIOS_TRACKED_ENTITY_PATH
				.concat("?totalPages=true&ouMode=DESCENDANTS&" + Constants.PARAMETER_TRACKED_ENTITY_FIELDS));

		if (organizationUnityIds != null) {
			uri.append("&ou=");
			for (int i = 0; i < organizationUnityIds.length; i++) {
				if (i == 0)
					uri.append(organizationUnityIds[i]);
				else
					uri.append(";" + organizationUnityIds[i]);
			}
		}

		if (programId != null) {
			uri.append("&program=" + programId);
		}

		if (programStartDate != null) {
			uri.append("&programStartDate=" + programStartDate);
		}

		if (programEndDate != null) {
			uri.append("&programEndDate=" + programEndDate);
		}

		if (filters != null) {
			uri.append("&filter=" + filters);
		}

		try {
			Data data = new Data();

			if (siosService != null) {
				siosService.build(uri.toString() + "&pageSize=50&page=1");
				Data request = (Data) siosService.invoke(Data.class);

				if (request.getTrackedEntityInstances() != null)
					data.getTrackedEntityInstances().addAll(request.getTrackedEntityInstances());

				int page = 2;
				long pageCount = request.getPager().getPageCount();
				long total = request.getPager().getPageCount();
				data.setPager(new Paging(pageCount, total, 50));

				while (page <= pageCount) {
					siosService.build(uri.toString() + "&pageSize=50&page=" + page);
					request = (Data) siosService.invoke(Data.class);
					if (request.getTrackedEntityInstances() != null)
						data.getTrackedEntityInstances().addAll(request.getTrackedEntityInstances());
					page++;
				}
			}
			return data;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
