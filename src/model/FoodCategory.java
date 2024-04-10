package model;

public class FoodCategory {
	private String name;
	
	public FoodCategory(String name) {
		setFoodCategory(name);
	}
	
	public boolean setFoodCategory(String name) {
		this.name = name;
		return true;
	}
	
	public String getFoodCategory(String name) {
		return name;
	}
	
	
}
