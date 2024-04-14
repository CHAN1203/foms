package controller;
import helper.Helper;
import model.MenuItem;
import repository.Repository;
import repository.FileType;
import enums. *;


public class ManagerController extends StaffController{
	
	
	public static String getMenuIdFromName(String name) {
		//loop through menu list, get the id of the item and return "" if the item not in list
		for(MenuItem currentMenuItem : Repository.MENU_ITEMS.values()) {
			if (currentMenuItem.getName().equals(name)) {
                return currentMenuItem.getMenuItemId();
            }
		}
		return "";
	}
	
	public static boolean addMenuItem(String name, String description, double price, FoodCategory foodCategory) {
		String formattedName = name.toUpperCase();
		String menuIdToUpdate = getMenuIdFromName(name);
		if(!menuIdToUpdate.equals("")) {
			// means theres a duplicate 
            return false;
		}
		int mid = Helper.generateUniqueId(Repository.MENU_ITEMS);
        String menuItemId = String.format("M%04d", mid);
        MenuItem newMenuItem = new MenuItem(menuItemId, formattedName, description, price, foodCategory);//how to put enum argument 
        Repository.MENU_ITEMS.put(menuItemId, newMenuItem);
        Repository.persistData(FileType.MENU_ITEMS);
        return true;
	}
	
	public static boolean updateMenuItem(String name, String description, double price, FoodCategory foodCategory){
        String formattedName = name.toUpperCase();
        String menuIdToUpdate = getMenuIdFromName(formattedName);
        if (menuIdToUpdate.equals("")) {
            return false;
        }
        MenuItem menuItemToUpdate = Repository.MENU_ITEMS.get(menuIdToUpdate);
        menuItemToUpdate.setName(formattedName);
        menuItemToUpdate.setDescription(description);
        menuItemToUpdate.setPrice(price);
        menuItemToUpdate.setMenuCategory(foodCategory); //how to accept enum argument
        //!!
        Repository.persistData(FileType.MENU_ITEMS);
        return true;
    }
	
	/**
     * Removes menu item from the menu
     * 
     * @param name Name of the menu item to be removed
     * @return {@code true} if menu item is removed successfully. Otherwise, {@code false} if menu item fails to be removed (menu item not found in database)
     */
	public static boolean removeMenuItem(String name) {
        String formattedName = name.toUpperCase();
        String menuIdToDelete = getMenuIdFromName(formattedName);
        if (menuIdToDelete.equals("")) {
            // not found
            return false;
        }
        Repository.MENU_ITEMS.remove(menuIdToDelete);
        Repository.persistData(FileType.MENU_ITEMS);
        return true;
    }
	/**
     * Initializer for dummy menu items in the hotel. 
     */
}
