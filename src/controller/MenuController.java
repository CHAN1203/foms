package controller;

import repository.Repository;
import repository.FileType;
import model.MenuItem;
import java.util.HashMap;
import enums.FoodAvailability;
import helper.Helper;

// for javadocs
/**
 * MenuManager is a controller class that acts as a "middleman"
 * between the view class - {@link MenuView} and the model classes - {@link MenuItem}. <p>
 *
 * It can customize the menu. <p>
 * @author Hill Seah
 * @version 1.0
 * @since 2022-03-31
 *
 */
public class MenuController {
    /**
     * Default constructor for Menu Manager
     */
    public MenuController() {
        
    }
    /**
     * Adds a new menu item, with details of its name, preparation description and price to the menu
     * 
     * @param name Name of the menu item to be added
     * @param description Description of the menu item to be added
     * @param price Price of the menu item to be added
     * @return {@code true} if menu item is added successfully. Otherwise, {@code false} if menu item fails to be added (Duplicated menu item)
     */
    public static boolean addMenuItem(String branch,String name, String foodCategory, String description, double price, FoodAvailability foodAvailability) {
        String formattedName = name.toUpperCase();
        String menuIdToUpdate = getMenuIdFromName(branch, formattedName);
        
        if (!Repository.BRANCH.get(branch).getFoodCategoryList().contains(foodCategory)) {
        	Repository.BRANCH.get(branch).getFoodCategoryList().add(foodCategory);
        }
        
        if (!menuIdToUpdate.equals("")) {
            // means there is a duplicate 
            return false;
        }
        
        int mid = Helper.generateUniqueId(Repository.BRANCH.get(branch).getMenuItems());
        String menuItemId = String.format("M%04d", mid);
        MenuItem newMenuItem = new MenuItem(branch, menuItemId, name, foodCategory, description, price, foodAvailability);
        Repository.BRANCH.get(branch).getMenuItems().put(menuItemId, newMenuItem);
        Repository.persistData(FileType.BRANCH);
        return true;
    }

    /**
     * Updates an existing menu item, with details of its name, preparation description and price to the menu
     * 
     * @param name Name of the menu item to be updated
     * @param description Description of the updated menu item
     * @param price Price of the updated menu item
     * @return {@code true} if menu item is updated successfully. Otherwise, {@code false} if menu item fails to be updated (menu item not found in database)
     */
    public static boolean updateMenuItem(String branch,String name, String description, double price, String foodCategory, FoodAvailability foodAvailability){
        String formattedName = name.toUpperCase();
        String menuIdToUpdate = getMenuIdFromName(branch, formattedName);
        if (menuIdToUpdate.equals("")) {
            return false;
        }
        MenuItem menuItemToUpdate = Repository.BRANCH.get(branch).getMenuItems().get(menuIdToUpdate);
        menuItemToUpdate.setName(formattedName);
        menuItemToUpdate.setDescription(description);
        menuItemToUpdate.setPrice(price);
        menuItemToUpdate.setFoodCategory(foodCategory);
        menuItemToUpdate.setFoodAvailability(foodAvailability);
        Repository.persistData(FileType.BRANCH);
        return true;
    }

    /**
     * Removes menu item from the menu
     * 
     * @param name Name of the menu item to be removed
     * @return {@code true} if menu item is removed successfully. Otherwise, {@code false} if menu item fails to be removed (menu item not found in database)
     */
    public static boolean removeMenuItem(String branch,String name) {
        String formattedName = name.toUpperCase();
        String menuIdToDelete = getMenuIdFromName(branch, formattedName);
        if (menuIdToDelete.equals("")) {
            // not found
            return false;
        }
        Repository.BRANCH.get(branch).getMenuItems().remove(menuIdToDelete);
        Repository.persistData(FileType.BRANCH);
        return true;
    }

    /**
     * Gets the menu item id from the specified menu item name.
     * @param name Name of the menu item
     * @return The menu item id if menu item found. Otherwise, {@code ""} if menu item not found
     */
    public static String getMenuIdFromName(String branch,String name) {
        for (MenuItem currentMenuItem : Repository.BRANCH.get(branch).getMenuItems().values()) {
            if (currentMenuItem.getName().equalsIgnoreCase(name)) {
                return currentMenuItem.getMenuItemId();
            }
        }
        return "";
    }

