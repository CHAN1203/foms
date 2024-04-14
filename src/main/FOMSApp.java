package main;
import repository.FileType;
import repository.Repository;
import enums.EmployeeGender;
import enums.EmployeePosition;
import model. *;
import view. *;

public class FOMSApp {

	public static void main(String[] args) {
		
//		Admin admin = new Admin("Boss", "password", EmployeePosition.ADMIN, EmployeeGender.FEMALE, 62, "boss");
//		Repository.EMPLOYEE.put("HS Yap", employee);
//		Repository.persistData(FileType.EMPLOYEE);
		
//		Repository.clearDatabase();
		
		Repository.initializeDummyBranch();
		Repository.initializeDummyMenu();
		Repository.initializeDummyEmployee();
		
		
		Repository.readData(FileType.EMPLOYEE);
		Repository.readData(FileType.BRANCH);
		//System.out.println(Repository.Employee.get("HS Yap").getName());
		//System.out.println(Repository.Employee.get("HS Yap").getPassword());
		FastFoodAppView fastFoodAppView = new FastFoodAppView();
		fastFoodAppView.viewApp();
	}

}
