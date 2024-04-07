package model;

import java.io.Serializable;

import enums.EmployeePosition;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected String name;
	
	protected String password;
	
	protected EmployeePosition position;
	
	public Employee() {
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public EmployeePosition getPosition() {
		return this.position;
	}
	
	public Employee(String name, EmployeePosition position) {
		this.name = name;
		this.password = "password";
	}

	public Employee(String name, String password, EmployeePosition position) {
		this.name = name;
		this.password = password;
		this.position = position;
	}
}