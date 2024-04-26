package controller;
import helper.Helper;
import repository.Repository;
import repository.FileType;
import enums. *;
import model. *;
import java.util.ArrayList;
import java.util.Map;

/** AdminController is a controller class that acts as a "middleman"
 * between the view classes - {@link AdminView}, {@link DisplayStaffView}, {@link ManageBranchView}, 
 * {@link ManageStaffAccountView} and {@link ManagePaymentView} and the model class - {@link Branch} and {@link Employee}. <p>
 * 
 * It can initialize, add/edit/remove staff, display, assign, promote, transfer, add/remove payment method and open/close branch 
 * @author Chan Kee Qing
 * @version 1.0
 * @since 2024-04-01
 */
public class AdminController {

	/**
	 * function to add new staff account
	 * @param name the name of the new staff 	
	 * @param password the password of the new staff
	 * @param branch the branch of the new staff
	 * @param position the position of the new staff
	 * @param gender the gender of the new staff
	 * @param age the age of the new staff
	 * @param loginId the loginId of the new staff
	 * @return {@code true} if add staff is successful. Otherwise, {@code false} 
	 */
	public static void addStaffAccount(String name, String password, String branch, EmployeePosition position, EmployeeGender gender, int age, String loginId) {
		Employee emp = new Employee(name, password, branch, position, gender, age, loginId);
		//several conditions to recognize number of staff in specified position has increased
		if(emp.getPosition() == EmployeePosition.STAFF) {
			Repository.BRANCH.get(emp.getBranch()).addNumberOfStaff();
			Repository.BRANCH.get(emp.getBranch()).addNumberOfEmployee();
		}else if(emp.getPosition() == EmployeePosition.MANAGER) {
			Repository.BRANCH.get(emp.getBranch()).addNumberOfManager();
			Repository.BRANCH.get(emp.getBranch()).addNumberOfEmployee();
		}
		Repository.BRANCH.get(emp.getBranch()).getEmployee().put(emp.getLoginId(),emp);
		Repository.EMPLOYEE.put(emp.getLoginId(), emp);
		Repository.persistData(FileType.EMPLOYEE);
		Repository.persistData(FileType.BRANCH);
	}
	
	
    /**
     * function to update staff name
     * @param loginId targeted staff's login id
     * @param name targeted staff's name
     * @param attributeCode the attribute code for the detail that user choose to update
     * @return {@code true} if update staff is successful. Otherwise, {@code false}
     */
    public static boolean updateStaffAccount(String loginId,  String name, int attributeCode) {
    	//create a updateList that store Employee object
        ArrayList<Employee> updateList = searchStaffById(loginId);
        if (updateList.size() == 0) {
            // guest not found
            return false;
        }
        //loop through employee object
        for (Employee employee : updateList) {
            Employee staffToUpdate;
            switch (attributeCode) {
                case 1:
                	staffToUpdate = Repository.EMPLOYEE.get(loginId);
                	staffToUpdate.setName(name);
                    Repository.EMPLOYEE.put(employee.getLoginId(), staffToUpdate);
                    break;
                default:
                    break;
            }
        }
        Repository.persistData(FileType.EMPLOYEE);
        return true;
    }
    
    
    /**
     * Overloading method of updateGuest that update the gender of the employee <p>
     * @param loginId targeted staff's login id
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param gender targeted staff's gender
     * @return {@code true} if update staff is successful. Otherwise, {@code false}
     */
    public static boolean updateStaffAccount(String loginId, int attributeCode, EmployeeGender gender) {
    	//create a updateList that store Employee object
        ArrayList<Employee> updateList = searchStaffById(loginId);
        if (updateList.size() == 0) {
            // guest not found
            return false;
        }
        //loop through employee object
        for (Employee employee : updateList) {
            Employee staffToUpdate;
            switch (attributeCode) {
                case 2:
                	staffToUpdate = Repository.EMPLOYEE.get(loginId);
                	staffToUpdate.setGender(gender);
                    Repository.EMPLOYEE.put(employee.getLoginId(), staffToUpdate);
                    break;
                default:
                    break;
            }
        }
        Repository.persistData(FileType.EMPLOYEE);
        return true;
    }
    
