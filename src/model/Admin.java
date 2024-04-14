package model;

import enums.EmployeeGender;
import enums.EmployeePosition;

public class Admin {
	private static final long serialVersionUID = 1L;
	
	protected String name;
	
	protected String password;
	
	private int age;
	
	protected EmployeePosition position;
	
	private EmployeeGender gender;
	
	private String loginId;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setPosition(EmployeePosition position) {
		this.position = position;
	}

	public void setGender(EmployeeGender gender) {
		this.gender = gender;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public EmployeePosition getPosition() {
		return this.position;
	}
	
	public EmployeeGender getGender() {
		return this.gender;
	}
	
	public String getLoginId() {
		return this.loginId;
	}

	public Admin(String name, String password, EmployeePosition position, EmployeeGender gender, int age, String loginId) {
		this.name = name;
		this.password = password;
		this.age = age;
		this.position = position;
		this.gender = gender;
		this.loginId = loginId;
	}
}
