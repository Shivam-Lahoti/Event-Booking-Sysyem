package edu.neu.csye6200.GUI;

import edu.neu.csye6200.dataAccessing.models.TicketTypes;
import javax.swing.*;
import java.util.Hashtable;
import java.util.Dictionary;

public class ManageTicketTypeGUI extends BaseGUI{
    public ManageTicketTypeGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable, String TicketType_id) {
        super(previousJFrame, universalHashtable);

        // Here you can manage ticket types
        // First, the page title
        JLabel manageTicketTypeLabel = new JLabel();
        manageTicketTypeLabel.setText("Update Ticket Type");
        manageTicketTypeLabel.setFont(new java.awt.Font("Verdana", 1, 25));
        manageTicketTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(manageTicketTypeLabel);
        manageTicketTypeLabel.setBounds(0, 0, 1024, 50);

        //ticket name
        JLabel ticketTitleLabel = new JLabel();
        ticketTitleLabel.setText("Ticket Title *");
        ticketTitleLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketTitleLabel);
        ticketTitleLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 100, 200, 25);

        JTextField ticketTitleTextField = new JTextField();
        ticketTitleTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketTitleTextField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketTitleTextField);
        ticketTitleTextField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 100, 200, 25);

//        ticket price
        JLabel ticketPriceLabel = new JLabel();
        ticketPriceLabel.setText("Ticket Price *");
        ticketPriceLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketPriceLabel);
        ticketPriceLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 200, 200, 25);

        JTextField ticketPriceField = new JTextField();
        ticketPriceField.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketPriceField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketPriceField);
        ticketPriceField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 200, 200, 25);

        //Ticket Quantity
        JLabel ticketQuantityLabel = new JLabel();
        ticketQuantityLabel.setText("Ticket Quantity *");
        ticketQuantityLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketQuantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketQuantityLabel);
        ticketQuantityLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 300, 200, 25);

        JTextField ticketQuantityField = new JTextField();
        ticketQuantityField.setFont(new java.awt.Font("Verdana", 1, 15));
        ticketQuantityField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(ticketQuantityField);
        ticketQuantityField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 300, 200, 25);

        // The buttons should be in the same row, side by side
        int buttonWidth = 200;
        int gapBetweenButtons = 10; // Adjust this value based on the gap you want between buttons;
        //update ticket info button
        JButton updateTicketInfo = new JButton();
        updateTicketInfo.setText("Update Ticket");
        updateTicketInfo.setFont(new java.awt.Font("Verdana", 1, 15));
        updateTicketInfo.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(updateTicketInfo);
        updateTicketInfo.setBounds(currentJFrame.getX() + buttonWidth + gapBetweenButtons, 650, buttonWidth, 50);


        // Then, a button to delete the ticket
        JButton deleteTicketButton = new JButton();
        deleteTicketButton.setText("Delete Ticket");
        deleteTicketButton.setFont(new java.awt.Font("Verdana", 1, 15));
        deleteTicketButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(deleteTicketButton);
        deleteTicketButton.setBounds(updateTicketInfo.getX() + buttonWidth + gapBetweenButtons, 650, buttonWidth, 50);

        // Then, a button to go back to the previous page
        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.setFont(new java.awt.Font("Verdana", 1, 15));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(backButton);
        backButton.setBounds(deleteTicketButton.getX() + buttonWidth + gapBetweenButtons, 650, buttonWidth, 50);

        updateTicketInfo.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                //getting info from the form
                String ticketTitle = ticketTitleTextField.getText();
                String ticketPrice = ticketPriceField.getText();
                String ticketQuantity = ticketQuantityField.getText();

                ticketTitle = ticketTitle.trim();
                ticketPrice = ticketPrice.trim();
                ticketQuantity = ticketQuantity.trim();

                //checking if input is valid
                if (ticketTitle.equals("")) {
                    JOptionPane.showMessageDialog(currentJFrame, "Ticket Title cannot be empty!");
                    return;
                }
                if (ticketPrice.equals("")) {
                    JOptionPane.showMessageDialog(currentJFrame, "Ticket Price cannot be empty!");
                    return;
                }
                if (ticketQuantity.equals("")) {
                    JOptionPane.showMessageDialog(currentJFrame, "Ticket Quantity cannot be empty!");
                    return;
                }

                //update the ticket information
                String finalTicket_title = ticketTitle;
                String finalTicket_price = ticketPrice;
                String finalTicket_quantity = ticketQuantity;

                if(TicketTypes.getInstance().updateTicketType(
                        TicketType_id,
                        new Hashtable<String, String>(){{
                            put("price", finalTicket_price);
                            put("name", finalTicket_title);
                            put("how_many_tickets_in_total", finalTicket_quantity);
                        }})
                ){
                    JOptionPane.showMessageDialog(currentJFrame, "Ticket Updated Successfully");
                    currentJFrame.setVisible(false);
                    previousJFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(currentJFrame, "Ticket Update Failed");
                }
            }
        });

        deleteTicketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt){
                if(TicketTypes.getInstance().deleteTicketType(TicketType_id)){
                    JOptionPane.showMessageDialog(currentJFrame, "Ticket Deleted Successfully");
                    currentJFrame.setVisible(false);
                    previousJFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(currentJFrame, "Error in deleting the ticket");
                }
            }
        });

        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Go back to the previous page
                currentJFrame.setVisible(false);
                previousJFrame.setVisible(true);
            }
        });

        //fetching data
        Dictionary<String, String> ticketRecord = TicketTypes.getInstance().getRecordById(TicketType_id);
        if (ticketRecord != null) {
            ticketTitleTextField.setText(ticketRecord.get("name"));
            ticketPriceField.setText(ticketRecord.get("price"));
            ticketQuantityField.setText(ticketRecord.get("how_many_tickets_in_total"));
        }
    }
}