    /**
     * Overloading method of updateGuest that update the age of the employee <p>
     * @param loginId targeted staff's login id
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param age targeted staff's age
     * @return {@code true} if update staff is successful. Otherwise, {@code false}
     */
    public static boolean updateStaffAccount(String loginId, int attributeCode, int age ) {
    	//create a updateList that store Employee object
        ArrayList<Employee> updateList = searchStaffById(loginId);
        if (updateList.size() == 0) {
            // guest not found
            return false;
        }
        //loop through employee object
        for (Employee employee : updateList) {
            Employee staffToUpdate;
            switch (attributeCode) {
                case 3:
                	staffToUpdate = Repository.EMPLOYEE.get(loginId);
                	staffToUpdate.setAge(age);
                    Repository.EMPLOYEE.put(employee.getLoginId(), staffToUpdate);
                    break;
                default:
                    break;
            }
        }
        Repository.persistData(FileType.EMPLOYEE);
        return true;
    }
    
    
    /**
     * Function to remove employee from the database <p>
     * @param loginID the targeted employee's login ID
     * @return {@code true} if remove successfully. Otherwise, {@code false} if employee id is not found
     */
    public static boolean removeStaffAccount(String loginID) { 
    	ArrayList<Employee> removeList = searchStaffById(loginID);
        if (removeList.size() == 0) {
            // not found
            return false;
        }
        for (Employee employee : removeList) {
            if (Helper.promptConfirmation("remove this guest")) {
            	if(employee.getPosition() == EmployeePosition.STAFF) {
            		Repository.BRANCH.get(employee.getBranch()).deductNumberOfStaff();
        		}else if(employee.getPosition() == EmployeePosition.MANAGER) {
        			Repository.BRANCH.get(employee.getBranch()).deductNumberOfManager();
        		}
            	Repository.BRANCH.get(employee.getBranch()).deductNumberOfEmployee();
                Repository.EMPLOYEE.remove(loginID);
                Repository.BRANCH.get(employee.getBranch()).getEmployee().remove(loginID);
            } else {
                return false;
            }
        }
        Repository.persistData(FileType.EMPLOYEE);
        Repository.persistData(FileType.BRANCH);
        return true;
    }
    
    /**
     * function to return the employee object by searching employee's login id
     * @param name the targeted employee's name
     * @return employee object as a list
     */
    public static ArrayList<Employee> searchStaffById(String loginId) {
    	//create an array list to store employee object
        ArrayList<Employee> searchList = new ArrayList<Employee>();
        //if EMPLOYEE hash map contains a key equal to the value stored in the variable name
        if (Repository.EMPLOYEE.containsKey(loginId)) {
            Employee searchedStaff = Repository.EMPLOYEE.get(loginId);
            searchList.add(searchedStaff);
        }
        return searchList;
    }
    
    /**
     * function to display staff list according to branch
     * @param branch the branch of the restaurant 
     * @return {@code true} if display successfully. Otherwise, {@code false} 
     */
    public static boolean displayStaffListByBranch(String branch) {
    	ArrayList<Employee> staffNameList = new ArrayList<Employee>();
    
    	//can't just iterate through map, need to do modification to loop through, need to import packages for map.entry
        for (Map.Entry<String, Employee> entry : Repository.BRANCH.get(branch).getEmployee().entrySet()) {
        	Employee employee = entry.getValue();
            staffNameList.add(employee);
        }
        
        if(staffNameList.size() != 0) {
        	for(Employee employee : staffNameList) {
            	System.out.println(employee.getName());
            }
        	return true;
        }
        return false;
	}
    
    /**
     * Overloading function to display staff list according to branch
     * @param position the position of employee
     * @return {@code true} if display successfully. Otherwise, {@code false}
     */
    public static boolean displayStaffListByRole(EmployeePosition position) {
    	ArrayList<Employee> staffNameList = new ArrayList<Employee>();
    	//can't just iterate through map, need to do modification to loop through, need to import packages for map.entry
        for (Map.Entry<String, Employee> entry : Repository.EMPLOYEE.entrySet()) {
        	Employee employee = entry.getValue();
        	if (employee.getPosition().equals(position)) {
        		staffNameList.add(employee);
        	}
            
        }
        if(staffNameList.size() != 0) {
        	for(Employee employee : staffNameList) {
            	System.out.println(employee.getName());
            }
        	return true;
        }
        return false;
	}
    
    
    /**
     * Overloading function to display staff list according to gender
     * @param gender the gender of the employee
     * @return {@code true} if display successfully. Otherwise, {@code false}
     */ 
    public static boolean displayStaffListByGender(EmployeeGender gender) {
    	ArrayList<Employee> staffNameList = new ArrayList<Employee>();
    	//can't just iterate through map, need to do modification to loop through, need to import packages for map.entry
        for (Map.Entry<String, Employee> entry : Repository.EMPLOYEE.entrySet()) {
        	Employee employee = entry.getValue();
        	if (employee.getGender().equals(gender)) {
        		staffNameList.add(employee);
        	}
        }
        if(staffNameList.size() != 0) {
        	for(Employee employee : staffNameList) {
            	System.out.println(employee.getName());
            }
        	return true;
        }
        return false;
	}
    
