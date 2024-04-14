package repository;

public enum FileType {
	
	EMPLOYEE("Employee"),
	CATEGORY("Category"),
	// ORDERS("Orders"),
	MENU_ITEMS("MenuItems"),
	BRANCH("Branch"),
	ADMIN("Admin");
	
	public final String fileName;
	
	private FileType(String name) {
		this.fileName = name;
	}
}
