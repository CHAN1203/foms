package model;

import java.io.Serializable;
import enums.FoodAvailability;

/**
 * MenuItem represents the menu item in the customisable menu. <p>
 * 
 * Each menu item contains a menu item id, the name of the item, the description of the item and the price of the item.
 * @author Sher Min, Jacky
 * @version 1.0
 * @since 2024-04-10
 */
public class MenuItem implements Serializable{
	 /**
     * For Java Serializable
     */
    private static final long serialVersionUID = 4L;
    
    /**
     * 
     */
    private String branch;
    
    /**
     * Id of the menu item
     */
    private String menuItemId;
    
    /**
     * Name of the menu item
     */
    private String name;
    
    /**
     * Food Category of menu item
     */
    private String foodCategory;
    
    /**
     * Description of the preparation methods of the menu item
     */
    private String description;
    
    /**
     * Price of the menu item
     */
    private double price;
    
    /**
     * Availability of the menu item
     */
    private FoodAvailability  foodAvailability;
    
    /**
     * Constructs and initializes the branch, id, name, food category, description, price and availability of menu item.
     * 
     * @param branch Branch of the menu item
     * @param menuItemId Id of the menu item
     * @param name Name of the menu item
     * @param foodCategory Food category of the menu item
     * @param description Description of the menu item
     * @param price Price of the menu item
     * @param foodAvailability Availability of menu item
     */
    public MenuItem(String branch, String menuItemId, String name, String foodCategory, String description, double price, FoodAvailability  foodAvailability) {
        setBranch(branch);
    	setMenuItemId(menuItemId);
        setName(name);
        setFoodCategory(foodCategory);
        setDescription(description);
        setPrice(price);
        setFoodAvailability(foodAvailability);
    }
    
    /**
     * Sets the Branch of the menu item
     * 
     * @param branch Branch of the menu item
     * @return {@code true} if sets successfully
     */
    public boolean setBranch(String branch) {
        this.branch = branch;
        return true;
    } 
    
    /**
     * Sets the Id of the menu item
     * 
     * @param menuItemId Id of the menu item
     * @return {@code true} if sets successfully
     */
    public boolean setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
        return true;
    }
    
    /**
     * Sets the name of the menu item
     * 
     * @param name Name of menu item
     * @return {@code true} if sets successfully
     */
    public boolean setName(String name) {
        this.name = name;
        return true;
    }
    
    /**
     * Sets the Food Category of the menu item
     * 
     * @param foodCategory Food Category of menu item
     * @return {@code true} if sets successfully
     */
    public boolean setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
        return true;
    }

    /**
     * Sets the description of the menu item
     * 
     * @param description Description of menu item
     * @return {@code true} if sets successfully
     */
    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    /**
     * Sets the price of the menu item
     * 
     * @param price Price of menu item
     * @return {@code true} if sets successfully. Otherwise, {@code false} if failed to set price when a negative number is passed as an argument
     */
    public boolean setPrice(double price) {
        if (price < 0) {
            return false;
        }
        this.price = price;
        return true;
    }
    
    /**
     * Sets the Food Availability of the menu item
     * 
     * @param foodAvailability Food Availability of menu item
     * @return {@code true} if sets successfully
     */
    public boolean setFoodAvailability(FoodAvailability foodAvailability) {
    	this.foodAvailability = foodAvailability;
    	return true;
    };

    /**
     * Gets the Branch of the menu item
     * 
     * @return Branch of the menu item
     */
    public String getBranch() {
        return branch;
    }
    
    /**
     * Gets the Id of the menu item
     * 
     * @return Id of the menu item
     */
    public String getMenuItemId() {
        return menuItemId;
    }
    
    /**
     * Gets the Name of the menu item
     * 
     * @return Name of the menu item
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the Food Category of the menu item
     * 
     * @return Food Category of the menu item
     */
    public String getFoodCategory() {
        return foodCategory;
    }

    /**
     * Gets the description of the menu item
     * 
     * @return Description of the menu item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the price of the menu item
     * 
     * @return Price of the menu item
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Gets the food availability of the menu item
     * 
     * @return Food Availability of the menu item
     */
    public FoodAvailability getFoodAvailability() {
    	return foodAvailability;
    };

    
    
    
}
