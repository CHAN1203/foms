package view;

import helper.Helper;
import model.Employee;
import repository.Repository;
import controller.ManagerController;
import controller.MenuController;
import controller.StaffController;
import controller.UserController;
import enums. *;

/**
 * ManagerView provides the view to take user input which calls {@link MenuController} to handle {@link Order}
 * 
 * @author Shermin, Yue Hang
 * @version 1.0
 * @since 2024-04-06
 */
public class ManagerView extends StaffView{
	/**
	 * branch name ManagerView;
	 */
	private String branch;
	/**
	 * Constructor for ManagerView
	 * @param branch branch name for the branch that the manager is from
	 */
	public ManagerView(String branch) {
		super(branch);
		this.branch = branch;
	}
	/**
	 * View Actions of ManagerView
	 */
	public void printActions() {
		printBreadCrumbs("Fast Food App View > Login View > Manager View");
		System.out.println("What would you like to do ?");
        System.out.println("(1) Add menu item");
        System.out.println("(2) Remove menu item");
        System.out.println("(3) Update menu item");
        System.out.println("(4) Print all menu items");
        System.out.println("(5) Display processing order");
        System.out.println("(6) View order details");
		System.out.println("(7) Process order");
		System.out.println("(8) Display staff list");
		System.out.println("(9) Change password");
		System.out.println("(10) Back");
	}
	/**
	 * View Application of ManagerView
	 */
	public void viewApp() {
		int opt = -1; 
		String name;
        String description;
        double price;
        String foodCategory;
        FoodAvailability foodAvailability;
        do {
            printActions();
            opt = Helper.readInt(1, 10);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Manager View > Add menu item");
                    System.out.println("Enter name of item to be added:");
                    name = Helper.readString();
                    System.out.println("Enter description of " + name + ":");
                    description = Helper.readString();
                    do {
	                    System.out.println("Enter price of " + name + ":");
	                    price = Helper.readDouble();
	                    if(price<=0) {
	                    	System.out.println("Error. Negative price, please key in again...");
	                    }
                    }while (price<=0);
                    System.out.println("Enter food category of " + name + ":");
                    foodCategory = Helper.readString();
                    foodAvailability = promptFoodAvailability(name);
                    addMenuItem(name, description, price, foodCategory, foodAvailability);
                    break;
                
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Manager View > Remove menu item");
                    System.out.println("Enter name of item to be removed:");
                    name = Helper.readString();
                    removeMenuItem(name);
                    break;
                
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Manager View > Update menu item");
                    System.out.println("Enter name of item to be updated:");
                    name = Helper.readString();
                    System.out.println("Enter description of " + name + ":");
                    description = Helper.readString();
                    System.out.println("Enter price of " + name + ":");
                    price = Helper.readDouble();
                    System.out.println("Enter food category of " + name + ":");
                    foodCategory = Helper.readString();
                    foodAvailability = promptFoodAvailability(name);
                    updateMenuItem(name, description, price, foodCategory, foodAvailability);
                    break;
                    
                case 4:
                	Helper.clearScreen();
                	printBreadCrumbs("Fast Food App View > Login View > Manager View > Print All Menu Items");
                	MenuController.printAllMenuItems(this.branch);
                	break;
                case 5:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Manager View > Display Processing Order");
                	StaffController.displayProcessingOrders(this.branch);
                	break;
                case 6:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Manager View > View Order Details");
                	System.out.println("Select order to view details:");
					int choice = promptSelectOrderId(this.branch);
					if(choice == 0) {
						continue;
					}else if(choice == Repository.BRANCH.get(branch).getOrders().size() +1) {
						break;
					}
					else {
						StaffController.viewParticularOrderDetails(this.branch, choice);
					}
                    break;
                case 7:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Manager View > Process Order View");
                	System.out.println("Select order to process:");
					int selection = promptSelectOrderId(this.branch);
					if(selection == 0) {
						continue;
					}else if(selection == Repository.BRANCH.get(branch).getOrders().size() +1) {
						break;
					}
					else {
						StaffController.updateOrderStatus(this.branch, selection);
					}
					break;
                case 8:
                	Helper.clearScreen();
                	printBreadCrumbs("Fast Food App View > Login View > Manager View > Display Staff List View");
                	if(ManagerController.displayStaffList(this.branch)) {
                	}
                	else {
                		System.out.println("No staff in branch");
                	}
            		break;
                case 9:
                	printBreadCrumbs("Fast Food App View > Login View > Manager View > Change Password View");
                	promptChangePassword();
                	break;
                case 10:
                	break;
            }
            if (opt != 10) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 10);
	}
	/**
	 * The function to add a {@link MenuItem} to the list of MenuItem through {@link MenuController}
	 * @param name name of the MenuItem to be added
	 * @param description description of the MenuItem to be added
	 * @param price price of the MenuItem to be added
	 * @param foodCategory category of the MenuItem to be added
	 * @param foodAvailability availability of the MenuItem to be added 
	 */
	private void addMenuItem(String name, String description, double price, String foodCategory, FoodAvailability foodAvailability){
        if (MenuController.addMenuItem(this.branch, name, foodCategory, description, price, foodAvailability)){
            System.out.printf("\"%s\" added to menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to menu FAILED (\"%s\" is already in the menu)\n", name);
        }
    }
	/**
	 * The function to remove a {@link MenuItem} from the list of MenuItem through {@link MenuController}
	 * @param name name of the MenuItem to be removed
	 */
	private void removeMenuItem(String name){
        if (MenuController.removeMenuItem(this.branch, name)){
            System.out.printf("\"%s\" removed from menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from menu FAILED (\"%s\" NOT FOUND in order\\ removal quantity > current quantity)\n", name);
        }
    }
	/**
	 * The function to update the attributes of a {@link MenuItem}
	 * @param name name of the MenuItem to be updated
	 * @param description new description for the MenuItem
	 * @param price new price of the MenuItem
	 * @param foodCategory new category of the MenuItem
	 * @param foodAvailability new availability of the MenuItem
	 */
	private void updateMenuItem(String name, String description, double price, String foodCategory, FoodAvailability foodAvailability) {
		if (MenuController.updateMenuItem(this.branch , name, description, price, foodCategory, foodAvailability)){
            System.out.printf("%s updated in menu SUCCESSFULLY\n", name);
        } else {
            System.out.printf("Update menu FAILED (\"%s\" NOT FOUND in menu)\n", name);
        }
    }
	/**
	 * The function to prompt Manager to select the availability of a particular MenuItem
	 * @param name name of the MenuItem
	 * @return the enum of FoodAvailability 
	 */
	private FoodAvailability promptFoodAvailability(String name) {
		int opt = -1;
		System.out.println("Choose availability of " + name + ":");
		System.out.println("(1)" + FoodAvailability.AVAILABLE);
		System.out.println("(2)" + FoodAvailability.UNAVAILABLE);
		opt = Helper.readInt(1,2);
		if (opt == 1) {
			return FoodAvailability.AVAILABLE;
		}
		else {
			return FoodAvailability.UNAVAILABLE;
		}
	}
	/**
	 * The function to prompt a Manager to change password through {@link UserController}
	 */
	private void promptChangePassword() {
		System.out.println("Verify your loginID: ");
		String loginId = Helper.readString();
		System.out.println("Verify your password: ");
		String password = Helper.readString();
		
		Employee emp = Repository.EMPLOYEE.get(loginId);
		
		if( emp != null && emp.getPassword().equals(password)) {
			System.out.println("Verification successful");
			System.out.println();
			System.out.println("Enter new password: ");
			String newPassword = Helper.readString();
			System.out.println("Re-enter new password: ");
			String confirmPassword = Helper.readString();
			if(UserController.changePassword(emp, newPassword, confirmPassword)) {
				System.out.println("Password changed successfully!");
				return;
			}
			else {
				System.out.println("Password does not match");
				return;
			}
			
		}
		else {
			System.out.println("Verification failed");
			return;
		}
	}
}
