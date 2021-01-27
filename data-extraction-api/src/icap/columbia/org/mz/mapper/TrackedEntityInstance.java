/**
 * 
 */
package icap.columbia.org.mz.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simone Fernando 19/01/2021
 *
 */
public class TrackedEntityInstance {

	private String trackedEntityInstance;

	private String orgUnit;

	private OrganizationUnity organizationUnity;

	private List<Attribute> attributes;

	private List<Enrollment> enrollments;

	public TrackedEntityInstance() {
	}

	public String getTrackedEntityInstance() {
		return trackedEntityInstance;
	}

	public void setTrackedEntityInstance(String trackedEntityInstance) {
		this.trackedEntityInstance = trackedEntityInstance;
	}

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	public OrganizationUnity getOrganizationUnity() {
		return organizationUnity;
	}

	public void setOrganizationUnity(OrganizationUnity organizationUnity) {
		this.organizationUnity = organizationUnity;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	/**
	 * 
	 * @param key
	 * @return the attribute value with the given key
	 */
	public String getAttributeValueById(String key) {
		if (attributes != null) {
			for (Attribute attribute : attributes) {
				if (key.equals(attribute.getAttribute()))
					return attribute.getValue();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param programStageId
	 * @return one single event with the given programStage Id with
	 */
	public Event getSingleEventByProgramStageIdAndTrackedEntityInstanceId(String programStageId) {
		if (enrollments != null) {
			for (Enrollment enrollment : enrollments) {
				List<Event> events = enrollment.getEvents();
				for (Event event : events) {
					if (event.getProgramStage().equals(programStageId))
						return event;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param programStageId
	 * @return one multiple events with the given programStage Id with
	 */
	public List<Event> getMultipleEventByProgramStageIdAndTrackedEntityInstanceId(String programStageId) {
		List<Event> eventsByStage = new ArrayList<Event>();
		if (enrollments != null) {
			for (Enrollment enrollment : enrollments) {
				List<Event> events = enrollment.getEvents();
				for (Event event : events) {
					if (event.getProgramStage().equals(programStageId))
						eventsByStage.add(event);
				}
			}
		}
		return eventsByStage;
	}

}
