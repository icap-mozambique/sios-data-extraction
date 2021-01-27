/**
 * 
 */
package icap.columbia.org.mz.teste;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.client.ClientProtocolException;

import icap.columbia.org.mz.mapper.Data;
import icap.columbia.org.mz.service.util.Constants;
import icap.columbia.org.mz.service.util.PoiXLSUtil;
import icap.columbia.org.mz.service.util.SIOSUtil;

/**
 * @author Simone Fernando
 *
 */
public class Teste {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		SIOSUtil.authenticate("sfc2124@cumc.columbia.edu", "Covid@2019");

		System.out
				.println(SIOSUtil.getOrganisationUnityByLevel(Constants.SIOS_ORGANIZATION_LEVEL_ID_UNIDADE_SANITARIA));

//		Data data = SIOSUtil
//				.getSIOSData(
//						new String[] { Constants.ID_OU_MAPUTO_CIDADE, Constants.ID_OU_CABO_DELGADO,
//								Constants.ID_OU_GAZA, Constants.ID_OU_INHAMBANE, Constants.ID_OU_MANICA,
//								Constants.ID_OU_MAPUTO_PROVINCIA, Constants.ID_OU_NAMPULA, Constants.ID_OU_NIASSA,
//								Constants.ID_OU_SOFALA, Constants.ID_OU_TETE, Constants.ID_OU_ZAMBEZIA },
//						Constants.ID_PROGRAM_FICHA_INTERNAMENTO_COVID_19, null, null, null);
//
//		PoiXLSUtil poiXLSUtil = new PoiXLSUtil();
//
//		byte[] exported = poiXLSUtil.exportXLS(data, null);
//
//		poiXLSUtil.writeToFile(exported, "C:\\Users\\Intuser\\sios_export.xls");

	}

}
