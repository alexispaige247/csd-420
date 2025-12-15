/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fanmanager;

/**
 *
 * @author alexismitchell
 */
// Alexis Mitchell
// December 14, 2025
// This program connects to a MySQL database to display and update fan records using a GUI.

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class FanManager extends JFrame {

    // Database connection info
    private final String url = "jdbc:mysql://localhost:3306/databasedb?useSSL=false&serverTimezone=UTC";
    private final String user = "student1";
    private final String password = "pass";

    // GUI components
    private JTextField txtID, txtFirstName, txtLastName, txtFavoriteTeam;
    private JButton btnDisplay, btnUpdate;

    public FanManager() {
        setTitle("Fan Manager");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Labels
        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(20, 20, 80, 25);
        add(lblID);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setBounds(20, 50, 80, 25);
        add(lblFirstName);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setBounds(20, 80, 80, 25);
        add(lblLastName);

        JLabel lblFavoriteTeam = new JLabel("Favorite Team:");
        lblFavoriteTeam.setBounds(20, 110, 100, 25);
        add(lblFavoriteTeam);

        // Text fields
        txtID = new JTextField();
        txtID.setBounds(130, 20, 200, 25);
        add(txtID);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(130, 50, 200, 25);
        add(txtFirstName);

        txtLastName = new JTextField();
        txtLastName.setBounds(130, 80, 200, 25);
        add(txtLastName);

        txtFavoriteTeam = new JTextField();
        txtFavoriteTeam.setBounds(130, 110, 200, 25);
        add(txtFavoriteTeam);

        // Buttons
        btnDisplay = new JButton("Display");
        btnDisplay.setBounds(50, 150, 120, 30);
        add(btnDisplay);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(200, 150, 120, 30);
        add(btnUpdate);

        // Button actions
        btnDisplay.addActionListener(e -> displayRecord());
        btnUpdate.addActionListener(e -> updateRecord());
    }

    private void displayRecord() {
        int id;
        try {
            id = Integer.parseInt(txtID.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID must be an integer!");
            return;
        }

        String sql = "SELECT * FROM fans WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                txtFirstName.setText(rs.getString("firstname"));
                txtLastName.setText(rs.getString("lastname"));
                txtFavoriteTeam.setText(rs.getString("favoriteteam"));
            } else {
                JOptionPane.showMessageDialog(this, "Record not found!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateRecord() {
    int id;
    try {
        id = Integer.parseInt(txtID.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID must be an integer!");
        return;
    }

    String sql = "INSERT INTO fans (ID, firstname, lastname, favoriteteam) " +
                 "VALUES (?, ?, ?, ?) " +
                 "ON DUPLICATE KEY UPDATE " +
                 "firstname = VALUES(firstname), " +
                 "lastname = VALUES(lastname), " +
                 "favoriteteam = VALUES(favoriteteam)";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);
        pstmt.setString(2, txtFirstName.getText());
        pstmt.setString(3, txtLastName.getText());
        pstmt.setString(4, txtFavoriteTeam.getText());

        pstmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Record inserted or updated successfully!");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found!");
            System.exit(0);
        }

        // Launch GUI
        SwingUtilities.invokeLater(() -> {
            FanManager manager = new FanManager();
            manager.setVisible(true);
        });
    }
}