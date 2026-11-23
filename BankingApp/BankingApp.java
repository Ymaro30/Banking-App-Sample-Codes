package BankingApp;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BankingApp {
    private static UserDAO userDAO = new UserDAO();
    private static Scanner scanner = new Scanner(System.in);
    private static User loggedInUser = null;
     static boolean running = true; 
     static int input;

    public static void main(String[] args) {
                System.out.println();
                System.out.println("==============================================================================");
                System.out.println("I                                                                            I");
                System.out.println("I          ---------Welcome to Java Banking System---------                  I");
                System.out.println("I                                                                            I");
                System.out.println("==============================================================================\n");


        while (running) {
            if (loggedInUser == null) {
                showLoginMenu();
            } else {
                showDashboardMenu();
            }
        }
    }

    private static void showLoginMenu() {
                System.out.println();
                System.out.println("==============================================================================");
                System.out.println("I                                                                            I");
                System.out.println("I         === Banking APP LOGIN ===                                          I");
                System.out.println("I         1. Log in                                                          I");
                System.out.println("I         2. Register New Account                                            I");
                System.out.println("I         3. Exit                                                            I");
                System.out.print("          Choose an option: ");
                input = getIntInput();
                userAttempts();
        }
        
    
    private static int getIntInput() {
        
        try {
            int scan = scanner.nextByte();
            if (scan >=1 && scan <=3) {
            input = scan;
            } else { System.out.print("          Invalid input. Please choose between 1 and 3 only."); 
                    }

        } catch (InputMismatchException e) { // Clears the invalid double/text from the scanner buffer
                System.out.println();
                System.out.print("          Invalid input. Please enter a number of your choice. ");
                    scanner.next(); 
                       
            }
            return input;
    }


            static   byte attempts = 3;
    private static void userAttempts(){
        switch (input) {
            case 1:
                
                while(attempts > 0 ){
                System.out.println();
                System.out.print("          Enter your Mobile Number (e.g., 09876543210): ");

                    String accNum = scanner.next();
                    System.out.print("          Enter Password: ");
                    String password = scanner.next();
          
                    User acc = userDAO.getUserByMobile(accNum);

                        if (acc != null && acc.authenticate(password)) {
                        loggedInUser = acc;
                        System.out.println("          Welcome back, " + acc.getOwnerName() + "!\n");
                       attempts = 3;
                        } else if(attempts > 1) {
                        --attempts;
                    System.out.println("          Invalid mobile number or PIN. Attempts remaining: " + attempts);
                        
                        chance();
                       
                        } else {
                System.out.println("==============================================================================");
                System.out.println("I                                                                            I");
                System.out.println("I          Too many failed login attempts. Access locked                     I");
                System.out.println("I                                                                            I");
                System.out.println("==============================================================================\n");
                            System.exit(0);
                        }
                        break;
                    }
                break;
            case 2:
                createAccount(scanner);
                break;
            case 3:
                System.out.println("==============================================================================");
                System.out.println("I                                                                            I");
                System.out.println("I          Thank you for using BankingApp. Goodbye!                          I");
                System.out.println("I                                                                            I");
                System.out.println("==============================================================================\n");
                System.exit(0);
                 break;
             default:
                 System.out.println(" ");
        }
    }

    private static void chance(){
                System.out.println();
                System.out.println("==============================================================================");
                System.out.println("I                                                                            I");
                System.out.println("I         1. For another attemp.                                             I");
                System.out.println("I         2. For Register new acount.                                        I");
                System.out.print("          Choose an option. ");
                int chances = getIntInput();
                switch (chances) {
                    case 1: 
                        userAttempts();
                        break;
                    case 2:
                        createAccount(scanner);
                        break;
                }
    }


    private static void showDashboardMenu() {    
        printMenu(); 
        
            boolean exit = false;

            while (!exit) {
                
                try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                    switch (choice) {
                      case 1:
                        checkBalance();
                        break;
                       case 2:
                        cashIn();
                        break;
                      case 3:
                        withdraw();
                        break;
                      case 4:
                        transferMoney();
                        break;
                      case 5:
                        viewTransactionLogs();
                        break;
                      case 6:
                        exit = true;
                        loggedInUser = null;
                        System.out.println("==============================================================================");
                        System.out.println("I                                                                            I");
                        System.out.println("I          Thank you for using the banking app. Goodbye!                     I");
                        System.out.println("I                                                                            I");
                        System.out.println("==============================================================================\n");
                        break;
                      default:
                        System.out.println("==============================================================================");
                        System.out.println("I                                                                            I");
                        System.out.println("I          Invalid option. Please try again.                                 I");
                        System.out.println("I                                                                            I");
                        System.out.println("==============================================================================\n");
                    }
                } catch (InputMismatchException e) { 
                        System.out.print("           Invalid input. Please enter only the number of your choice: ");
                        scanner.next(); // Critical : Clears the invalid double/text from the scanner buffer
                        }
                break;
            }
    }

    
    private static void printMenu() {
            System.out.println();
            System.out.println("==============================================================================");
            System.out.println("I                                                                            I");
            System.out.println("I          --- MAIN MENU ---                                                 I");
            System.out.println("I                                                                            I");
            System.out.println("I          1. Check Balance                                                  I");
            System.out.println("I          2. Cash In                                                        I");
            System.out.println("I          3. Withdraw                                                       I");
            System.out.println("I          4. Transfer Money                                                 I");
            System.out.println("I          5. View Transaction Logs                                          I");
            System.out.println("I          6. Exit                                                           I");
            System.out.print("           Choose an option: ");
        
    }


    private static void createAccount(Scanner scanner) {
            System.out.println();
            System.out.println("==============================================================================");
            System.out.println("I                                                                            I");
            System.out.println("I          === Registration Form ===                                         I");
            System.out.println("I                                                                            I");
            System.out.print("           Enter your mobile number: ");
            String mobileNum = scanner.next();
            boolean key = true;
            User user = userDAO.getUserByMobile(mobileNum);
      while (key) {
        if (user != null ) {
                        System.out.print("           PLease double check your mobile number, and enter it again: ");
            mobileNum = scanner.next();
        } key = false;
      }
            System.out.print("           Choose a password: ");
            String password = scanner.next();
            scanner.nextLine(); // consume newline
            System.out.print("           Enter full name: ");
            String fullName = scanner.nextLine();
            System.out.print("           Enter initial deposit ($): ");
            double initialDeposit = scanner.nextDouble();

            registerUser(mobileNum, password, fullName, initialDeposit);
                        System.out.println("           Registration complete! You can now log-in Ma'am/Sir " + fullName.toUpperCase());
    }


    private static void registerUser(String mobileNumber, String password, String fullName, double initialBalance) {
        
            Transaction t = new Transaction(mobileNumber, password, initialBalance, fullName, LocalDateTime.now());
            User user = new User(mobileNumber, password, fullName, initialBalance);
            
            userDAO.insertUsers(user); 
            userDAO.addTransactionRecord(t); 

            User user2 = userDAO.getUserByMobile(mobileNumber);
            loggedInUser = user2;
    }


    private static void checkBalance() {
            // Refresh from DB
            loggedInUser = userDAO.getUserByMobile(loggedInUser.getMobileNumber());
            System.out.printf("           Current Balance: PHP %.2f\n", loggedInUser.getBalance() );
            System.out.println();
    }


    private static void cashIn() {
                        System.out.println();
                        System.out.println("==============================================================================");
                        System.out.println("I                                                                            I");
                        System.out.print("           Enter cash-in amount (PHP): ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("           Amount must be greater than zero.");
        }
            loggedInUser.deposit(amount);
            userDAO.updateBalance(loggedInUser.getMobileNumber(), loggedInUser.getBalance());

            Transaction t = new Transaction(loggedInUser.getMobileNumber(), loggedInUser.getPin(), loggedInUser.getFullName(), amount, "CASH-IN", "Loaded funds into account", LocalDateTime.now());
            userDAO.addTransactionRecord(t); 
            System.out.printf("           Cash-in successful! New Balance: PHP %.2f\n", loggedInUser.getBalance());
            System.out.println();
    }


    private static  void withdraw() {
                        System.out.println();
                        System.out.println("==============================================================================");
                        System.out.println("I                                                                            I");
                        System.out.print("           Enter amount to withdraw (PHP): ");
        
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

        if (amount > loggedInUser.balance()) {
            System.out.println("           Insufficient amount of balance.");
            return;
        }
            loggedInUser.withdraw(amount);
            userDAO.updateBalance(loggedInUser.getMobileNumber(), loggedInUser.getBalance());

            Transaction t = new Transaction(loggedInUser.getMobileNumber(), loggedInUser.getPin(), loggedInUser.getFullName(), amount, "CASH-OUT", "Deducted funds into account", LocalDateTime.now());
            userDAO.addTransactionRecord(t); 
            System.out.printf("           Withdraw successful! New Balance: PHP %.2f\n", loggedInUser.getBalance());
    }



    private static void transferMoney() {
                        System.out.println();
                        System.out.println("==============================================================================");
                        System.out.println("I                                                                            I");
            System.out.print("           Enter recipient's mobile number: ");
            String recipientMobile = scanner.nextLine();

        if (recipientMobile.equals(loggedInUser.getMobileNumber())) {
            System.out.println("           You cannot transfer money to your own account.");
            return;
        }

            User recipient = userDAO.getUserByMobile(recipientMobile);
        if (recipient == null) {
            System.out.println("           Recipient mobile number not found.");
            return;
        }

            System.out.print("           Enter amount to transfer (PHP): ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

        if (amount <= 0) {
            System.out.println("           Transfer amount must be greater than zero.");
            return;
        }

        if (!loggedInUser.withdraw(amount)) {
            System.out.println("           Insufficient balance for this transfer.");
            return;
        }

            recipient.deposit(amount);

            // Update both balances in DB
            userDAO.updateBalance(loggedInUser.getMobileNumber(), loggedInUser.getBalance());
            userDAO.updateBalance(recipient.getMobileNumber(), recipient.getBalance());

            // Record transactions for sender and receiver
            userDAO.addTransactionRecord(new Transaction(loggedInUser.getMobileNumber(), "TRANSFER-OUT", amount, "Transferred to " + recipient.getFullName(), LocalDateTime.now()));
            userDAO.addTransactionRecord(new Transaction(recipient.getMobileNumber(), "TRANSFER-IN", amount, "Received from " + loggedInUser.getFullName(), LocalDateTime.now()));

            System.out.printf("           Transfer of PHP %.2f to %s was successful!\n", amount, recipient.getFullName()); //the %.2f is == amount, likewise
            System.out.println("           Your current balance is " + loggedInUser.getBalance());
                
    }

    private static void viewTransactionLogs() {
            System.out.println();
            System.out.println("==============================================================================");
            System.out.println("I                                                                            I");
            List<Transaction> logs = userDAO.getTransactionsByUser(loggedInUser.getMobileNumber());
            System.out.println("I          --- TRANSACTION HISTORY ---                                       I");
        if (logs.isEmpty()) {
            System.out.println("I          No transactions found.                                            I");
            return;
        }
        for (Transaction t : logs) {
            System.out.printf("          [%s] %s: PHP %.2f | Details: %s\n",
            t.getTransactionDate(), t.getTransactionType(), t.getAmount(), t.getDetails());
        }
    }
}