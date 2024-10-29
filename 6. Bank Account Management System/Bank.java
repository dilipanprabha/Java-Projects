import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Account
 */
class Account {
    public enum AccountType {
        SAVINGS(1), CHECKING(2), BUSINESS(3);

        private int accountTypeCode;

        private AccountType(int accountTypeCode) {
            this.accountTypeCode = accountTypeCode;
        }

        public int getAccountTypeCode() {
            return accountTypeCode;
        }
    }

    private final int SBI_BANK_CODE = 20;
    private HashMap<String, Integer> branch = new HashMap<>();
    private String accountHolder;
    private String phone;
    private String email;
    private int accountNumber;
    private double balance;
    private String bankName;
    private String IFSC;
    private String accountType;

    public Account(String accountHolder, String phone, String email) {
        this.accountHolder = accountHolder;
        this.phone = phone;
        this.email = email;
    }

    
    /** 
     * Returns the valid string of the user name and email
     * 
     * @param scanner object of the Scanner class to get input from user
     * @param prompt string to display the message to the user
     * @param match string used to display a error message based on the string(name/email)
     * @return the valid String
     */
    public static String getValidString(Scanner scanner, String prompt, String match) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);

        final String NAME_REGEX = "^[a-zA-Z\\s]+";

        while (true) {
            System.out.print(prompt);
            try {
                String name = scanner.nextLine();

                if (!name.toLowerCase().matches(NAME_REGEX) && match.equals("name")) throw new Exception("\n⚠️ Invalid name. Please enter a valid name without numbers or special characters.\n");
                if (name.length() < 2 && match.equals("name")) throw new Exception("\n⚠️ Name is too short. Please enter a name with at least 2 characters.\n");
                if (name.length() > 50 && match.equals("name")) throw new Exception("\n⚠️⚠️ Name is too long. Please enter a name with no more than 50 characters.\n");
                if (!EMAIL_REGEX.matcher(name).matches() && match.equals("email")) throw new Exception("\n⚠️ Invalid email format. Please enter a valid email, e.g., example@domain.com.\n");

                return name;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    /** 
     * Returns the valid string of the user phone
     * 
     * @param scanner object of the Scanner class to get input from user
     * @param prompt string to display the message to the user
     * @param length used to display a error message based on the size
     * @return the valid number
     */
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

    public int createCustomerNumber() {
        return (int)Math.floor(Math.random() * 999999) + 100000;
    }

    public void addBranch(String branchName, int branchCode) {
        if (checkValidString(branchName)) {
            System.out.println("Enter correct branch name");
            return;
        } 
        if (checkValidNumber(branchCode, 3)) {
            System.out.println("Enter correct branch code");
            return;
        } 

        branch.put(branchName, branchCode);
    }

    public static boolean checkValidString(String str) {
        final String NAME_REGEX = "^[a-zA-Z\\s]+";

        if (str.matches(NAME_REGEX)) return true;
        return false;
    }

    public static boolean checkValidNumber(int num, int length) {
        String strNumber = String.valueOf(num);
        if (strNumber.length() == length) return true;
        return false;
    }

    public int getBranchCode(String branchName) {
        return branch.get(branchName);
    }

}

/**
 * Bank
 */
public class Bank {

    public static void main(String[] args) {
    }
}