    /**
     * Overloading function to display staff list according to age
     * @param age the age of the employee
     * @return {@code true} if display successfully. Otherwise, {@code false}
     */
    public static boolean displayStaffListByAge(int age) {
    	ArrayList<Employee> staffNameList = new ArrayList<Employee>();
    	//can't just iterate through map, need to do modification to loop through, need to import packages for map.entry
        for (Map.Entry<String, Employee> entry : Repository.EMPLOYEE.entrySet()) {
        	Employee employee = entry.getValue();
        	if (employee.getAge() == age) {
        		staffNameList.add(employee);
        	}
        }
        if(staffNameList.size() != 0) {
        	for(Employee employee : staffNameList) {
            	System.out.println(employee.getName());
            }
        	return true;
        }
        return false;
	}
    
    
    /**
     * function to promote a staff to manager
     * @param loginId targeted staff's login id
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param position targeted staff's position after promotion
     * @return {@code true} if promote staff successfully. Otherwise, {@code false}
     */
    public static boolean promoteStaff(String loginId, int attributeCode, EmployeePosition position) {
    	//create a updateList that store Employee object
        ArrayList<Employee> updateList = searchStaffById(loginId);
        if (updateList.size() == 0) {
            // guest not found
            return false;
        }
        //loop through employee object
        for (Employee employee : updateList) {
            Employee staffToUpdate;
            switch (attributeCode) {
                case 2:
                	staffToUpdate = Repository.EMPLOYEE.get(loginId);
                	staffToUpdate.setPosition(position);
                	Repository.EMPLOYEE.remove(loginId);
                    Repository.BRANCH.get(employee.getBranch()).getEmployee().remove(loginId);
                    Repository.EMPLOYEE.put(employee.getLoginId(), staffToUpdate);
                    Repository.BRANCH.get(employee.getBranch()).getEmployee().put(employee.getLoginId(),staffToUpdate);
                    Repository.BRANCH.get(staffToUpdate.getBranch()).addNumberOfManager();
                    break;
                default:
                    break;
            }
        }
        Repository.persistData(FileType.EMPLOYEE);
        Repository.persistData(FileType.BRANCH);
        return true;
    }
    
    
    /**
     * function to transfer staff to other branch
     * @param loginId targeted staff's login id
     * @param branch targeted staff's branch after transferring
     * @return {@code true} if transfer staff successful. Otherwise, {@code false}
     */
    public static boolean transferStaff(String loginId,  String branch) {
    	//create a updateList that store Employee object
        ArrayList<Employee> updateList = searchStaffById(loginId);
        if (updateList.size() == 0) {
            // guest not found
            return false;
        }
        //loop through employee object
        for (Employee employee : updateList) {
        	if(Repository.BRANCH.get(branch).getNumberOfEmployee() < Repository.BRANCH.get(branch).getstaffQuota()) {
        		if(employee.getPosition() == EmployeePosition.MANAGER) {
                	if(Repository.BRANCH.get(branch).getNumberOfManager() < Repository.BRANCH.get(branch).getManagerQuota()) {
                		Repository.BRANCH.get(employee.getBranch()).deductNumberOfManager();
                        Employee staffToTransfer;
                    	staffToTransfer = Repository.EMPLOYEE.get(loginId);
                    	//remove the previous employee info on branch and employee hash map
                    	Repository.EMPLOYEE.remove(loginId);
                    	Repository.BRANCH.get(employee.getBranch()).getEmployee().remove(loginId);
                    	staffToTransfer.setBranch(branch);
                    	//serialize the updated employee info to the branch and employee hash map
                        Repository.EMPLOYEE.put(employee.getLoginId(), staffToTransfer);
                        Repository.BRANCH.get(employee.getBranch()).getEmployee().put(employee.getLoginId(),staffToTransfer);
                        Repository.persistData(FileType.EMPLOYEE);
                        Repository.persistData(FileType.BRANCH);
                      //once transfer successfully, must increase both number of staff and number of employee
                        Repository.BRANCH.get(staffToTransfer.getBranch()).addNumberOfManager();
                        Repository.BRANCH.get(staffToTransfer.getBranch()).addNumberOfEmployee();
                    	return true;
            		}else {
            			System.out.println("Manager Quota Exceeded. Transfer Manager Unsucessful!");
                    	return false;
            		}
        		}
        		Repository.BRANCH.get(employee.getBranch()).deductNumberOfStaff();
                Employee staffToTransfer;
            	staffToTransfer = Repository.EMPLOYEE.get(loginId);
            	Repository.EMPLOYEE.remove(loginId);
            	Repository.BRANCH.get(employee.getBranch()).getEmployee().remove(loginId);
            	staffToTransfer.setBranch(branch);
            	//serialize the updated employee info to the branch and employee hash map
                Repository.EMPLOYEE.put(employee.getLoginId(), staffToTransfer);
                Repository.BRANCH.get(employee.getBranch()).getEmployee().put(employee.getLoginId(),staffToTransfer);
                //update the hash map by overwriting them
                Repository.persistData(FileType.EMPLOYEE);
                Repository.persistData(FileType.BRANCH);
                //once transfer successfully, must increase both number of staff and number of employee
                Repository.BRANCH.get(staffToTransfer.getBranch()).addNumberOfStaff();
                Repository.BRANCH.get(staffToTransfer.getBranch()).addNumberOfEmployee();
                return true;
        	}else {
        		System.out.println("Staff Quota Exceeded. Transfer Staff Unsucessful!");
        		return false;
        	}
        }
        return false;
    }
    
   

