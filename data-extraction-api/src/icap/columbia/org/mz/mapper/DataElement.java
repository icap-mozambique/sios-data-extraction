/**
 * 
 */
package icap.columbia.org.mz.mapper;

/**
 * Represent the DataElement returned on API
 * 
 * @author Simone Fernando, 20/01/2021
 *
 */
public class DataElement {

	// the stored ID
	private String id;

	// The formName
	private String formName;

	// The DataType of the Element
	private String valueType;

	public DataElement() {
	}

	/**
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * 
	 * @param formName
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * 
	 * @return valueType
	 */
	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

}
