package BankingApp;


import java.util.ArrayList;
import java.util.List; 

public abstract class Account {
    protected String mobileNumber;
    protected String password;
    protected String fullName;
    protected double balance;
    protected List<Transaction> transactionList;

    public Account(String mobileNumber, String password, String fullName, double balance) {
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.fullName = fullName;
        this.balance = balance;
        this.transactionList = new ArrayList<>();
    } 
 
    // Abstract methods to demonstrate Polymorphism
    public abstract void deposit(double amount);
    public abstract boolean withdraw(double amount);

    // Getters & Setters
    public String getMobileNumber() { return mobileNumber; }
    public String getPin() { return password; }
    public String getFullName() { return fullName; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public List<Transaction> getTransactionList() { return transactionList; }
    public void addTransaction(Transaction t) { this.transactionList.add(t); }

    
    public boolean authenticate(String inputPin) {
        return this.password.equals(inputPin);
    }

    public String getOwnerName() {
        return fullName;
    }
}