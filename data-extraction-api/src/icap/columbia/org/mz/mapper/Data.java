/**
 * 
 */
package icap.columbia.org.mz.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent all Data returned to Extraction API
 * 
 * @author Simone Fernando, 21/01/2021
 * 
 *
 */
public class Data {

	// The Paging Mapper for pagination
	private Paging pager;

	// Represent all TrackedEntity with attributes and Stages with all dataElement
	// values
	private List<TrackedEntityInstance> trackedEntityInstances;

	// List of all requested organisation unity
	private List<OrganizationUnity> organisationUnits;

	public Data() {
		trackedEntityInstances = new ArrayList<TrackedEntityInstance>();
	}

	/**
	 * 
	 * @return pager
	 */
	public Paging getPager() {
		return pager;
	}

	/**
	 * 
	 * @param pager
	 */
	public void setPager(Paging pager) {
		this.pager = pager;
	}

	/**
	 * 
	 * @return trackedEntityInstances
	 */
	public List<TrackedEntityInstance> getTrackedEntityInstances() {
		return trackedEntityInstances;
	}

	/**
	 * 
	 * @param trackedEntityInstances
	 */
	public void setTrackedEntityInstances(List<TrackedEntityInstance> trackedEntityInstances) {
		this.trackedEntityInstances = trackedEntityInstances;
	}

	/**
	 * 
	 * @return
	 */
	public List<OrganizationUnity> getOrganisationUnits() {
		return organisationUnits;
	}

	/**
	 * 
	 * @param organisationUnits
	 */
	public void setOrganisationUnits(List<OrganizationUnity> organisationUnits) {
		this.organisationUnits = organisationUnits;
	}

}
