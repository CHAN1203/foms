package main;
import repository.FileType;
import repository.Repository;
import enums.EmployeeGender;
import enums.EmployeePosition;
import model. *;
import view. *;

public class FOMSApp {

	public static void main(String[] args) {
		
//		Repository.clearDatabase();
//		Repository.initializeDummyAdmin();
//		Repository.initializeDummyBranch();
//		Repository.initializeDummyMenu();
//		Repository.initializeDummyEmployee();
//		Repository.initializeDummyPaymentMethod();
		
		Repository.readData(FileType.EMPLOYEE);
		Repository.readData(FileType.BRANCH);
		Repository.readData(FileType.ADMIN);
		Repository.readData(FileType.PAYMENT_METHODS);
		
		FastFoodAppView fastFoodAppView = new FastFoodAppView();
		fastFoodAppView.viewApp();
	}

}
