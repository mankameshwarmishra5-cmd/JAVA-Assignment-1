import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    // Display account details
    public void displayDetails() {
        System.out.println("Account Number   : " + accountNumber);
        System.out.println("Account Holder   : " + accountHolderName);
        System.out.println("Current Balance  : $" + balance);
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
}

public class BankingApplication {
    private static final int MAX_ACCOUNTS = 100;
    private static BankAccount[] accounts = new BankAccount[MAX_ACCOUNTS];
    private static int totalAccounts = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    performDeposit(scanner);
                    break;
                case 3:
                    performWithdrawal(scanner);
                    break;
                case 4:
                    viewAccount(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using our banking system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }

        } while (choice != 5);

        scanner.close();
    }

    // Create account
    private static void createAccount(Scanner scanner) {
        if (totalAccounts >= MAX_ACCOUNTS) {
            System.out.println("Cannot create more accounts. Limit reached.");
            return;
        }

        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Deposit Amount: ");
        double initialDeposit = scanner.nextDouble();

        accounts[totalAccounts] = new BankAccount(accNum, name, initialDeposit);
        totalAccounts++;

        System.out.println("Account created successfully!");
    }

    // Deposit money
    private static void performDeposit(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        BankAccount account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter Amount to Deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Withdraw money
    private static void performWithdrawal(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        BankAccount account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter Amount to Withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // View account
    private static void viewAccount(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        BankAccount account = findAccount(accNum);
        if (account != null) {
            account.displayDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    // Find account by account number
    private static BankAccount findAccount(String accountNumber) {
        for (int i = 0; i < totalAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }
}
