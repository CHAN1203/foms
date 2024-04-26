package model;

import enums. *;

import java.io.Serializable;

/**
 * Employee represents the employee in the fast food chain. <p>
 * 
 * Each employee contains name, password for login, age, branch, position in fast food chain, gender, and login id for login.
 * @author Sher Min, Yue Hang
 * @version 2.0
 * @since 2024-04-05
 */
public class Employee implements Serializable {
	/**
	 * For Java Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name of employee
	 */
	protected String name;
	
	/**
	 * Password of employee for login
	 */
	protected String password;
	
	/**
	 * Age of employee
	 */
	private int age;
	
	/**
	 * Branch of employee
	 */
	private String branch;
	
	/**
	 * {@link EmployeePosition} Position of employee in branch
	 */
	protected EmployeePosition position;
	
	/**
	 * {@link EmployeeGender} Gender of employee
	 */
	private EmployeeGender gender;
	
	/**
	 * Login id of employee for login
	 */
	private String loginId;
	
	/**
	 * Default constructor of employee class
	 */
	public Employee() {
	}
	
	/**
	 * Sets the name of employee
	 * @param name Name of employee
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the password of employee for login
	 * @param password Password of employee for login
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the age of employee 
	 * @param age Age of employee
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Sets the branch of employee
	 * @param branch Branch of employee
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * Sets the position of employee in branch
	 * @param position {@link EmployeePosition} Position of employee in branch
	 */
	public void setPosition(EmployeePosition position) {
		this.position = position;
	}

	/**
	 * Sets the gender of employee
	 * @param gender {@link EmployeeGender} Gender of employee
	 */
	public void setGender(EmployeeGender gender) {
		this.gender = gender;
	}
	
	/**
	 * Sets the login id of employee for login
	 * @param loginId Login id of employee for login
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	/**
	 * Gets the name of employee
	 * @return Name of employee
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the age of employee
	 * @return Age of employee
	 */
	public int getAge() {
		return this.age;
	}
	
	/**
	 * Gets the password of employee for login
	 * @return Password of employee for login
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Gets the position of employee in branch
	 * @return {@link EmployeePosition} Position of employee in branch
	 */
	public EmployeePosition getPosition() {
		return this.position;
	}
	
	/**
	 * Gets the branch of employee
	 * @return Branch of employee
	 */
	public String getBranch() {
		return this.branch;
	}
	
	/**
	 * Gets the gender of employee
	 * @return {@link EmployeeGender} Gender of employee
	 */
	public EmployeeGender getGender() {
		return this.gender;
	}
	
	/**
	 * Gets the login id of employee for login
	 * @return Login id of employee for login
	 */
	public String getLoginId() {
		return this.loginId;
	}
	
	/**
	 * Constructs and initializes the employee's name, password for login, branch, position in branch, gender, age, and login id for login.
	 * 
	 * @param name Name of employee
	 * @param password Password of employee for login
	 * @param branch Branch of employee
	 * @param position {@link EmployeePosition} Position of employee in branch
	 * @param gender {@link EmployeeGender} Gender of employee
	 * @param age Age of employee
	 * @param loginId Login id of employee for login
	 */
	public Employee(String name, String password, String branch, EmployeePosition position, EmployeeGender gender, int age, String loginId) {
		this.name = name;
		this.password = password;
		this.age = age;
		this.branch = branch;
		this.position = position;
		this.gender = gender;
		this.loginId = loginId;
	}
	
}