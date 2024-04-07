package repository;
import java.util.HashMap;
import model.Employee;

import enums.EmployeePosition;

import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Repository {
    private static final String folder = "data";
    /**
     * HashMap to contain {@link Employee} objects.
     */
    public static HashMap<String, Employee> Employee = new HashMap<String, Employee>();
    /**
     * HashMap to contain {@link MenuItem} objects.
     */
    public static HashMap<String, MenuItem> MENU_ITEMS = new HashMap<String, MenuItem>();
    
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
                Employee = (HashMap<String, Employee>) object;
            } else {
            	
            }

            objectInputStream.close();
            fileInputStream.close();
            
        } catch (EOFException err) {
            System.out.println("Warning: " + err.getMessage());
            if (fileType == FileType.EMPLOYEE) {
                Employee = new HashMap<String, Employee>();
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
                objectOutputStream.writeObject(Employee);
            }else if(fileType == FileType.CATEGORY){
            	objectOutputStream.writeObject(Category);
            }else if (fileType == FileType.) {
                objectOutputStream.writeObject();
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
        Employee = new HashMap<String, Employee>();
        writeSerializedObject(FileType.EMPLOYEE);
        return true;
    }
}