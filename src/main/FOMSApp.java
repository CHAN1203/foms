package main;

import repository.FileType;
import repository.Repository;
import enums.EmployeePosition;
import model.Employee;
import view.LoginView;

public class FOMSApp {

	public static void main(String[] args) {
		
		//Employee employee = new Employee("HS Yap", "iamgay", EmployeePosition.ADMIN);
		//Repository.Employee.put("HS Yap", employee);
		//Repository.persistData(FileType.EMPLOYEE);
		
		Repository.readData(FileType.EMPLOYEE);
		//System.out.println(Repository.Employee.get("HS Yap").getName());
		//System.out.println(Repository.Employee.get("HS Yap").getPassword());
		LoginView loginView = new LoginView();
		loginView.viewApp();
	}

}
