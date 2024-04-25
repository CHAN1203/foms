package controller;

import model.MenuItem;
import repository.Repository;



public class ManagerController extends StaffController{
	
	
	public static String getMenuIdFromName(String branch, String name) {
		//loop through menu list, get the id of the item and return "" if the item not in list
		for(MenuItem currentMenuItem : Repository.BRANCH.get(branch).getMenuItems().values()) {
			if (currentMenuItem.getName().equals(name)) {
                return currentMenuItem.getMenuItemId();
            }
		}
		return "";
	}
	
}
