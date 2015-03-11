/**
 * 
 */
package test.cross.plateform.service;

import java.io.Serializable;

/**
 * @author liubing1
 *
 */
public class DemoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4911668234191746603L;

	private String name;
	
	private String age;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	
}
