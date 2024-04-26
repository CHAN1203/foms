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

	private List<String> foodCategoryList = new ArrayList<String>();
	//branch-employee hash map
	private HashMap<String, Employee> EMPLOYEE = new HashMap<>();
	private HashMap<String, MenuItem> MENU_ITEMS = new HashMap<>();
	private HashMap<String, Order> ORDERS = new HashMap<>();  // <Order ID, Order object>
	
	public Branch(String name, String location, int staffQuota, int numberOfStaff, int numberOfManager, int numberOfEmployee, int managerQuota) {
		this.name = name;
		this.location = location;
		this.staffQuota = staffQuota;
		this.numberOfStaff = numberOfStaff;
		this.numberOfManager = 0;
		this.numberOfEmployee = 0;
		this.managerQuota = 0;
	}
	
	
	protected void resetManagerQuota() {
		if (numberOfStaff >= 1 && numberOfStaff <= 4) {
			this.managerQuota = 1;
		}
		else if (numberOfStaff >= 5 && numberOfStaff <= 8) {
			this.managerQuota = 2;
		}
		else if (numberOfStaff >= 9 && numberOfStaff <= 15) {
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
		resetManagerQuota();
	}
	
	public void deductNumberOfManager() {
		this.numberOfManager--;
		resetManagerQuota();
	}
	
	public void deductNumberOfEmployee() {
		this.numberOfEmployee--;
	}
	
	public void addNumberOfStaff() {
		this.numberOfStaff++;
		resetManagerQuota();
	}
	
	public void addNumberOfManager() {
		this.numberOfManager++;
		resetManagerQuota();
	}
	
	public void addNumberOfEmployee() {
		this.numberOfEmployee++;
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
	
	public int getNumberOfStaff() {
		return this.numberOfStaff;
	}
	
	public int getNumberOfManager() {
		return this.numberOfManager;
	}
	
	public int getNumberOfEmployee() {
		return this.numberOfEmployee;
	}
	
	public int getManagerQuota() {
		return this.managerQuota;
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
