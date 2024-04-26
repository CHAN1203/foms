package model;

/**
 * FoodCategory represents the food category in branch's menu. <p>
 * 
 * Each food category consists of name
 * @author Jacky
 * @version 1.0
 * @since 2024-04-10
 */
public class FoodCategory {
	/**
	 * Name of food category
	 */
	private String name;
	
	/**
	 * Constructs and initializes food category in branch's menu
	 * @param name Name of food category
	 */
	public FoodCategory(String name) {
		setFoodCategory(name);
	}
	
	/**
	 * Sets the food category in branch's menu
	 * @param name Name of food category
	 * @return {@code true} if sets successfully
	 */
	public boolean setFoodCategory(String name) {
		this.name = name;
		return true;
	}
	
	/**
	 * Gets the food category in branch's menu
	 * @param name Name of food category
	 * @return Name of food category
	 */
	public String getFoodCategory(String name) {
		return name;
	}
	
	
}
