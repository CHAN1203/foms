package model;

import java.io.Serializable;
import java.util.HashMap;

public class Branch implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String location;
	
	private int staffQuota;
	
	//somewhere need to put branch into <branch, employee> hash map
	private HashMap<String, Employee> EMPLOYEE = new HashMap<String, Employee>();
	private HashMap<String, MenuItems> MENU_ITEMS = new HashMap<String, MenuItems>();
	private HashMap<String, Orders> ORDERS = new HashMap<String, Orders>();
	
	
	public String getName() {
		return this.name;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public int getstaffQuota() {
		return this.staffQuota;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setStaffQuota(int quota) {
		this.staffQuota = quota;
	}
	
	
	
	public Branch(String name, String location, int staffQuota) {
		this.name = name;
		this.location = location;
		this.staffQuota = staffQuota;
	}
	
	public HashMap<String, Employee> getEmployee() {
		return this.EMPLOYEE;
	}
	
}

//FOMS APP NTU JE JP HASHMAP<BRANCH>