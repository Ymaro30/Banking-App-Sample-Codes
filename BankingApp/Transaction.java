
package BankingApp;

import java.time.LocalDateTime;

public class Transaction { 
    private String mobileNumber; 
    private String password;
    private String fullname; 
    private double amount;

    private String transactionType;
    private String details;
    private LocalDateTime transactionDate;

    public Transaction(String mobileNumber, String password, String fullname, double amount, String transactionType, String details, LocalDateTime transactionDate) {
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.fullname = fullname;
        this.amount = amount;
        this.transactionType = transactionType;
        this.details = details;
        this.transactionDate = transactionDate;
    }

    public Transaction( String mobileNumber, String transactionType, double amount, String details, LocalDateTime transactionDate) {
         this.mobileNumber = mobileNumber;
         this.transactionType = transactionType;
        this.amount = amount;
        this.details = details;
        this.transactionDate = transactionDate;
    }
 
    // Getters and Setters (Encapsulation)
    public String getMobileNumber() { return mobileNumber; }
    public String getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
    public String getDetails() { return details; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public String getPassword() {return password;}
    public String getFullname() {return  fullname;}
}
