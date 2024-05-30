package edu.neu.csye6200.GUI;

import edu.neu.csye6200.dataAccessing.models.TicketTypes;

import javax.swing.*;
import java.util.Hashtable;

public class CreateTicketTypeGUI extends BaseGUI{
    public CreateTicketTypeGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable, String event_id) {
        super(previousJFrame, universalHashtable);

        // Here you can create a new ticket type
        // First, the page title
        JLabel createTicketTypeLabel = new JLabel();
        createTicketTypeLabel.setText("Create Ticket Type");
        createTicketTypeLabel.setFont(new java.awt.Font("Verdana", 1, 25));
        createTicketTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(createTicketTypeLabel);
        createTicketTypeLabel.setBounds(0, 0, 1024, 50);

        // Then, the form to create a new ticket type
        // The form contains ticket_type_name, ticket_type_price, ticket_type_quantity(in total)
        // The form also contains a submit button
        // The form also contains a cancel button

        JLabel ticketTypeNameLabel = new JLabel();
        ticketTypeNameLabel.setText("Ticket Type Name:");
        ticketTypeNameLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketTypeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketTypeNameLabel);
        ticketTypeNameLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 50, 800, 50);

        JTextField ticketTypeNameTextField = new JTextField();
        ticketTypeNameTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketTypeNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketTypeNameTextField);
        ticketTypeNameTextField.setBounds((currentJFrame.getWidth() - 800) / 2, 100, 800, 50);

        JLabel ticketTypePriceLabel = new JLabel();
        ticketTypePriceLabel.setText("Ticket Type Price:");
        ticketTypePriceLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketTypePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketTypePriceLabel);
        ticketTypePriceLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 150, 800, 50);

        JTextField ticketTypePriceTextField = new JTextField();
        ticketTypePriceTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketTypePriceTextField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketTypePriceTextField);
        ticketTypePriceTextField.setBounds((currentJFrame.getWidth() - 800) / 2, 200, 800, 50);

        JLabel ticketTypeQuantityLabel = new JLabel();
        ticketTypeQuantityLabel.setText("Ticket Type Quantity:");
        ticketTypeQuantityLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketTypeQuantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketTypeQuantityLabel);
        ticketTypeQuantityLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 250, 800, 50);

        JTextField ticketTypeQuantityTextField = new JTextField();
        ticketTypeQuantityTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketTypeQuantityTextField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketTypeQuantityTextField);
        ticketTypeQuantityTextField.setBounds((currentJFrame.getWidth() - 800) / 2, 300, 800, 50);

        JButton submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.setFont(new java.awt.Font("Verdana", 1, 15));
        submitButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(submitButton);
        submitButton.setBounds((currentJFrame.getWidth() - 800) / 2, 400, 800, 50);

        JButton cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFont(new java.awt.Font("Verdana", 1, 15));
        cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(cancelButton);
        cancelButton.setBounds((currentJFrame.getWidth() - 800) / 2, 450, 800, 50);

        // If pressed submit, then submit the form
        submitButton.addActionListener(e -> {
            // Get the values from the form
            String ticket_type_name = ticketTypeNameTextField.getText();
            String ticket_type_price = ticketTypePriceTextField.getText();
            String ticket_type_quantity = ticketTypeQuantityTextField.getText();

            // Check if the values are valid
            if (ticket_type_name.equals("")) {
                JOptionPane.showMessageDialog(null, "Ticket Type Name cannot be empty!");
                return;
            }
            if (ticket_type_price.equals("")) {
                JOptionPane.showMessageDialog(null, "Ticket Type Price cannot be empty!");
                return;
            }
            if (ticket_type_quantity.equals("")) {
                JOptionPane.showMessageDialog(null, "Ticket Type Quantity cannot be empty!");
                return;
            }

            // Check if the values are valid
            // Prices should be a decimal number no less than 0, quantity should be an integer no less than 0
            double ticket_type_price_double;
            try {
                ticket_type_price_double = Double.parseDouble(ticket_type_price);
                if (ticket_type_price_double < 0) {
                    JOptionPane.showMessageDialog(null, "Ticket Type Price should be no less than 0!");
                    return;
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Ticket Type Price should be a decimal number!");
                return;
            }
            int ticket_type_quantity_int;
            try {
                ticket_type_quantity_int = Integer.parseInt(ticket_type_quantity);
                if (ticket_type_quantity_int < 0) {
                    JOptionPane.showMessageDialog(null, "Ticket Type Quantity should be no less than 0!");
                    return;
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Ticket Type Quantity should be an integer!");
                return;
            }

            // If all values are valid, then submit the form
            if (TicketTypes.getInstance().insertTicketType(
                    event_id, ticket_type_price_double, ticket_type_name, ticket_type_quantity_int
            )) {
                JOptionPane.showMessageDialog(null, "Ticket Type Created Successfully!");
                currentJFrame.dispose();
                previousJFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Ticket Type Created Failed!");
            }
        });

        // If pressed cancel, then go back to manage ticket gui
        cancelButton.addActionListener(e -> {
            currentJFrame.dispose();
            previousJFrame.setVisible(true);
        });
    }
}
