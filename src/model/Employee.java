package model;

import enums. *;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected String name;
	
	protected String password;
	
	private String branch;
	
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
	
	public String getBranch() {
		return this.branch;
	}
	
	public Employee(String name, EmployeePosition position) {
		this.name = name;
		this.password = "password";
	}

	public Employee(String name, String password, String branch, EmployeePosition position) {
		this.name = name;
		this.password = password;
		this.position = position;
	}
}
