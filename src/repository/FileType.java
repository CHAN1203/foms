package repository;

public enum FileType {
	
	EMPLOYEE("Employee");
	
	public final String fileName;
	
	private FileType(String name) {
		this.fileName = name;
	}
}
