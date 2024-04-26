package controller;

import java.util.ArrayList;
import java.util.Map;

import model.Employee;
import model.MenuItem;
import repository.Repository;


/**
 * @author Sher Min
 * @version 1.0
 * @since 2024-4-4
 */
public class ManagerController extends StaffController{
	
	/**
	 * Get the menu item's ID from name of item
	 * @param branch Name of Branch
	 * @param name Name of menu item
	 * @return The menu ID of item
	 */
	public static String getMenuIdFromName(String branch, String name) {
		//loop through menu list, get the id of the item and return "" if the item not in list
		for(MenuItem currentMenuItem : Repository.BRANCH.get(branch).getMenuItems().values()) {
			if (currentMenuItem.getName().equals(name)) {
                return currentMenuItem.getMenuItemId();
            }
		}
		return "";
	}
	
	/**
	 * Display all the staffs in the branch
	 * @param branch Name of Branch
	 * @return {@code true} if there exist staff in the branch, {@code false} otherwise
	 */
	public static boolean displayStaffList(String branch) {
    	ArrayList<Employee> staffNameList = new ArrayList<Employee>();
    	//can't just iterate through map, need to do modification to loop through, need to import packages for map.entry
        for (Map.Entry<String, Employee> entry : Repository.BRANCH.get(branch).getEmployee().entrySet()) {
        	Employee employee = entry.getValue();
            staffNameList.add(employee);
        }
        if(staffNameList.size() != 0) {
        	int i = 1;
        	for(Employee employee : staffNameList) {
//            	System.out.println("(" + (i++) + ") " + employee.getName() + "    Employee Position: " + employee.getPosition());
            	System.out.printf("%-4d %-26s %-10s \n", i++, employee.getName(), employee.getPosition());
            }
        	return true;
        }
        return false;
	}
	
}
