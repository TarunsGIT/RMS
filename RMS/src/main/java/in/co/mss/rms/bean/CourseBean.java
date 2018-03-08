package in.co.mss.rms.bean;

/**
 * Course JavaBean encapsulates Course attributes
 * 
 * @author Session Facade
 * @version 1.0
 * 
 * 
 */

public class CourseBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * Course Name
	 */
	private String name;

	/**
	 * Course Description
	 */
	private String description;

	/**
	 * Course Duration
	 */
	private Integer duration;

	/**
	 * accessor
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return name;
	}

}
