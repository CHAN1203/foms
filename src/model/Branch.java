package model;

import java.util.HashMap;

public class Branch {
	
	private String name;
	
	private String location;
	
	private int staffQuota;
	
	private HashMap<String, Employee> EMPLOYEE = new HashMap<String, Employee>();
	private HashMap<String, MenuItems> MENU_ITEMS = new HashMap<String, MenuItems>();
	private HashMap<String, Orders> ORDERS = new HashMap<String, Orders>();
	
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