package main;

import repository.FileType;
import repository.Repository;
import enums.EmployeePosition;
import model.Employee;
import view.FastFoodAppView;
import view.LoginView;

public class FOMSApp {

	public static void main(String[] args) {
		
		//Employee employee = new Employee("HS Yap", "iamgay", EmployeePosition.ADMIN);
		//Repository.Employee.put("HS Yap", employee);
		//Repository.persistData(FileType.EMPLOYEE);
		
		Repository.readData(FileType.EMPLOYEE);
		//System.out.println(Repository.Employee.get("HS Yap").getName());
		//System.out.println(Repository.Employee.get("HS Yap").getPassword());
		FastFoodAppView fastFoodAppView = new FastFoodAppView();
		fastFoodAppView.viewApp();
	}

}
