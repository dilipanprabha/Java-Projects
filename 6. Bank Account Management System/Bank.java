import java.util.Scanner;

/**
 * Account
 */
class Account {
    private String name;
    private String phone;
    private int accountNumber;
    private String email;
    private double balance;
    private String bankName;
    private String branch;
    private String IFSC;
    private String accountType;

    public Account(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public static String getValidString(Scanner scanner, String prompt, String match) {
        String expression = (match.equals("name")) ? "^[a-zA-Z\\s]+" : "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
        while (true) {
            System.out.print(prompt);
            try {
                String name = scanner.nextLine();
                if (name.toLowerCase().matches(expression) && match.equals("name")) throw new Exception("\n⚠️ Invalid name. Please enter a valid name without numbers or special characters.\n");
                if (name.length() < 2 && match.equals("name")) throw new Exception("\n⚠️ Name is too short. Please enter a name with at least 2 characters.\n");
                if (name.length() > 50 && match.equals("name")) throw new Exception("\n⚠️⚠️ Name is too long. Please enter a name with no more than 50 characters.\n");
                if (name.toLowerCase().matches(expression) && match.equals("email")) throw new Exception("\n⚠️ Invalid email format. Please enter a valid email, e.g., example@domain.com.\n");
                return name;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static String getValidNumber(Scanner scanner, String prompt, int length) {
        String expression = "[0-9]+";
        while (true) {
            System.out.print(prompt);
            try {
                String number = scanner.nextLine();
            if (!number.matches(expression)) throw new Exception("\n⚠️ Invalid phone number. Please enter only numbers.\n");
            if (number.length() != length) throw new Exception("\n⚠️ Phone number should be exactly 10 digits. Please enter a valid number.\n");
            return number;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } 
    }
}

/**
 * Bank
 */
public class Bank {

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}