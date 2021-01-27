/**
 * 
 */
package icap.columbia.org.mz.mapper;

/**
 * This Class represent the value struture of an DataElement
 * 
 * @author Simone Fernando, 20/01/2021
 *
 */
public class DataValue {

	// the dataElement ID
	private String dataElement;

	// the Stored Value on Database
	private String value;

	public DataValue() {
	}

	/**
	 * 
	 * @return dataElement
	 */
	public String getDataElement() {
		return dataElement;
	}

	/**
	 * 
	 * @param dataElement
	 */
	public void setDataElement(String dataElement) {
		this.dataElement = dataElement;
	}

	/**
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
