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
	
	private int managerQuota;
	
	private int numberOfEmployee;
	
	private int numberOfStaff;
	
	private int numberOfManager;
	//somewhere need to put branch into <branch, employee> hash map
	private List<String> foodCategoryList = new ArrayList<String>();
	//branch-employee hash map
	private HashMap<String, Employee> EMPLOYEE = new HashMap<>();
	private HashMap<String, MenuItem> MENU_ITEMS = new HashMap<>();
	private HashMap<String, Order> ORDERS = new HashMap<>();  // <Order ID, Order object>
	
	public Branch(String name, String location, int staffQuota, int numberOfStaff) {
		this.name = name;
		this.location = location;
		this.staffQuota = staffQuota;
		setManagerQuota(this.staffQuota);
		this.numberOfEmployee = 0;
		this.numberOfStaff = 0;
		this.numberOfManager = 0;
	}
	
	protected void setManagerQuota(int staffQuota) {
		if (staffQuota >= 1 && staffQuota <= 4) {
			this.managerQuota = 1;
		}
		else if (staffQuota >= 5 && staffQuota <= 8) {
			this.managerQuota = 2;
		}
		else if (staffQuota >= 9 && staffQuota <= 15) {
			this.managerQuota = 3;
		}
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
	
	public void deductNumberOfStaff() {
		this.numberOfStaff--;
		setManagerQuota(this.staffQuota);
	}
	
	public void addNumberOfStaff() {
		this.numberOfStaff++;
		setManagerQuota(this.staffQuota);
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
	
	public int getNumberOffStaff() {
		return this.numberOfStaff;
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