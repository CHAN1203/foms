package controller;
import helper.Helper;
import repository.Repository;
import repository.FileType;
import enums. *;
import model. *;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminController {

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
		System.out.println("Manager Quota:" + Repository.BRANCH.get(emp.getBranch()).getManagerQuota());
		System.out.println("num of emlpoyee:" + Repository.BRANCH.get(emp.getBranch()).getNumberOfEmployee());
		System.out.println("num of manager:" + Repository.BRANCH.get(emp.getBranch()).getNumberOfManager());
		
		Repository.BRANCH.get(emp.getBranch()).getEmployee().put(emp.getLoginId(),emp);
		Repository.EMPLOYEE.put(emp.getLoginId(), emp);
		Repository.persistData(FileType.EMPLOYEE);
		Repository.persistData(FileType.BRANCH);
	}
	
	
    
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
     * @param guestId the guest id of the guest
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param gender the new gender of the guest
     * @return {@code true} if updating of guest is successful. Otherwise, {@code false} if the guest id is not found.
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
     * @param guestId the guest id of the guest
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param gender the new gender of the guest
     * @return {@code true} if updating of guest is successful. Otherwise, {@code false} if the guest id is not found.
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
     * @param LoginId the employee id of the employee that the user want to remove
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
            	//Repository.BRANCH.get(employee.getBranch()).addNumberOfStaff();
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
    
    public static ArrayList<Employee> searchStaffById(String name) {
    	//create an array list to store employee object
        ArrayList<Employee> searchList = new ArrayList<Employee>();
        //if EMPLOYEE hash map contains a key equal to the value stored in the variable name
        if (Repository.EMPLOYEE.containsKey(name)) {
            Employee searchedStaff = Repository.EMPLOYEE.get(name);
            searchList.add(searchedStaff);
        }
        return searchList;
    }
    
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
    
    //method to promote staff
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
                    break;
                default:
                    break;
            }
        }
        Repository.persistData(FileType.EMPLOYEE);
        Repository.persistData(FileType.BRANCH);
        return true;
    }
    
    
    //transfer staff to other branch
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
                      //once transfer successfully, must increase both number of staff and numeber of employee
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
                //once transfer successfully, must increase both number of staff and numeber of employee
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
    
   
    
    //return true when open successfully
    public static boolean openBranch(String newBranch, String location, int staffQuota, int numberOfStaff) {
    	//number of staff, manager and so on are 0 when the branch is first created
    	Branch branch = new Branch(newBranch, location, staffQuota, 0, 0, 0, 0);
    	Repository.BRANCH.put(branch.getName(), branch);
    	Repository.persistData(FileType.BRANCH);
    	return true;
    }
    
    //use if-else statement to check if the branch exist
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
    
	public static void assignManager(String name, String password, String branch, EmployeePosition position, EmployeeGender gender, int age, String loginId) {
		addStaffAccount(name,password,branch, position, gender, age, loginId);
	}
	
    public static void initializeDummyBranchInfo() {
    	openBranch("NTU", "North Spine Plaza", 8, 1);
    	openBranch("JE", "Jurong East", 11, 1);
    	openBranch("JP", "Jurong Point", 15, 1);
     }
    
    public static void initializeDummyEmployee() {
    	addStaffAccount("kumar Blackmore", "password", "NTU", EmployeePosition.STAFF, EmployeeGender.MALE, 32, "kumarB");
    	addStaffAccount("Alexei", "password", "NTU", EmployeePosition.MANAGER, EmployeeGender.MALE, 25, "Alexei");
    	addStaffAccount("Tom Chan", "password", "JP", EmployeePosition.MANAGER,EmployeeGender.MALE, 56, "TomC");
    	addStaffAccount("Alica Ang", "password", "JE", EmployeePosition.MANAGER, EmployeeGender.FEMALE, 27, "AlicaA");
    	addStaffAccount("Mary lee", "password", "JE", EmployeePosition.STAFF, EmployeeGender.FEMALE, 44, "MaryL");
    	addStaffAccount("Justin Loh", "password", "JP", EmployeePosition.STAFF, EmployeeGender.MALE, 49, "JustinL");
    }
    
    public static void initializePaymentMethod() {
    	addPaymentMethod("Cash");
    	addPaymentMethod("Paynow");
    	addPaymentMethod("Paywave");
     }
    
}
