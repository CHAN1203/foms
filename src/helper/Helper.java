package helper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
    public static final Scanner sc = new Scanner(System.in);
    
    public static int readInt() {
        while (true) {
            try {
                int userInput = -1;
                userInput = sc.nextInt();
                sc.nextLine(); // Consume newline left-over
                return userInput;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid Input, Enter an integer!");
            }
        }
    }
    
    public static String readString() {
        
        String userInput = sc.nextLine();
        return userInput;
    }
}
