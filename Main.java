import java.util.*;

public class Main {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}

public class BankingSystem {
    private Map<String, Account> accounts;

    public BankingSystem() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);

        if (fromAccount != null && toAccount != null) {
            if (fromAccount.getBalance() >= amount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Transfer failed: Insufficient balance in source account.");
            }
        } else {
            System.out.println("Transfer failed: One or both accounts do not exist.");
        }
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();

        // Create accounts
        Account account1 = new Account("123456", "Alice", 1000);
        Account account2 = new Account("789012", "Bob", 500);
        Account account3 = new Account("345678", "Charlie", 1500);

        // Add accounts to banking system
        bankingSystem.addAccount(account1);
        bankingSystem.addAccount(account2);
        bankingSystem.addAccount(account3);

        // Perform transactions
        System.out.println("Initial account details:");
        System.out.println(bankingSystem.getAccount("123456"));
        System.out.println(bankingSystem.getAccount("789012"));
        System.out.println(bankingSystem.getAccount("345678"));

        bankingSystem.transfer("123456", "789012", 300);
        bankingSystem.transfer("345678", "123456", 200);

        System.out.println("Final account details after transactions:");
        System.out.println(bankingSystem.getAccount("123456"));
        System.out.println(bankingSystem.getAccount("789012"));
        System.out.println(bankingSystem.getAccount("345678"));
    }
}
