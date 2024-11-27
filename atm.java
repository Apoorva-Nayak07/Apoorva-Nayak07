import java.util.ArrayList;
import java.util.Scanner;

public class atm {
    
    // ATM User Data (In a real system, this would be in a database)
    private static double accountBalance = 1000.00; // Initial account balance
    private static String userPin = "1234";  // Default user PIN
    private static final ArrayList<String> transactionHistory = new ArrayList<>(); // Store transaction history
    
    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Simulate ATM operations
        boolean sessionActive = true;
        
        System.out.println("Welcome to the ATM!");
        
        // User authentication
        if (authenticateUser()) {
            while (sessionActive) {
                showMenu();
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1 -> checkBalance();
                    case 2 -> withdrawCash();
                    case 3 -> depositCash();
                    case 4 -> changePin();
                    case 5 -> viewTransactionHistory();
                    case 6 -> {
                        System.out.println("Exiting. Thank you for using the ATM.");
                        sessionActive = false;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Authentication failed. Please try again later.");
        }
    }

    // Function to authenticate the user with PIN
    private static boolean authenticateUser() {
        System.out.print("Please enter your PIN: ");
        String enteredPin = scanner.next();
        
        // Check if the entered PIN matches the stored PIN
        return enteredPin.equals(userPin);
    }

    // Function to display ATM menu options
    private static void showMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Account Balance");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Deposit Cash");
        System.out.println("4. Change PIN");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    // Function to check the account balance
    private static void checkBalance() {
        System.out.println("Your account balance is: $" + accountBalance);
    }

    // Function to withdraw cash
    private static void withdrawCash() {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        // Check if the user has sufficient balance
        if (amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("You have withdrawn $" + amount);
            recordTransaction("Withdrew $" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    // Function to deposit cash
    private static void depositCash() {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        accountBalance += amount;
        System.out.println("You have deposited $" + amount);
        recordTransaction("Deposited $" + amount);
    }

    // Function to change the PIN
    private static void changePin() {
        System.out.print("Enter your current PIN: ");
        String currentPin = scanner.next();
        
        if (currentPin.equals(userPin)) {
            System.out.print("Enter your new PIN: ");
            String newPin = scanner.next();
            userPin = newPin;
            System.out.println("Your PIN has been changed successfully.");
            recordTransaction("Changed PIN");
        } else {
            System.out.println("Incorrect current PIN. Unable to change PIN.");
        }
    }

    // Function to view transaction history
    private static void viewTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Function to record a transaction
    private static void recordTransaction(String transaction) {
        transactionHistory.add(transaction);
    }
}

