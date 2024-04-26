package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Branch represents the branch of fast food chain. <p>
 * 
 * Each branch contains it's name, location, staff quota, manager quota, number of employees in total, number of staff, and number of manager.
 * Branch also contains lists of food category and menu items in the customisable menu, list of orders, and list of employees.
 * @author Jacky, Hong Sheng, Yue Hang
 * @version 3.0
 * @since 2024-04-01
 */
public class Branch implements Serializable{
	/**
	 * For Java Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name of branch
	 */
	private String name;
	
	/**
	 * Location of branch
	 */
	private String location;
	
	/**
	 * Staff quota of branch
	 */
	private int staffQuota;
	
	/**
	 * Manager quota of branch
	 */
	private int managerQuota;
	
	/**
	 * Total number of employees (both staff and manager) in branch
	 */
	private int numberOfEmployee;
	
	/**
	 * Number of staff in branch
	 */
	private int numberOfStaff;
	
	/**
	 * Number of manager in branch
	 */
	private int numberOfManager;
	
	/**
	 * List of food category of branch's menu
	 */
	private List<String> foodCategoryList = new ArrayList<String>();
	
	/**
     * HashMap to contain {@link Employee} objects.
     */
	private HashMap<String, Employee> EMPLOYEE = new HashMap<>();
	
	/**
     * HashMap to contain {@link MenuItem} objects.
     */
	private HashMap<String, MenuItem> MENU_ITEMS = new HashMap<>();
	
	/**
     * HashMap to contain {@link Orders} objects.
     */
	private HashMap<String, Order> ORDERS = new HashMap<>();  
	
	/**
	 * Constructs and initilizes the name, location, staff quota, number of staff, number of manager, number of employee, and manager quota.
	 * 
	 * @param name Name of branch
	 * @param location Location of branch
	 * @param staffQuota Staff quota of branch
	 * @param numberOfStaff Total number of staff (both staff and manager) in branch
	 * @param numberOfManager Number of manager in branch
	 * @param numberOfEmployee Number of employee in branch
	 * @param managerQuota Manager quota of branch
	 */
	public Branch(String name, String location, int staffQuota, int numberOfStaff, int numberOfManager, int numberOfEmployee, int managerQuota) {
		this.name = name;
		this.location = location;
		this.staffQuota = staffQuota;
		this.numberOfStaff = numberOfStaff;
		this.numberOfManager = 0;
		this.numberOfEmployee = 0;
		this.managerQuota = 0;
	}
	
	/**
	 * Reset manager quota of branch whenever any changes made to number of staff
	 */
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
	
	/**
	 * Sets the name of branch 
	 * 
	 * @param name Name of branch
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the location of branch
	 * 
	 * @param location Location of branch
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Sets the staff quota of branch
	 * 
	 * @param quota Staff quota of branch
	 */
	public void setStaffQuota(int quota) {
		this.staffQuota = quota;
	}
	
	/**
	 * Deducts the number of staff by 1 and resets the manager quota of branch through {@link resetManagerQuota()} method
	 */
	public void deductNumberOfStaff() {
		this.numberOfStaff--;
		resetManagerQuota();
	}
	
	/**
	 * Deducts the number of manager by 1 and resets the manager quota of branch through {@link resetManagerQuota()} method
	 */
	public void deductNumberOfManager() {
		this.numberOfManager--;
		resetManagerQuota();
	}
	
	/**
	 * Deducts the number of employee by 1
	 */
	public void deductNumberOfEmployee() {
		this.numberOfEmployee--;
	}
	
	/**
	 * Adds the number of staff by 1 and resets the manager quota of branch through {@link resetManagerQuota()} method
	 */
	public void addNumberOfStaff() {
		this.numberOfStaff++;
		resetManagerQuota();
	}
	
	/**
	 * Adds the number of manager by 1 and resets the manager quota of branch through {@link resetManagerQuota()} method
	 */
	public void addNumberOfManager() {
		this.numberOfManager++;
		resetManagerQuota();
	}
	
	/**
	 * Adds the number of employee by 1
	 */
	public void addNumberOfEmployee() {
		this.numberOfEmployee++;
	}
	
	/**
	 * Gets the name of branch
	 * 
	 * @return Name of branch
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the location of branch
	 * @return Location of branch
	 */
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * Gets the staff quota of branch
	 * 
	 * @return Staff quota of branch
	 */
	public int getstaffQuota() {
		return this.staffQuota;
	}
	
	/**
	 * Gets the number of staff in branch
	 * 
	 * @return Number of staff in branch
	 */
	public int getNumberOfStaff() {
		return this.numberOfStaff;
	}
	
	/**
	 * Gets the number of manager in branch
	 * 
	 * @return Number of manager in branch
	 */
	public int getNumberOfManager() {
		return this.numberOfManager;
	}
	
	/**
	 * Gets the number of employee in branch
	 * 
	 * @return Number of employee in branch
	 */
	public int getNumberOfEmployee() {
		return this.numberOfEmployee;
	}
	
	/**
	 * Gets the manager quota of branch
	 * 
	 * @return Manager quota of branch
	 */
	public int getManagerQuota() {
		return this.managerQuota;
	}
	
	/**
	 * Gets the hash map of employee of branch
	 * 
	 * @return HashMap to contain {@link Employee} objects
	 */
	public HashMap<String, Employee> getEmployee() {
		return this.EMPLOYEE;
	}
	
	/**
	 * Gets the hash map of menu items of branch
	 * 
	 * @return HashMap to contain {@link MenuItem} objects.
	 */
	public HashMap<String, MenuItem> getMenuItems() {
		return this.MENU_ITEMS;
	}
	
	/**
	 * Gets the hash map of orders of branch
	 * 
	 * @return HashMap to contain {@link Orders} objects.
	 */
	public HashMap<String, Order> getOrders() {
		return this.ORDERS;
	}
	
	/**
	 * Gets the list of food category of branch's menu
	 * 
	 * @return List of food category of branch's menu
	 */
	public List<String> getFoodCategoryList() {
		return foodCategoryList;
	}
	
}