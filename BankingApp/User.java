package BankingApp;

public class User extends Account {
    String transactionType;
    String details;
    String fullname;
    String mobileNumber;

    public User(String mobileNumber, String pin, String fullName, double balance) {
        super(mobileNumber, pin, fullName, balance);
        this.mobileNumber = mobileNumber;
        this.password = pin;
        this.fullname = fullName;
    }

    @Override
    public void deposit(double amount) {
            this.balance = balance + amount; 
    }   
    

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance = balance - amount;
            return true;
        }
        return false;
    } 
    
    public String mobileNumber() { return mobileNumber; }
    public String pin() {return password;}
    public String fullname() {return  fullname;}
    public double balance() { return balance; }
}