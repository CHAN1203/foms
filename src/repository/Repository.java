package repository;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import model. *;
import controller. *;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Repository {
    private static final String folder = "data";
    
    public static HashMap<String, Employee> EMPLOYEE = new HashMap<>();
    public static HashMap<String, Branch> BRANCH = new HashMap<>();
    public static HashMap<Integer, Order> ORDER = new HashMap<>(); // <orderID, Order>
    
    //haven't written any serialization part
    public static Set<String> BRANCHES = new HashSet<>();
    public static Set<String> PAYMENT_METHODS = new HashSet<>();
    /**
     * Constructor that reads all the data from the data file during initialization of program.
     */
    
    /**
     * A method to save a particular {@link FileType} into database.
     * @param fileType file type to be saved.
     * @see FileType for the different type of filetypes.
     */
    public static void persistData(FileType fileType) {
        writeSerializedObject(fileType);
    }
    
    public static void readData(FileType fileType) {
    	readSerializedObject(fileType);
    }

    /**
     * A method to save all files into database.
     */
    public static void saveAllFiles() {
    	persistData(FileType.EMPLOYEE);
    	persistData(FileType.BRANCH);
    }

	private static boolean readSerializedObject(FileType fileType) {
        String fileExtension = ".dat";
        String filePath = "./src/repository/" + folder + "/" + fileType.fileName + fileExtension;
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            
            if (!(object instanceof HashMap)) {
                System.out.println(fileType.fileName);
                objectInputStream.close();
                return false;
            }
            
            // Read into database
            if (fileType == FileType.EMPLOYEE) {
                EMPLOYEE = (HashMap<String, Employee>) object;
            } else if(fileType == FileType.BRANCH) {
            	BRANCH = (HashMap<String, Branch>) object;
            }

            objectInputStream.close();
            fileInputStream.close();
            
        } catch (EOFException err) {
            System.out.println("Warning: " + err.getMessage()); //error exception check later with Aarons code
            if (fileType == FileType.EMPLOYEE) {
                EMPLOYEE = new HashMap<String, Employee>();
            } else {
            	
            }
        } catch (IOException err) {
            err.printStackTrace();
            return false;
        } catch (ClassNotFoundException err) {
            err.printStackTrace();
            return false;
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
            return false;
        }
        return true;
    }

    private static boolean writeSerializedObject(FileType fileType) {
        String fileExtension = ".dat";
        String filePath = "./src/repository/" + folder + "/" + fileType.fileName + fileExtension;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            if (fileType == FileType.EMPLOYEE) {
                objectOutputStream.writeObject(EMPLOYEE);
            } else if (fileType == FileType.BRANCH){
            	objectOutputStream.writeObject(BRANCH);
            } 
            objectOutputStream.close();
            fileOutputStream.close();
            return true;
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
            return false;
        }
    }

    /**
     * A method to clear out all the data in database.
     * @return {@code true} if data is cleared successfully.
     */
    public static boolean clearDatabase() {
        // Initialize empty data
        EMPLOYEE = new HashMap<String, Employee>();
        writeSerializedObject(FileType.EMPLOYEE);
        return true;
    }
    
    public static boolean initializeDummyBranch() {
        if (BRANCH.size() != 0) {
            return false;
        }
        AdminController.initializeDummyBranchInfo();
        return true;
    }
    
}