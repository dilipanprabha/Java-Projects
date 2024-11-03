import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;


class BankBranch {
    private String bankCode;
    private String bankName;
    private String branchCode;
    private String branchName;
    private String ifscCode;
    private String bankNum = "01";

    public BankBranch(String bankCode, String bankName, String branchCode, String branchName, String ifscCode) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
    }

    public String getBankNum() {
        return bankNum;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }
}

class Account {
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
    
    private AccountType accountType;
    private String accountHolder;
    private String phone;
    private String email;
    private String accountNumber;
    private String IFSC;
    private BankBranch branchDetails;

    public Account(String accountHolder, AccountType accountType, String phone, String email, BankBranch branchDetails) {
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.phone = phone;
        this.email = email;
        this.branchDetails = branchDetails;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getIFSC() {
        return IFSC;
    }
    
    public String getAccountType() {
        return accountType.toString();
    }

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

    public static boolean checkValidString(String str, String match) {
        String REGEX = (match.equals("ifsc")) ? "^[A-Z]{4}0[0-9]{6}$" : "^[a-zA-Z\\s]+";

        if (str.matches(REGEX)) return true;
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
        String accountPattern = "^[0-9]{2}-[0-9]{3}-[0-9]{6}-[1-4]$";

        String BANK_CODE = String.valueOf(branchDetails.getBankNum());
        String BRANCH_CODE = String.valueOf(branchDetails.getBranchCode().substring(2));
        String CUSTOMER_NUMBER = String.valueOf(createCustomerNumber());
        String ACCOUNT_TYPE_CODE = String.valueOf(accountType.getAccountTypeCode());
        
        accountNumber = BANK_CODE + "-" + BRANCH_CODE + "-" + CUSTOMER_NUMBER + "-" + ACCOUNT_TYPE_CODE;
        if (!accountNumber.matches(accountPattern)) {
            System.out.println(accountNumber);
            System.out.println("⚠️ An unexpected error occurred. Please try again later.");
            return;
        }
        System.out.println("🎉 Account created successfully! Your account number is " + accountNumber + ".");
    }

    private int createCustomerNumber() {
        return (int)Math.floor(Math.random() * 999999) + 100000;
    }

}

class Bank {
    // SBI Bank Code
    private final int SBI_BANK_CODE = 20;
    // Create a HashMap to store branches with IFSC code as the key
    private static HashMap<String, BankBranch> branches = new HashMap<>();
    // Create a HashMap to store accounts with Account Number code as the key
    private static HashMap<String, Account> accounts = new HashMap<>();
    private static String bankName;
    private static Account account;

    public static void menu(Scanner scanner) {
        // Greeting message
        System.out.println("\n🏦 Welcome to Devendra Kula Vellalar Bank Account Management System!🇧🇫\n");
        System.out.println("Please select an option to get started.\n");

        // Diplay details of available options to help the customer decide
        System.out.println("1. Create a new account");
        System.out.println("2. View account details");
        System.out.println("3. Deposit funds");
        System.out.println("4. Withdraw funds");
        System.out.println("5. Transfer funds");
        System.out.println("6. Exit");
        int choice = Account.getValidNumber(scanner, "\nEnter your choice: ", 1, 6);
        action(choice, scanner);
    }

    public static void addBranchDetails() {
        // Add some SBI branch details
        branches.put("SBIN0001234", new BankBranch("SBIN", "State Bank of India", "01234", "Mumbai Main", "SBIN0001234"));
        branches.put("SBIN0005678", new BankBranch("SBIN", "State Bank of India", "05678", "Delhi Central", "SBIN0005678"));
        branches.put("SBIN0009101", new BankBranch("SBIN", "State Bank of India", "09101", "Bangalore North", "SBIN0009101"));
        branches.put("SBIN0001122", new BankBranch("SBIN", "State Bank of India", "01122", "Hyderabad West", "SBIN0001122"));
        branches.put("SBIN0003344", new BankBranch("SBIN", "State Bank of India", "03344", "Chennai South", "SBIN0003344"));
    }

    private static BankBranch selectBranch(Scanner scanner) {
        Collection<BankBranch> bankBranches = branches.values();

        int i = 1;
        HashMap<Integer, String> branchSelectionMap = new HashMap<>();
        System.out.println("\nAvailable branches for SBI:");
        for (BankBranch bankBranch : bankBranches) {
            System.out.println(i + ". Bank Name: " + bankBranch.getBankName() + "; Bank Branch: " + bankBranch.getBranchName());
            branchSelectionMap.put(i, bankBranch.getIfscCode());
            i++;
        }
        Integer choice = Account.getValidNumber(scanner, "\nPlease choose a number from the list above:", 1, branchSelectionMap.size());
        return branches.get(branchSelectionMap.get(choice));
        
    }

    private static void createNewAccount(Scanner scanner) {
        String name = Account.getValidString(scanner, "Enter your name: ", "name", 0);
        String email = Account.getValidString(scanner, "Enter your email: ", "email", 0);
        String phone = Account.getValidString(scanner, "Enter your phone number: ", "phone", 10);
        Account.accountTypeDetails();
        int choice = Account.getValidNumber(scanner, "Please enter your choice to select an account type: ", 1, 4);
        Account.AccountType accountType = Account.getAccountType(choice);
        BankBranch branch = selectBranch(scanner);
        Account user = new Account(name, accountType, phone, email, branch);
        user.createAccountNumber();
        accounts.put(user.getAccountNumber(), user);
        account = user;
    }

    private static void action(int num, Scanner scanner) {
        if (num == 1) createNewAccount(scanner);
        System.out.println(branches);
        System.out.println(accounts.get(account.getAccountNumber()));
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank.addBranchDetails();
        Bank.menu(scanner);
        scanner.close();
    }
}
