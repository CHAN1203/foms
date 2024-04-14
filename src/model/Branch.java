package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Branch implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String location;
	
	private int staffQuota;
		
	//somewhere need to put branch into <branch, employee> hash map
	private List<String> foodCategoryList;
	private HashMap<String, Employee> EMPLOYEE = new HashMap<>();
	private HashMap<String, MenuItem> MENU_ITEMS = new HashMap<>();
	private HashMap<String, Order> ORDERS = new HashMap<>();  // <Order ID, Order object>
	
	public Branch(String name, String location, int staffQuota) {
		this.name = name;
		this.location = location;
		this.staffQuota = staffQuota;
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
	
	public String getName() {
		return this.name;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public int getstaffQuota() {
		return this.staffQuota;
	}
	
	public HashMap<String, Employee> getEmployee() {
		return this.EMPLOYEE;
	}
	
	public HashMap<String, MenuItem> getMenuItems() {
		return this.MENU_ITEMS;
	}

	public HashMap<String, Order> getOrders() {
		return this.ORDERS;
	}
	

	public List<String> getFoodCategoryList() {
		return foodCategoryList;
	}
}

//FOMS APP NTU JE JP HASHMAP<BRANCH>