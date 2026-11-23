package BankingApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List; 

public class UserDAO { 

    public User getUserByMobile(String mobileNumber) {
            String sql = "SELECT * FROM users WHERE mobile_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mobileNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getString("mobile_number"),
                        rs.getString("pin"),
                        rs.getString("full_name"),
                        rs.getDouble("balance")
                );
                user.getTransactionList().addAll(getTransactionsByUser(mobileNumber));
                return user;
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return null;
    }

    public void updateBalance(String mobileNumber, double newBalance) {
        String sql = "UPDATE users SET balance = ? WHERE mobile_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setString(2, mobileNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     // 1. Insert the new user in the 'users' table at DB 
    public void insertUsers(User user) {
        String insertUserSql = "INSERT IGNORE INTO users (mobile_number, pin, full_name, balance) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement userStmt = conn.prepareStatement(insertUserSql)) {
            userStmt.setString(1, user.mobileNumber());
            userStmt.setString(2, user.pin());
            userStmt.setString(3, user.fullname());
            userStmt.setDouble(4, user.balance());
            userStmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addTransactionRecord(Transaction t) {
        String sql = "INSERT INTO transactions (mobile_number, transaction_type, amount, details) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getMobileNumber());
            stmt.setString(2, t.getTransactionType());
            stmt.setDouble(3, t.getAmount());
            stmt.setString(4, t.getDetails());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }

    public List<Transaction> getTransactionsByUser(String mobileNumber) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE mobile_number = ? ORDER BY transaction_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mobileNumber);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction(
                        rs.getString("mobile_number"),
                        rs.getString("transaction_type"),
                        rs.getDouble("amount"),
                        rs.getString("details"),
                        rs.getTimestamp("transaction_date").toLocalDateTime()
                );
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
