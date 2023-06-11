//Task 5. Banking system : internship

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited:" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn:" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void checkBalance() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance:" + balance);
    }

    public void transfer(BankAccount recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            System.out.println("Transferred:" + amount + " to " + recipient.accountHolderName);
        } else {
            System.out.println("Insufficient balance for transfer.");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

class BankingSystem {
    private Map<String, BankAccount> accounts;

    public BankingSystem() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolderName) {
        BankAccount account = new BankAccount(accountNumber, accountHolderName);
        accounts.put(accountNumber, account);
        System.out.println("Account created successfully.");
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}

public class bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankingSystem bankingSystem = new BankingSystem();

        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Banking System Menu =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String accountHolderName = scanner.nextLine();
                    bankingSystem.createAccount(accountNumber, accountHolderName);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    BankAccount depositAccount = bankingSystem.getAccount(accountNumber);
                    if (depositAccount != null) {
                        System.out.print("Enter deposit amount:");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    BankAccount withdrawAccount = bankingSystem.getAccount(accountNumber);
                    if (withdrawAccount != null) {
                        System.out.print("Enter withdrawal amount:");
                        double withdrawalAmount = scanner.nextDouble();
                        withdrawAccount.withdraw(withdrawalAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    BankAccount balanceAccount = bankingSystem.getAccount(accountNumber);
                    if (balanceAccount != null) {
                        balanceAccount.checkBalance();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter sender's account number: ");
                    String senderAccountNumber = scanner.nextLine();
                    BankAccount senderAccount = bankingSystem.getAccount(senderAccountNumber);
                    if (senderAccount != null) {
                        System.out.print("Enter recipient's account number: ");
                        String recipientAccountNumber = scanner.nextLine();
                        BankAccount recipientAccount = bankingSystem.getAccount(recipientAccountNumber);
                        if (recipientAccount == null) {
                            System.out.println("Recipient account not found. Creating new account...");
                            System.out.print("Enter recipient's account holder name: ");
                            String recipientAccountHolderName = scanner.nextLine();
                            bankingSystem.createAccount(recipientAccountNumber, recipientAccountHolderName);
                            recipientAccount = bankingSystem.getAccount(recipientAccountNumber);
                        }
                        if (recipientAccount != null) {
                            System.out.print("Enter transfer amount:");
                            double transferAmount = scanner.nextDouble();
                            senderAccount.transfer(recipientAccount, transferAmount);
                        } else {
                            System.out.println("Recipient account not found.");
                        }
                    } else {
                        System.out.println("Sender account not found.");
                    }
                    break;
                case 6:
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}




