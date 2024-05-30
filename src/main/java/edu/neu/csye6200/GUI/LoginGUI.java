package edu.neu.csye6200.GUI;

import edu.neu.csye6200.Services.AccountService;
import edu.neu.csye6200.dataAccessing.models.User;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class LoginGUI extends BaseGUI{

    LoginGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable) {
        super(previousJFrame, universalHashtable);
        if (super.universalHashtable == null)
            super.universalHashtable = new Hashtable<>();


        // Welcome Label should be in the top center of the window
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("Event Ticket Management System");
        welcomeLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(welcomeLabel);
        welcomeLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 50, 500, 100);


        // username and password area, the text should be smaller than the label mentioned above
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

        // Login Button and register button, don't make them too long, and make them in the center, symmetrical
        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(loginButton);
        loginButton.setBounds((currentJFrame.getWidth() - 500) / 2, 400, 200, 25);

        JButton registerButton = new JButton();
        registerButton.setText("Register");
        registerButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(registerButton);
        registerButton.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 400, 200, 25);


        // Register Button should open a new window
        registerButton.addActionListener(e -> {
            new RegisterGUI(currentJFrame, super.universalHashtable);
            currentJFrame.setVisible(false);
        });

        // Login Button should open a new window
        loginButton.addActionListener(e -> {
            // First check if the username and password are correct
            String username = usernameField.getText();
            String password = passwordField.getText();

            // If correct, then open the main window
            // If not, then show a message dialog
            if (AccountService.loginVerification(username, password)) {
                super.universalHashtable.put("user_id", User.getInstance().getRecordByUsername(username).get("id"));
                new MainGUI(currentJFrame, super.universalHashtable);
                currentJFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(currentJFrame, "Username or password is incorrect");
            }
        });


    }

    LoginGUI() {
        this(null, null);
    }
}
