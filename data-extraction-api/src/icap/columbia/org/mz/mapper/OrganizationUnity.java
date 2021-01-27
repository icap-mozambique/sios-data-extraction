package icap.columbia.org.mz.mapper;

/**
 * The class represents the organisation unity struture
 * 
 * @author Simone Fernando, 20/01/2021
 *
 */
public class OrganizationUnity {

	private String id;
	private String displayName;
	private String displayFormName;

	public OrganizationUnity() {
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
	 * @return displayFormName
	 */
	public String getDisplayFormName() {
		return displayFormName;
	}

	/**
	 * 
	 * @param displayFormName
	 */
	public void setDisplayFormName(String displayFormName) {
		this.displayFormName = displayFormName;
	}

}
