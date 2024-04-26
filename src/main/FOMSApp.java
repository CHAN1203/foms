package main;
import repository.FileType;
import repository.Repository;
import view. *;

public class FOMSApp {

	public static void main(String[] args) {
		
		Repository.clearDatabase();
		Repository.initializeDummyAdmin();
		Repository.initializeDummyBranch();
		Repository.initializeDummyMenu();
		Repository.initializeDummyEmployee();
		Repository.initializeDummyPaymentMethod();
		
		Repository.readData(FileType.EMPLOYEE);
		Repository.readData(FileType.BRANCH);
		Repository.readData(FileType.ADMIN);
		Repository.readData(FileType.PAYMENT_METHODS);
		
		printFOMSTitle();
		FastFoodAppView fastFoodAppView = new FastFoodAppView();
		fastFoodAppView.viewApp();
	
	}
	
	private static void printFOMSTitle() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           _________    _______     __      __    ______                             ║");
        System.out.println("║                          /        /  /       \\    / \\    //|    /     \\                             ║");
        System.out.println("║                          ▐▐▐▐▐▐▐▐▐/  ▐▐▐▐▐▐▐▐ |  ▐▐  \\  ▐▐ |   /▐▐▐▐▐▐▐ |                           ║");
        System.out.println("║                          ▐▐ |        ▐▐ |  ▐▐ |  ▐▐▐▐  ▐▐▐▐ |  ▐▐ \\__▐▐/                            ║");
        System.out.println("║                          ▐▐ |______  ▐▐ |  ▐▐ |  ▐▐ |▐▐/ ▐▐ |  ▐▐                                   ║");
        System.out.println("║                          ▐▐▐▐▐▐▐▐▐/  ▐▐ |  ▐▐ |  ▐▐ |    ▐▐ |   ▐▐▐▐▐▐▐\\                            ║");
        System.out.println("║                          ▐▐ |        ▐▐ |  ▐▐ |  ▐▐ |    ▐▐ |     \\__▐▐|                            ║");
        System.out.println("║                          ▐▐ |        ▐▐ |  ▐▐ |  ▐▐ |    ▐▐ |  ▐▐    ▐▐ /                           ║");
        System.out.println("║                          ▐▐ /        ▐▐▐▐▐▐▐▐/   ▐▐/     ▐▐/    ▐▐▐▐▐▐ /                            ║");
        System.out.println("║                                                                                                     ║");
        System.out.println("║                           Welcome to Fastfood Ordering Management System                            ║");
        System.out.println("║                                                                                                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }


}
