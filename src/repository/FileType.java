package repository;

public enum FileType {
	
	EMPLOYEE("Employee"),
	CATEGORY("Category"),
	ORDERS("Orders"),
	MENU_ITEMS("MenuItems"),
	BRANCH("Branch");
	
	public final String fileName;
	
	private FileType(String name) {
		this.fileName = name;
	}
}
