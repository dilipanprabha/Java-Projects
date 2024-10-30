import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

enum AccountType {
    SAVINGS(1),
    CHECKING(2),
    FIXED_DEPOSIT(3),
    RECURRING_DEPOSIT(4);

    private int accountTypeCode;

    private AccountType(int accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public int getAccountTypeCode() {
        return accountTypeCode;
    }
}
/**
 * Account
 */
class Account {

    private final int SBI_BANK_CODE = 20;
    private HashMap<String, Integer> branches = new HashMap<>();
    private String branchName;
    private String accountHolder;
    private String phone;
    private String email;
    private String accountNumber;
    private double balance;
    private String bankName;
    private String IFSC;
    private AccountType accountType;

    public Account(String accountHolder, AccountType accountType, String phone, String email) {
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.phone = phone;
        this.email = email;
    }

    
    /** 
     * Returns the valid string of the user name, email, and phone
     * 
     * @param scanner object of the Scanner class to get input from user
     * @param prompt string to display the message to the user
     * @param match string used to display a error message based on the string(name/email/phone)
     * @param length used to display a error message based on the size
     * @return the valid String
     */
    public static String getValidString(Scanner scanner, String prompt, String match, int length) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);

        final String NAME_REGEX = "^[a-zA-Z\\s]+";

        final String PHONE_REGEX = "[0-9]+";

        while (true) {
            System.out.print(prompt);
            try {
                String string = scanner.nextLine();

                if (match.equals("name") && !string.toLowerCase().matches(NAME_REGEX)) throw new Exception("\n⚠️ Invalid name. Please enter a valid name without numbers or special characters.\n");
                if (match.equals("name") && string.length() < 2) throw new Exception("\n⚠️ Name is too short. Please enter a name with at least 2 characters.\n");
                if (match.equals("name") && string.length() > 50) throw new Exception("\n⚠️⚠️ Name is too long. Please enter a name with no more than 50 characters.\n");
                if (match.equals("email") && !EMAIL_REGEX.matcher(string).matches()) throw new Exception("\n⚠️ Invalid email format. Please enter a valid email, e.g., example@domain.com.\n");
                if (match.equals("phone") && !string.matches(PHONE_REGEX)) throw new Exception("\n⚠️ Invalid phone number. Please enter only numbers.\n");
                if (match.equals("phone") && string.length() != length) throw new Exception("\n⚠️ Phone number should be exactly 10 digits. Please enter a valid number.\n");
                
                return string;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static int getValidNumber(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int number = scanner.nextInt();
                scanner.nextLine();

                if (min > number || max < number) throw new Exception("\n⚠️ Invalid input! Please enter a valid number between " + min + " and " + max + ".\n");

                return number;
            } catch (InputMismatchException e) {
                System.out.println("\n⚠️ Invalid number. Please enter only numbers.\n");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
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

    public static void accountTypeDetails() {
        // Display details of each account type to help the customer decide
        System.out.println("\n1. Savings Account");
        System.out.println("   - Ideal for personal savings with interest.");
        System.out.println("   - Limited transactions per month.");
        System.out.println("   - Minimum balance required: $500.");
        System.out.println();

        System.out.println("2. Checking Account");
        System.out.println("   - Suitable for frequent transactions.");
        System.out.println("   - Ideal for individuals and businesses.");
        System.out.println("   - No interest, but no transaction limits.");
        System.out.println();

        System.out.println("3. Fixed Deposit Account");
        System.out.println("   - Lock your funds for a fixed period to earn higher interest.");
        System.out.println("   - Minimum lock-in period: 6 months.");
        System.out.println("   - Early withdrawal penalties may apply.");
        System.out.println();

        System.out.println("4. Recurring Deposit Account");
        System.out.println("   - Deposit a fixed amount monthly for a set period.");
        System.out.println("   - Ideal for disciplined saving.");
        System.out.println("   - Higher interest rate than savings accounts.");
        System.out.println();

        System.out.println("Please enter the number corresponding to your preferred account type to proceed [1-4].\n");
    }

    public static AccountType getAccountType(int num) {
        if (num == 1) return AccountType.SAVINGS;
        else if (num == 2) return AccountType.CHECKING;
        else if (num == 3) return AccountType.FIXED_DEPOSIT;
        return AccountType.RECURRING_DEPOSIT;
    }

    public void createAccountNumber() {
        final String BANK_CODE = String.valueOf(SBI_BANK_CODE);
        final String BRANCH_CODE = String.valueOf(getBranchCode(branchName));
        final String CUSTOMER_NUMBER = String.valueOf(createCustomerNumber());
        final String ACCOUNT_TYPE_CODE = String.valueOf(accountType.getAccountTypeCode());
        if (BRANCH_CODE.equals("-1")) {
            System.out.println("⚠️ An unexpected error occurred. Please try again later.");
            return;
        }

        accountNumber = BANK_CODE + "-" + BRANCH_CODE + "-" + CUSTOMER_NUMBER + "-" + ACCOUNT_TYPE_CODE;
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

        branches.put(branchName, branchCode);
    }

    public int getBranchCode(String branchName) {
        if (branches.containsKey(branchName)) return branches.get(branchName);
        return -1;
    }

}

/**
 * Bank
 */
public class Bank {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String name = Account.getValidString(scanner, "Enter your name: ", "name", 0);
        String email = Account.getValidString(scanner, "Enter your email: ", "email", 0);
        String phone = Account.getValidString(scanner, "Enter your phone number: ", "phone", 10);
        Account.accountTypeDetails();
        int choice = Account.getValidNumber(scanner, "Please enter your choice to select an account type: ", 1, 4);
        AccountType accountType = Account.getAccountType(choice);
        
        Account person1 = new Account(name, accountType, phone, email);
    }
}


// Account creation function is successfully created. But the problem is branch. We create addBranch without static keyword. So we can't able to add branch without create account. But to create account we want branch name. Yeah we can set addBranch as static. But if we create like this anyone can use addBranch function. That's not a best way. But we've one option, we can create seperate class only for Bank to manage details about Bank.