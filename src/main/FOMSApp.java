package main;
import repository.FileType;
import repository.Repository;
import enums.EmployeePosition;
import model.Employee;
import view. *;

public class FOMSApp {

	public static void main(String[] args) {
		
		//Employee employee = new Employee("HS Yap", "iamgay", EmployeePosition.ADMIN);
		//Repository.Employee.put("HS Yap", employee);
		//Repository.persistData(FileType.EMPLOYEE);
		
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
