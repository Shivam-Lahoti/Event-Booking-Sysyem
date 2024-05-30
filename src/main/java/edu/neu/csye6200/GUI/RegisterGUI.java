package edu.neu.csye6200.GUI;

import edu.neu.csye6200.Services.AccountService;
import edu.neu.csye6200.dataAccessing.models.User;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class RegisterGUI extends BaseGUI{
    RegisterGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable) {
        super(previousJFrame, universalHashtable);

        // Register page, will ask the user to provide a new username and password, display_name, email_address, isOrganizer
        JLabel registerLabel = new JLabel();
        registerLabel.setText("Register");
        registerLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(registerLabel);
        registerLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 50, 500, 100);

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        usernameLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(usernameLabel);
        usernameLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 200, 200, 25);

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Verdana", Font.PLAIN, 15));
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(usernameField);
        usernameField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 200, 200, 25);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(passwordLabel);
        passwordLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 300, 200, 25);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 15));
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(passwordField);
        passwordField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 300, 200, 25);

        JLabel displayNameLabel = new JLabel();
        displayNameLabel.setText("Display Name:");
        displayNameLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        displayNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(displayNameLabel);
        displayNameLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 400, 200, 25);

        JTextField displayNameField = new JTextField();
        displayNameField.setFont(new Font("Verdana", Font.PLAIN, 15));
        displayNameField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(displayNameField);
        displayNameField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 400, 200, 25);

        JLabel emailAddressLabel = new JLabel();
        emailAddressLabel.setText("Email Address:");
        emailAddressLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        emailAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(emailAddressLabel);
        emailAddressLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 500, 200, 25);

        JTextField emailAddressField = new JTextField();
        emailAddressField.setFont(new Font("Verdana", Font.PLAIN, 15));
        emailAddressField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(emailAddressField);
        emailAddressField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 500, 200, 25);

        JLabel isOrganizerLabel = new JLabel();
        isOrganizerLabel.setText("Are you an organizer?");
        isOrganizerLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        isOrganizerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(isOrganizerLabel);
        isOrganizerLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 600, 200, 25);

        JCheckBox isOrganizerCheckBox = new JCheckBox();
        isOrganizerCheckBox.setFont(new Font("Verdana", Font.PLAIN, 15));
        isOrganizerCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(isOrganizerCheckBox);
        isOrganizerCheckBox.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 600, 200, 25);

        // Register Button should open a new window
        JButton registerButton = new JButton();
        registerButton.setText("Register");
        registerButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(registerButton);
        registerButton.setBounds((currentJFrame.getWidth() - 500) / 2, 700, 400, 25);

        // Register Button should open a new window
        registerButton.addActionListener(e -> {
           // First get the data from the fields
            String username = usernameField.getText();
            String password = passwordField.getText();
            String displayName = displayNameField.getText();
            String emailAddress = emailAddressField.getText();
            boolean isOrganizer = isOrganizerCheckBox.isSelected();


            // Display name and email should not have spaces in the beginning or the end
            displayName = displayName.trim();
            emailAddress = emailAddress.trim();


            // username and password cannot be empty
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(currentJFrame, "Username and password cannot be empty!");
                return;
            }

            // Username and password cannot contain spaces or special characters, only letters and numbers
            if (!username.matches("[a-zA-Z0-9]+")) {
                JOptionPane.showMessageDialog(currentJFrame, "Username can only contain letters and numbers!");
                return;
            }
            if (!password.matches("[a-zA-Z0-9]+")) {
                JOptionPane.showMessageDialog(currentJFrame, "Password can only contain letters and numbers!");
                return;
            }

            // Then check if the username exists already,
            if (User.getInstance().checkIfExists(User.getTableName(), User.getHeaders(), "username", username)) {
                JOptionPane.showMessageDialog(currentJFrame, "Username already exists!");
                return;
            }

            // Store the new user
            if (AccountService.registerNewUser(username, displayName, password, emailAddress, isOrganizer)) {
                JOptionPane.showMessageDialog(currentJFrame, "Successfully registered!");
                previousJFrame.setVisible(true);
                currentJFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(currentJFrame, "Failed to register!");
            }
        });
    }
}
