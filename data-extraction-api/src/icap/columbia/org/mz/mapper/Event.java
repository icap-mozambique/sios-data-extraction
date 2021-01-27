/**
 * 
 */
package icap.columbia.org.mz.mapper;

import java.util.Date;
import java.util.List;

/**
 * @author Simone Fernando, 20/01/2021
 *
 */
public class Event {
	private String programStage;

	private String trackedEntityInstance;

	private Date eventDate;

	private List<DataValue> dataValues;

	public Event() {
	}

	/**
	 * 
	 * @return programStage
	 */
	public String getProgramStage() {
		return programStage;
	}

	/**
	 * 
	 * @param programStage
	 */
	public void setProgramStage(String programStage) {
		this.programStage = programStage;
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
	 * @return eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * 
	 * @param eventDate
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * 
	 * @return dataValues
	 */
	public List<DataValue> getDataValues() {
		return dataValues;
	}

	/**
	 * 
	 * @param dataValues
	 */
	public void setDataValues(List<DataValue> dataValues) {
		this.dataValues = dataValues;
	}

	/**
	 * 
	 * @param dataElementId
	 * @return the Value of an data element ID
	 */
	public String getDataValueById(String dataElementId) {
		if (dataValues != null) {
			for (DataValue dataValue : dataValues) {
				if (dataValue.getDataElement().equals(dataElementId))
					return dataValue.getValue();
			}
		}
		return null;
	}

}
