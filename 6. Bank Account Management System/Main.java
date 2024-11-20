import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


class BankBranch {
    private String bankCode;
    private String bankName;
    private String branchCode;
    private String branchName;
    private String ifscCode;
    private String bankNum;

    // A static list to store all branches
    private static List<BankBranch> branches = new ArrayList<>();

    public BankBranch(String bankCode, String bankName, String branchCode, String branchName, String ifscCode) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.bankNum = "01";
        branches.add(this);
    }
    
    /** 
     * @return BankNumber
     */
    public String getBankNum() {
        return bankNum;
    }

    /** 
     * @return BankCode
     */
    public String getBankCode() {
        return bankCode;
    }

    /** 
     * @return BankName
     */
    public String getBankName() {
        return bankName;
    }

    /** 
     * @return BranchCode
     */
    public String getBranchCode() {
        return branchCode;
    }

    /** 
     * @return BranchName
     */
    public String getBranchName() {
        return branchName;
    }

    /** 
     * @return IFSC-code
     */
    public String getIfscCode() {
        return ifscCode;
    }

    // Static method to display all available branches
    public static void displayBranches() {
        for (int i = 0; i < branches.size(); i++) {
            System.out.println((i + 1) + ". " + branches.get(i).getBranchName() + " (IFSC: " + branches.get(i).getIfscCode() + ")");
        }
    }

    // Static method to get the total count of branches
    public static int getBranchCount() {
        return branches.size();
    }

    // Static method to get a branch by its index
    public static BankBranch getBranchByIndex(int index) {
        if (index >= 0 && index < branches.size()) {
            return branches.get(index);
        } else {
            throw new IllegalArgumentException("Invalid branch index. Please try again.");
        }
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
    private BigDecimal amount;
    private String accountNumber;
    private String IFSC;
    private BankBranch branchDetails;

    public Account(String accountHolder, String amount, AccountType accountType, String phone, String email, BankBranch branchDetails) {
        this.accountHolder = accountHolder;
        this.amount = new BigDecimal(amount);
        this.amount = this.amount.setScale(2, RoundingMode.HALF_UP);
        this.accountType = accountType;
        this.phone = phone;
        this.email = email;
        this.branchDetails = branchDetails;
    }
    
    public String getAmount() {
        return amount.toString();
    }

    public void addAmount(String amount) {
        BigDecimal addAmount = new BigDecimal(amount);
        this.amount = this.amount.add(addAmount);
    }

    public boolean subtractAmount(String amount) {
        BigDecimal subAmount = new BigDecimal(amount);
        // Minimum balance requirement (can be different for account types)
        BigDecimal minimumBalance = new BigDecimal(100);
    
        // Check if the subAmount is valid (greater than zero)
        if (subAmount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("❌ Error: Amount to withdraw must be greater than zero.");
            return false;
        }
    
        // Check if sufficient balance exists
        if (this.amount.subtract(subAmount).compareTo(minimumBalance) < 0) {
            System.out.println("❌ Error: Insufficient balance. Your balance cannot drop below $" + minimumBalance + ".");
            return false;
        }
    
        // Perform the subtraction
        this.amount = this.amount.subtract(subAmount).setScale(2, RoundingMode.HALF_UP);
        System.out.println("✅ Success: $" + subAmount + " has been withdrawn.");
        System.out.println("💰 Remaining balance: $" + this.amount);
        return true;
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

    /** 
     * Returns the valid string of the user name, email, amount, account and phone
     * 
     * @param scanner object of the Scanner class to get input from user
     * @param prompt string to display the message to the user
     * @param match string used to display a error message based on the string(name/email/phone/amount/account)
     * @param length used to display a error message based on the size
     * @return the valid String
     */
    public static String getValidString(Scanner scanner, String prompt, String match, int length) {
        Pattern emailPattern = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        String namePattern = "^[a-zA-Z\\s]+";
        String phonePattern = "[0-9]+";
        String amountPattern = "^(\\d+)(\\.\\d{1,2})?$";
        String accountPattern = "^[0-9]{2}-[0-9]{3}-[0-9]{6}-[1-4]$";

        while (true) {
            System.out.print(prompt);
            try {
                String string = scanner.nextLine();

                if (match.equals("name") && !string.toLowerCase().matches(namePattern)) throw new Exception("\n⚠️ Invalid name. Please enter a valid name without numbers or special characters.\n");
                if (match.equals("name") && string.length() < 2) throw new Exception("\n⚠️ Name is too short. Please enter a name with at least 2 characters.\n");
                if (match.equals("name") && string.length() > 50) throw new Exception("\n⚠️⚠️ Name is too long. Please enter a name with no more than 50 characters.\n");
                if (match.equals("email") && !emailPattern.matcher(string).matches()) throw new Exception("\n⚠️ Invalid email format. Please enter a valid email, e.g., example@domain.com.\n");
                if (match.equals("phone") && !string.matches(phonePattern)) throw new Exception("\n⚠️ Invalid phone number. Please enter only numbers.\n");
                if (match.equals("phone") && string.length() != length) throw new Exception("\n⚠️ Phone number should be exactly 10 digits. Please enter a valid number.\n");
                if (match.equals("amount") && !string.matches(amountPattern)) throw new Exception("\n⚠️ Invalid amount entered. Please enter a valid value.\n");
                if (match.equals("amount") && string.startsWith("-")) throw new Exception("\n⚠️ \"Error: Deposit amount cannot be negative. Please enter a valid amount.\"\n");
                if (match.equals("account") && !string.matches(accountPattern)) throw new Exception("\n⚠️ Invalid account number entered. Please enter a valid account number.\n");
                
                return string;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    /** 
     * Returns the valid number based on the min and max and also solve common errors.
     * 
     * @param scanner object of the Scanner class to get input from user
     * @param prompt string to display the message to the user
     * @param min int used to check the number, like the given number is not less than min.
     * @param max int used to check the number, like the given number is not greater than max.
     * @return the valid Number
     */
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

    // Static method to print account type details
    public static void displayAccountTypeDetails() {
        System.out.println("\n📘 Account Type Details:");
        System.out.println("1. SAVINGS - Ideal for individuals to save money, interest rates apply.");
        System.out.println("2. CHECKING - For frequent transactions, no interest is provided.");
        System.out.println("3. FIXED_DEPOSIT - Lock-in period with higher interest rates.");
        System.out.println("4. RECURRING_DEPOSIT - Regular deposits for a specified tenure with interest.");
        System.out.println();
    }

    public static AccountType getAccountType(int choice) {
        switch (choice) {
            case 1:
                return AccountType.SAVINGS;
            case 2:
                return AccountType.CHECKING;
            case 3:
                return AccountType.FIXED_DEPOSIT;
            case 4:
                return AccountType.RECURRING_DEPOSIT;
            default:
                throw new IllegalArgumentException("Invalid account type selected.");
        }
    }

    public void createAccountNumber() {
        String accountPattern = "^[0-9]{2}-[0-9]{3}-[0-9]{6}-[1-4]$";

        String BANK_CODE = String.valueOf(branchDetails.getBankNum());
        String BRANCH_CODE = String.valueOf(branchDetails.getBranchCode().substring(2));
        String CUSTOMER_NUMBER = String.valueOf(createCustomerNumber());
        String ACCOUNT_TYPE_CODE = String.valueOf(accountType.getAccountTypeCode());
        
        accountNumber = BANK_CODE + "-" + BRANCH_CODE + "-" + CUSTOMER_NUMBER + "-" + ACCOUNT_TYPE_CODE;
        if (!accountNumber.matches(accountPattern)) {
            System.out.println("Error creating account. Please ensure all details are entered correctly.");
            return;
        }
        System.out.println("\n🎉 Account created successfully! Your account number is " + accountNumber + ".\n");
    }

    private int createCustomerNumber() {
        return (int)Math.floor(Math.random() * 999999) + 100000;
    }

    public void accountDetails() {
        System.out.println("\nAccount Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: $" + amount + "\n");
    }
}

class Bank {
    // SBI Bank Code
    // private final int SBI_BANK_CODE = 20;
    // Create a HashMap to store branches with IFSC code as the key
    private static HashMap<String, BankBranch> branches = new HashMap<>();
    // Create a HashMap to store accounts with Account Number code as the key
    private static HashMap<String, Account> accounts = new HashMap<>();
    private static String bankName = "SBI";

    public static void menu(Scanner scanner) {
        int temp = 0;
        while (true) {
            if (temp == 0) {
                // Greeting message
                System.out.println("\n🏦 Welcome to +" + bankName + " Bank Account Management System!\n");
                System.out.println("Please select an option to get started.\n");
                temp++;
            }

            displayDetails();
            int choice = Account.getValidNumber(scanner, "\nEnter your choice: ", 1, 6);
            if (choice == 6) {
                System.out.println("");
                String yesOrNo = getValidYesOrNo(scanner, "Are you sure you want to exit? (yes/no): ");
                if (yesOrNo.equalsIgnoreCase("yes")) {
                    System.out.println("\nThank you for using " + bankName + " Bank Management System! Have a great day! 👋\n");
                    break;
                } else if (yesOrNo.equalsIgnoreCase("no")) {
                    System.out.println("\nOperation canceled. Returning to the main menu.\n");
                } else {
                    System.out.println("⚠️ Invalid input. Please enter [yes|no] only.");
                }
            } else action(choice, scanner);
        }
    }

    private static void displayDetails() {
        // Diplay details of available options to help the customer decide
        System.out.println("1. Create a new account");
        System.out.println("2. View account details");
        System.out.println("3. Deposit funds");
        System.out.println("4. Withdraw funds");
        System.out.println("5. Transfer funds");
        System.out.println("6. Exit");
    }

    public static void addBranchDetails() {
        // Add some SBI branch details
        branches.put("SBIN0001234", new BankBranch("SBIN", "State Bank of India", "01234", "Mumbai Main", "SBIN0001234"));
        branches.put("SBIN0005678", new BankBranch("SBIN", "State Bank of India", "05678", "Delhi Central", "SBIN0005678"));
        branches.put("SBIN0009101", new BankBranch("SBIN", "State Bank of India", "09101", "Bangalore North", "SBIN0009101"));
        branches.put("SBIN0001122", new BankBranch("SBIN", "State Bank of India", "01122", "Hyderabad West", "SBIN0001122"));
        branches.put("SBIN0003344", new BankBranch("SBIN", "State Bank of India", "03344", "Chennai South", "SBIN0003344"));
    }

    private static void createNewAccount(Scanner scanner) {
        System.out.println("\n\"✨ Creating a New Bank Account...\"\n");

        Map<String, String> accountDetails = getAccountDetailsFromUser(scanner);

        Account.AccountType accountType = getAccountTypeFromUser(scanner);

        BankBranch branch = selectBranch(scanner);

        Account newAccount = initializeAccount(accountDetails, accountType, branch);

        accounts.put(newAccount.getAccountNumber(), newAccount);

        System.out.println("\n🎉 Account created successfully!");
    }

    private static Map<String, String> getAccountDetailsFromUser(Scanner scanner) {
        Map<String, String> details = new HashMap<>();
        details.put("name", Account.getValidString(scanner, "Enter account holder’s name: ", "name", 0));
        details.put("amount", Account.getValidString(scanner, "Enter initial deposit amount: ", "amount", 0));
        details.put("phone", Account.getValidString(scanner, "Enter your phone number: ", "phone", 10));
        details.put("email", Account.getValidString(scanner, "Enter your email: ", "email", 0));
        return details;
    }    

    private static Account.AccountType getAccountTypeFromUser(Scanner scanner) {
        String showDetails = getValidYesOrNo(scanner, "Do you want to see account type details? (yes/no): ");
        if (showDetails.equalsIgnoreCase("yes")) {
            Account.displayAccountTypeDetails();
        }
        int choice = Account.getValidNumber(scanner, "Please select the type of account (1-4): ", 1, 4);
        return Account.getAccountType(choice);
    }

    private static String getValidYesOrNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().toLowerCase();
                if (!input.equals("yes") && !input.equals("no")) {
                    throw new IllegalArgumentException("Please enter 'yes' or 'no'.");
                }
                return input;
            } catch (Exception e) {
                System.out.println("⚠️ " + e.getMessage());
            }
        }
    }
    
    private static BankBranch selectBranch(Scanner scanner) {
        System.out.println("\nAvailable Branches:");
        BankBranch.displayBranches(); // Assume this method lists all branches
        int branchChoice = Account.getValidNumber(scanner, "Select a branch by its number: ", 1, BankBranch.getBranchCount());
        return BankBranch.getBranchByIndex(branchChoice - 1);
    }

    private static Account initializeAccount(Map<String, String> details, Account.AccountType accountType, BankBranch branch) {
        String name = details.get("name");
        String amount = details.get("amount");
        String phone = details.get("phone");
        String email = details.get("email");
    
        // Create and initialize the account
        Account newAccount = new Account(name, amount, accountType, phone, email, branch);
        newAccount.createAccountNumber();
        return newAccount;
    }
    
    private static void action(int num, Scanner scanner) {
        if (num == 1) createNewAccount(scanner);
        else if (num == 2) viewAccountDetails(scanner);
        else if (num == 3) depositFunds(scanner);
        else if (num == 4) withdrawFunds(scanner);
        else if (num == 5) transferFunds(scanner);
    }

    private static boolean checkAccount(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    private static void viewAccountDetails(Scanner scanner) {
        System.out.println("\n🔍 View Account Details");
        String accountNumber = Account.getValidString(scanner, "Enter the account number to view details: ", "account", 0);
        if (checkAccount(accountNumber)) accounts.get(accountNumber).accountDetails();
        else System.out.println("\nAccount not found. Please check the account number and try again.\n");
    }

    private static void depositFunds(Scanner scanner) {
        System.out.println("\n💵 Deposit Funds");
        String accountNumber = Account.getValidString(scanner, "Enter account number to deposit funds into: ", "account", 0);
        if (checkAccount(accountNumber)) {
            Account account = accounts.get(accountNumber);
            String amount = Account.getValidString(scanner, "Enter amount to deposit: ", "amount", 0);
            account.addAmount(amount);
            System.out.println("Deposit successful! New balance: $" + account.getAmount() + "\n");
        } else System.out.println("\nAccount not found. Please check the account number and try again.\n");
    }

    private static void withdrawFunds(Scanner scanner) {
        System.out.println("\n💸 Withdraw Funds");
        String accountNumber = Account.getValidString(scanner, "Enter account number to withdraw funds from: ", "account", 0);
        if (checkAccount(accountNumber)) {
            Account account = accounts.get(accountNumber);
            String amount = Account.getValidString(scanner, "Enter amount to withdraw: ", "amount", 0);
            if (account.subtractAmount(amount)) {
                System.out.println("Withdrawal successful! New balance: $" + account.getAmount());
            }
        }
    }

    private static void transferFunds(Scanner scanner) {
        System.out.println("\n💸 Transfer Funds");
        String sender = Account.getValidString(scanner, "Enter your account number (sender): ", "account", 0);
        String receiver = Account.getValidString(scanner, "Enter recipient’s account number: ", "account", 0);

        if (checkAccount(sender) && checkAccount(receiver)) {
            Account account1 = accounts.get(sender);
            Account account2 = accounts.get(receiver);
            String amount = Account.getValidString(scanner, "Enter the amount to transfer: ", "amount", 0);
            // if (Integer.parseInt(account1.getAmount()) > Integer.parseInt(amount)) {    
                if (account1.subtractAmount(amount)) {
                    account2.addAmount(amount);
                    System.out.println("\nTransfer successful! New balance: $" + account1.getAmount() + "\n");
                } 
                // else {
                //     System.out.println("\n⚠️ An unexpected error occurred. Please try again later.\n");
                // }
            // } else {
            //     System.out.println("\nError: Insufficient funds. Transfer not completed.\n");
            // }
        } else {
            if (!checkAccount(sender)) System.out.println("Error: Invalid sender account number. Please verify and try again.");
            else System.out.println("Error: Invalid recipient account number. Please verify and try again.");
        }
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
