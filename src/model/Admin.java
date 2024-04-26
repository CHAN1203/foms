package model;

import java.io.Serializable;

import enums.EmployeeGender;
import enums.EmployeePosition;

/**
 * Admin represents admin of the fast food chain. <p>
 * 
 * Admin contains admin's name, age, gender, login id, password, and position in the fast food chain.
 * @author Kee Qing
 * @version 1.0
 * @since 2024-04-01
 */
public class Admin implements Serializable{
	/**
     * For Java Serializable
     */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name of admin
	 */
	protected String name;
	
	/**
	 * Password of admin for login
	 */
	protected String password;
	
	/**
	 * Age of admin
	 */
	private int age;
	
	/**
	 * {@link EmployeePosition} Position of admin in fast food chain
	 */
	protected EmployeePosition position;
	
	/**
	 * {@link EmployeeGender} Gender of admin
	 */
	private EmployeeGender gender;
	
	/**
	 * Login id of admin for login
	 */
	private String loginId;
	
	/**
	 * Set the name of admin
	 * 
	 * @param name Name of admin
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the password of admin for login
	 * 
	 * @param password Password of admin for login
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Set the age of admin
	 * 
	 * @param age Age of admin
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Set the position of admin in fast food chain
	 * 
	 * @param position {@link EmployeePosition} Position of admin in fast food chain
	 */
	public void setPosition(EmployeePosition position) {
		this.position = position;
	}

	/**
	 * Set the gender of admin
	 * 
	 * @param gender {@link EmployeeGender} Gender of admin
	 */
	public void setGender(EmployeeGender gender) {
		this.gender = gender;
	}
	
	/**
	 * Set the login id of admin
	 * @param loginId Login id of admin for login
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	/**
	 * Gets the name of admin
	 * 
	 * @return Name of admin
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the age of admin
	 * 
	 * @return Age of admin
	 */
	public int getAge() {
		return this.age;
	}
	
	/**
	 * Gets the password of admin for login
	 * 
	 * @return Password of admin for login
	 */
	public String getPassword() {
		return this.password;
	}
	
	
	/**
	 * Gets the position of admin in fast food chain
	 * 
	 * @return {@link EmployeePosition} Position of admin in fast food chain
	 */
	public EmployeePosition getPosition() {
		return this.position;
	}
	
	/**
	 * Gets the gender of admin 
	 * 
	 * @return {@link EmployeeGender} Gender of admin
	 */
	public EmployeeGender getGender() {
		return this.gender;
	}
	
	/**
	 * Gets the login id of admin for login
	 * 
	 * @return Login id of admin for login
	 */
	public String getLoginId() {
		return this.loginId;
	}

	/**
	 * Constructs and initializes name, password, position, gender, age, and loginId respectively.
	 * 
	 * @param name Name of admin
	 * @param password Password of admin for login
	 * @param position Position of admin in fast food chain
	 * @param gender Gender of admin
	 * @param age Age of admin
	 * @param loginId Login id of admin for login
	 */
	public Admin(String name, String password, EmployeePosition position, EmployeeGender gender, int age, String loginId) {
		this.name = name;
		this.password = password;
		this.age = age;
		this.position = position;
		this.gender = gender;
		this.loginId = loginId;
	}
}
