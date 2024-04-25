package repository;

public enum FileType {
	
	EMPLOYEE("Employee"),
	CATEGORY("Category"),
	// ORDERS("Orders"),
	MENU_ITEMS("MenuItems"),
	BRANCH("Branch"),
	ADMIN("Admin"),
	PAYMENT_METHODS("PaymentMethods");
	
	public final String fileName;
	
	private FileType(String name) {
		this.fileName = name;
	}
}
