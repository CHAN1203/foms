package model;

import java.io.Serializable;

public class MenuItems implements Serializable{// this is just a copy paste from Aarons code, CHANGE TO OUR OWN
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
     * Description of the preparation methods of the menu item
     */
    private String description;
    /**
     * Price of the menu item
     */
    private double price;
    
    /**
     * Constructs and initializes the Id, name, description and price of the menu item respectively.
     * 
     * @param menuItemId Id of the menu item.
     * @param name Name of the menu item
     * @param description Description of the preparation methods of the menu item
     * @param price Price of the menu item
     */
    public MenuItems(String branch, String menuItemId, String name, String description, double price) {
        setBranch(branch);
    	setMenuItemId(menuItemId);
        setName(name);
        setDescription(description);
        setPrice(price);
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
     * Sets the Branch of the menu item
     * 
     * @param Branch of the menu item
     * @return {@code true} if sets successfully
     */
    public boolean setBranch(String branch) {
        this.branch = branch;
        return true;
    }

    /**
     * Gets the name of the menu item
     * 
     * @return Name of the menu item
     */
    public String getName() {
        return name;
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
     * Gets the Id of the menu item
     * 
     * @return Id of the menu item
     */
    public String getMenuItemId() {
        return menuItemId;
    }
    
    /**
     * Gets the Branch of the menu item
     * 
     * @return Branch of the menu item
     */
    public String getBranch() {
        return branch;
    }
}
