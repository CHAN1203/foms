package main;
import helper.Helper;
import repository.FileType;
import repository.Repository;
import view. *;

/**
 * The starting point of the application.
 * @author Hong Sheng, Jacky, Kee Qing, Sher Min, Yue Hang
 * @version 1.0
 * @since 2024-04-01
 */
public class FOMSApp {

	/**
     * Main function that is the starting point of the application.
     * @param args Arguments passed to the app
     */
	public static void main(String[] args) {
		
		//Repository.clearDatabase();
		Repository.readData(FileType.EMPLOYEE);
		Repository.readData(FileType.BRANCH);
		Repository.readData(FileType.ADMIN);
		Repository.readData(FileType.PAYMENT_METHODS);
		Repository.initializeDummyAdmin();
		Repository.initializeDummyBranch();
		Repository.initializeDummyMenu();
		Repository.initializeDummyEmployee();
		Repository.initializeDummyPaymentMethod();
		Helper.clearScreen();
		printFOMSTitle();
		FastFoodAppView fastFoodAppView = new FastFoodAppView();
		fastFoodAppView.viewApp();
	
	}
	
	 /**
     * Prints the HRPS title.
     */
	private static void printFOMSTitle() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           _________    _______     __      __    ______                             ║");
        System.out.println("║                          /        /  /       \\   / \\    //|   /     \\                               ║");
        System.out.println("║                          ▐▐▐▐▐▐▐▐▐/  ▐▐▐▐▐▐▐▐ |  ▐▐  \\  ▐▐ |  /▐▐▐▐▐▐▐ |                            ║");
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
