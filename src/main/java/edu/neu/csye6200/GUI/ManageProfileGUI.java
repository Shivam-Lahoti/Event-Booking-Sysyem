package edu.neu.csye6200.GUI;

import edu.neu.csye6200.Services.AccountService;
import edu.neu.csye6200.Utilities.PasswordUtility;
import edu.neu.csye6200.dataAccessing.models.ExtraOrganizerInfo;
import edu.neu.csye6200.dataAccessing.models.User;

import javax.swing.*;
import java.util.Hashtable;

public class ManageProfileGUI extends BaseGUI{
    public ManageProfileGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable) {
        super(previousJFrame, universalHashtable);

        // First, it doesn't matter if you're an organizer or a customer, you can always manage your shared profile information
        // This includes your username, password, display_name, email_address

        // First the page title
        JLabel manageProfileLabel = new JLabel();
        manageProfileLabel.setText("Manage Profile");
        manageProfileLabel.setFont(new java.awt.Font("Verdana", 1, 25));
        manageProfileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(manageProfileLabel);
        manageProfileLabel.setBounds(0, 0, 1024, 50);

        // Then the username and password area, the text should be smaller than the label mentioned above, the fields should also contain the current information
        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        usernameLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(usernameLabel);
        usernameLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 100, 200, 25);

        JTextField usernameField = new JTextField();
        usernameField.setText(User.getInstance().getRecordById(universalHashtable.get("user_id")).get("username"));
        usernameField.setFont(new java.awt.Font("Verdana", 1, 15));
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(usernameField);
        usernameField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 100, 200, 25);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(passwordLabel);
        passwordLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 200, 200, 25);

        JPasswordField passwordField = new JPasswordField();
        //passwordField.setText(User.getInstance().getRecordById(universalHashtable.get("user_id")).get("password_encrypted"));
        passwordField.setFont(new java.awt.Font("Verdana", 1, 15));
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(passwordField);
        passwordField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 200, 200, 25);

        JLabel displayNameLabel = new JLabel();
        displayNameLabel.setText("Display Name:");
        displayNameLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        displayNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(displayNameLabel);
        displayNameLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 300, 200, 25);

        JTextField displayNameField = new JTextField();
        displayNameField.setText(User.getInstance().getRecordById(universalHashtable.get("user_id")).get("display_name"));
        displayNameField.setFont(new java.awt.Font("Verdana", 1, 15));
        displayNameField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(displayNameField);
        displayNameField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 300, 200, 25);

        JLabel emailAddressLabel = new JLabel();
        emailAddressLabel.setText("Email Address:");
        emailAddressLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        emailAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(emailAddressLabel);
        emailAddressLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 400, 200, 25);

        JTextField emailAddressField = new JTextField();
        emailAddressField.setText(User.getInstance().getRecordById(universalHashtable.get("user_id")).get("email_address"));
        emailAddressField.setFont(new java.awt.Font("Verdana", 1, 15));
        emailAddressField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(emailAddressField);
        emailAddressField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 400, 200, 25);


        JTextField descriptionField;
        // If it's an organizer, then you can also manage your organizer-specific information, aka the description
        if (AccountService.isAnOrganizer(universalHashtable)) {
            // also modify the description
            JLabel descriptionLabel = new JLabel();
            descriptionLabel.setText("Description:");
            descriptionLabel.setFont(new java.awt.Font("Verdana", 1, 15));
            descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(descriptionLabel);
            descriptionLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 500, 200, 25);

            descriptionField = new JTextField();
            descriptionField.setText(
                    ExtraOrganizerInfo.getInstance().getRecordByUserId(universalHashtable.get("user_id")).get("description")
            );
            descriptionField.setFont(new java.awt.Font("Verdana", 1, 15));
            descriptionField.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(descriptionField);
            descriptionField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 500, 200, 25);
        } else {
            descriptionField = null;
        }


        // Then the buttons
        JButton saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.setFont(new java.awt.Font("Verdana", 1, 15));
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(saveButton);
        saveButton.setBounds((currentJFrame.getWidth() - 500) / 2, 600, 200, 25);

        JButton cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFont(new java.awt.Font("Verdana", 1, 15));
        cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(cancelButton);
        cancelButton.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 600, 200, 25);



        // Then the buttons' actions
        saveButton.addActionListener(e -> {
            // First check if the username is already taken
            String username = usernameField.getText();
            String password = passwordField.getText();
            String displayName = displayNameField.getText();
            String emailAddress = emailAddressField.getText();

            String description = null;
            if (AccountService.isAnOrganizer(universalHashtable)) {
                description = descriptionField.getText();
            }



            // If the username is already taken, then show a message dialog
            if (User.getInstance().getRecordByUsername(username) != null && !User.getInstance().getRecordById(universalHashtable.get("user_id")).get("username").equals(username)) {
                JOptionPane.showMessageDialog(currentJFrame, "Username already taken!");
            } else {
                // If password is empty, then don't update the password
                String encryptedPassword;
                if (password.equals("")) {
                    encryptedPassword = User.getInstance().getRecordById(universalHashtable.get("user_id")).get("password_encrypted");
                } else {
                    encryptedPassword = PasswordUtility.encrypt(password);
                }

                // Otherwise, update the user record
                User.getInstance().updateUser(
                        universalHashtable.get("user_id"),
                        new Hashtable<String, String>() {{
                            put("username", username);
                            put("password_encrypted", encryptedPassword);
                            put("display_name", displayName);
                            put("email_address", emailAddress);
                        }}
                );
                if (AccountService.isAnOrganizer(universalHashtable)) {
                    ExtraOrganizerInfo.getInstance().updateOrganizerInfoByUserId(universalHashtable.get("user_id"), description);
                }
                JOptionPane.showMessageDialog(currentJFrame, "Profile updated!");
            }
        });

        cancelButton.addActionListener(e -> {
            currentJFrame.setVisible(false);
            currentJFrame.dispose();
            previousJFrame.setVisible(true);

        });



        // Then, if you're an organizer, you can also manage your organizer-specific information
    }
}
