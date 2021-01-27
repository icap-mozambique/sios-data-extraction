/**
 * 
 */
package icap.columbia.org.mz.mapper;

import java.util.Date;
import java.util.List;

/**
 * The Class represent the TrackedEntity enrollments in an program, can be ONE
 * or MORE
 * 
 * @author Simone Fernando, 20/01/2021
 *
 */
public class Enrollment {

	// the tracked entity ID
	private String trackedEntityInstance;

	// the enrollment ID
	private String enrollment;

	// the Stored program ID
	private String program;

	// the enrollment Organisation Unity ID
	private String orgUnit;

	// the enrollment Organisation Unity NAME
	private String orgUnitName;

	// the enrollment date
	private Date enrollmentDate;

	// List all stored Stages with the dataelement values
	private List<Event> events;

	public Enrollment() {
	}

	/**
	 * 
	 * @return trackedEntityInstance
	 */
	public String getTrackedEntityInstance() {
		return trackedEntityInstance;
	}

	/**
	 * 
	 * @param trackedEntityInstance
	 */
	public void setTrackedEntityInstance(String trackedEntityInstance) {
		this.trackedEntityInstance = trackedEntityInstance;
	}

	/**
	 * 
	 * @return enrollment
	 */
	public String getEnrollment() {
		return enrollment;
	}

	/**
	 * 
	 * @param enrollment
	 */
	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	/**
	 * 
	 * @return program
	 */
	public String getProgram() {
		return program;
	}

	/**
	 * 
	 * @param program
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	/**
	 * 
	 * @return orgUnit
	 */
	public String getOrgUnit() {
		return orgUnit;
	}

	/**
	 * 
	 * @param orgUnit
	 */
	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	/**
	 * 
	 * @return orgUnitName
	 */
	public String getOrgUnitName() {
		return orgUnitName;
	}

	/**
	 * 
	 * @param orgUnitName
	 */
	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	/**
	 * 
	 * @return enrollmentDate
	 */
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	/**
	 * 
	 * @param enrollmentDate
	 */
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	/**
	 * 
	 * @return events
	 */
	public List<Event> getEvents() {
		return events;
	}

	/**
	 * 
	 * @param events
	 */
	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