    /**
     * Filter the menu items of a specific branch by food category
     * @param menuItems Hash map of menu items, branch Branch of menu item, foodCategory Food category of menu item
     * @return Hash Map of filtered menu items by food category
     */
    public static HashMap<String, MenuItem> filterMenuItemsByCategory(HashMap<String, MenuItem> menuItems, String foodCategory) {
        HashMap<String, MenuItem> filteredMenuItems = new HashMap<>();

        for (HashMap.Entry<String, MenuItem> entry : menuItems.entrySet()) {
            MenuItem menuItem = entry.getValue();
            if (menuItem.getFoodCategory().equalsIgnoreCase(foodCategory)) {
                filteredMenuItems.put(entry.getKey(), menuItem);
            }
        }
        
        return filteredMenuItems;
    }
    
    
    /**
     * Initializer for dummy menu items in the hotel. 
     */
    public static void initializeDummyMenuItems() {
        addMenuItem("NTU", "Fries", "Side", 
        		"Crispy golden fries, perfectly seasoned for a savory indulgence", 
        		3.2,
        		FoodAvailability.AVAILABLE);
        addMenuItem("NTU", "3 Piece Set Meal", "Set Meal", 
        		"A satisfying trio featuring juicy chicken, fries, and a refreshing drink", 
        		9.9,
        		FoodAvailability.AVAILABLE);
        addMenuItem("JP", "Cajun Fish", "Burger",
        		"A flavorful fusion of Cajun-spiced fish fillet, fresh veggies, and zesty sauce, served in a soft bun", 
        		5.6,
        		FoodAvailability.AVAILABLE);
        addMenuItem("JE", "Coleslaw", "Side", 
        		"A crisp blend of cabbage, carrots, and onions, coated in a creamy dressing with a tangy kick", 
        		2.7,
        		FoodAvailability.AVAILABLE);
        addMenuItem("JE", "3 Piece Set Meal", "Set Meal",
                "A satisfying trio featuring juicy chicken, fries, and a refreshing drink",
                10.4,
                FoodAvailability.AVAILABLE);
        addMenuItem("JP","Chicken Nugget", "Side",
        		"Tender chicken bites, breaded and fried to golden perfection, ideal for dipping",
                6.9,
                FoodAvailability.AVAILABLE);
        addMenuItem("NTU","Chicken Nugget", "Side",
        		"Tender chicken bites, breaded and fried to golden perfection, ideal for dipping",
                6.6,
                FoodAvailability.AVAILABLE);
        addMenuItem("JP","Pepsi", "Drink",
        		"A classic thirst-quencher, Pepsi offers a refreshing burst of cola flavor with every sip",
                2.1,
                FoodAvailability.AVAILABLE);
    }

    /**
     * Print the menu items from a specific food category on the menu, along with the details of each menu item.
     */
    public static void printMenuByFoodCategory(String branch, String foodCategory) {
        int num = 1;
    	for (MenuItem menuItem : Repository.BRANCH.get(branch).getMenuItems().values()) {
            if (menuItem.getFoodCategory().equalsIgnoreCase(foodCategory)) {
	        	System.out.println("Item " + num++);
            	System.out.println("Item name: " + menuItem.getName());
	            System.out.println("Description: " + menuItem.getDescription());
	            System.out.println(String.format("Price: $%.2f", menuItem.getPrice()));
	            System.out.println("Availabilty: " + menuItem.getFoodAvailability());
	            System.out.println();
            }
        }
    }
   
    
    /**
     * Print all menu items on the menu, along with the details of each menu item.
     */
    public static void printAllMenuItems(String branch) {
    	int num = 1;
        for (MenuItem menuItem : Repository.BRANCH.get(branch).getMenuItems().values()) {
        	System.out.println("Item (" + (num++) + ")");
        	System.out.println("Item name: " + menuItem.getName());
        	System.out.println("Item Category: " + menuItem.getFoodCategory());
	        System.out.println("Description: " + menuItem.getDescription());
	        System.out.println(String.format("Price: $%.2f", menuItem.getPrice()));
	        System.out.println("Availabilty: " + menuItem.getFoodAvailability());
	        System.out.println();
        }
    }
    
    /**
     * Print all the food category in the menu of a specific branch.
     */
    public static void printFoodCategory(String branch) {
    	int num = 1;
        for (String foodCategory : Repository.BRANCH.get(branch).getFoodCategoryList()) {
	        System.out.println("(" + (num++) + ") " + foodCategory);
        }
    }
    
   
    


}