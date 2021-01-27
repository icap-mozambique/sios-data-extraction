/**
 * 
 */
package icap.columbia.org.mz.mapper;

/**
 * @author Simone Fernando, 20/01/2021
 *
 */
public class Attribute {

	private String displayName; 

	private String attribute;

	private String valueType;

	private String value;

	public Attribute() {
	}

	/**
	 * 
	 * @return displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 
	 * @param displayName
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 
	 * @return attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * 
	 * @return valueType
	 */
	public String getValueType() {
		return valueType;
	}

	/**
	 * 
	 * @param valueType
	 */
	public void setValueType(String valueType) {
		this.valueType = valueType;
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
