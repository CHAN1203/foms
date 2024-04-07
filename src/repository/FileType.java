package repository;

public enum FileType {
	
	EMPLOYEE("Employee"),
	CATEGORY("Category");
	
	public final String fileName;
	
	private FileType(String name) {
		this.fileName = name;
	}
	
	
}