    /**
     * function to open new branch
     * @param newBranch new branch's name
     * @param location new branch's location
     * @param staffQuota new branch's staff quota
     * @param numberOfStaff new branch's number of staff
     * @return {@code true} if open branch successful. Otherwise, {@code false}
     */
    public static boolean openBranch(String newBranch, String location, int staffQuota, int numberOfStaff) {
    	//number of staff, manager and so on are 0 when the branch is first created
    	Branch branch = new Branch(newBranch, location, staffQuota, 0, 0, 0, 0);
    	Repository.BRANCH.put(branch.getName(), branch);
    	Repository.persistData(FileType.BRANCH);
    	return true;
    }
    
    //use if-else statement to check if the branch exist
    /**
     * function to close a branch
     * @param branchToClose name of the branch to be closed
     * @return {@code true} if close branch successful. Otherwise, {@code false}
     */
    public static boolean closeBranch(String branchToClose) {
    	if (Repository.BRANCH.keySet().contains(branchToClose)) {
            // Close the branch
    		Repository.BRANCH.remove(branchToClose);
    		Repository.persistData(FileType.BRANCH);
            System.out.println("Branch '" + branchToClose + "' has been closed.");
            return true;
        } else {
            System.out.println("Branch '" + branchToClose + "' does not exist.");
            return false;
        }
    }
    
    
    /**
     * function to add new payment method
     * @param newPaymentMethod new payment method's name
     * @return {@code true} if add payment method successful. Otherwise, {@code false}
     */
    public static boolean addPaymentMethod(String newPaymentMethod) {
    	return Repository.PAYMENT_METHODS.add(newPaymentMethod);
    }
    
    public static boolean removePaymentMethod(String existedPaymentMethod) {
    	if (Repository.PAYMENT_METHODS.contains(existedPaymentMethod)) {
            // Close the branch
    		Repository.PAYMENT_METHODS.remove(existedPaymentMethod);
            System.out.println("Payment Method '" + existedPaymentMethod + "' has been removed.");
            return true;
        } else {
            System.out.println("Payment Method '" + existedPaymentMethod + "' does not exist.");
            return false;
        }
    }
    
    
    /**
     * function to assign a manager to a branch
     * @param name the name of the new staff 	
	 * @param password the password of the new staff
	 * @param branch the branch of the new staff
	 * @param position the position of the new staff
	 * @param gender the gender of the new staff
	 * @param age the age of the new staff
	 * @param loginId the loginId of the new staff
     */
	public static void assignManager(String name, String password, String branch, EmployeePosition position, EmployeeGender gender, int age, String loginId) {
		addStaffAccount(name,password,branch, position, gender, age, loginId);
	}
	
	
	/**
	 * function to initialize test case 
	 */
    public static void initializeDummyBranchInfo() {
    	openBranch("NTU", "North Spine Plaza", 8, 1);
    	openBranch("JE", "Jurong East", 11, 1);
    	openBranch("JP", "Jurong Point", 15, 1);
     }
    
    /**
	 * function to initialize test case 
	 */
    public static void initializeDummyEmployee() {
    	addStaffAccount("kumar Blackmore", "password", "NTU", EmployeePosition.STAFF, EmployeeGender.MALE, 32, "kumarB");
    	addStaffAccount("Alexei", "password", "NTU", EmployeePosition.MANAGER, EmployeeGender.MALE, 25, "Alexei");
    	addStaffAccount("Tom Chan", "password", "JP", EmployeePosition.MANAGER,EmployeeGender.MALE, 56, "TomC");
    	addStaffAccount("Alica Ang", "password", "JE", EmployeePosition.MANAGER, EmployeeGender.FEMALE, 27, "AlicaA");
    	addStaffAccount("Mary lee", "password", "JE", EmployeePosition.STAFF, EmployeeGender.FEMALE, 44, "MaryL");
    	addStaffAccount("Justin Loh", "password", "JP", EmployeePosition.STAFF, EmployeeGender.MALE, 49, "JustinL");
    }
    
    /**
	 * function to initialize test case 
	 */
    public static void initializePaymentMethod() {
    	addPaymentMethod("Cash");
    	addPaymentMethod("Paynow");
    	addPaymentMethod("Paywave");
     }
    
}
