package edu.neu.csye6200.GUI;

import edu.neu.csye6200.dataAccessing.models.TicketTypes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ManageTicketGUI extends BaseGUI {

        ManageTicketGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable, String event_id) {
            super(previousJFrame, universalHashtable);

            // Here you can manage an event
            // First, the page title
            JLabel manageTicketLabel = new JLabel();
            manageTicketLabel.setText("Manage Ticket Types");
            manageTicketLabel.setFont(new java.awt.Font("Verdana", 1, 25));
            manageTicketLabel.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(manageTicketLabel);
            manageTicketLabel.setBounds(0, 0, 1024, 50);



            // A table to display all ticket types, and you can click on them to manage them, and you can also create new ticket types
            // An interactive table with a scroll bar
            // The table contains ticket_type_id, ticket_type_name, ticket_type_price, ticket_type_quantity(in total)
            // This is the table for displaying all existing ticket types and their information
            JLabel ticketTypeLabel = new JLabel();
            ticketTypeLabel.setText("Ticket Types:");
            ticketTypeLabel.setFont(new java.awt.Font("Verdana", 1, 15));
            ticketTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(ticketTypeLabel);
            ticketTypeLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 50, 800, 50);

            JTable ticketTypeTable = new JTable();
            ticketTypeTable.setFont(new java.awt.Font("Verdana", 1, 15));
            ticketTypeTable.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                            "Ticket Type ID", "Ticket Type Name", "Ticket Type Price", "Ticket Type Quantity"
                    }
            ) {
                Class[] types = new Class [] {
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean [] {
                        false, false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }

            });
            ticketTypeTable.setRowHeight(30);
            ticketTypeTable.getTableHeader().setReorderingAllowed(false);
            ticketTypeTable.getTableHeader().setFont(new java.awt.Font("Verdana", 1, 15));
            ticketTypeTable.getTableHeader().setOpaque(false);
            ticketTypeTable.getTableHeader().setBackground(new java.awt.Color(0, 0, 0));
            ticketTypeTable.getTableHeader().setForeground(new java.awt.Color(255, 255, 255));
            ticketTypeTable.setBounds((currentJFrame.getWidth() - 800) / 2, 100, 800, 300);
            currentJFrame.getContentPane().add(ticketTypeTable);

            // Then, a scroll bar
            JScrollPane ticketTypeScrollPane = new JScrollPane();
            ticketTypeScrollPane.setViewportView(ticketTypeTable);
            currentJFrame.getContentPane().add(ticketTypeScrollPane);
            ticketTypeScrollPane.setBounds((currentJFrame.getWidth() - 800) / 2, 100, 800, 300);

            // Then, a button to create new ticket types
            JButton createNewTicketTypeButton = new JButton();
            createNewTicketTypeButton.setText("Create New Ticket Type");
            createNewTicketTypeButton.setFont(new java.awt.Font("Verdana", 1, 15));
            createNewTicketTypeButton.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(createNewTicketTypeButton);
            createNewTicketTypeButton.setBounds((currentJFrame.getWidth() - 800) / 2, 400, 800, 50);

            JButton backButton = new JButton();
            backButton.setText("Back");
            backButton.setFont(new java.awt.Font("Verdana", 1, 15));
            backButton.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(backButton);
            backButton.setBounds(570, 500, 800, 50);

            backButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    // Go back to the previous page
                    currentJFrame.setVisible(false);
                    previousJFrame.setVisible(true);
                }
            });



            // Fetch data
            List<Dictionary<String, String>> ticketTypeTableData = TicketTypes.getInstance().getRecordsByEventId(event_id);
            if (ticketTypeTableData != null) {
                DefaultTableModel ticketTypeTableModel = (DefaultTableModel) ticketTypeTable.getModel();
                ticketTypeTableModel.setRowCount(0);
                for (Dictionary<String, String> ticketType : ticketTypeTableData) {
                    ticketTypeTableModel.addRow(new Object[]{
                            ticketType.get("id"),
                            ticketType.get("price"),
                            ticketType.get("name"),
                            ticketType.get("how_many_tickets_in_total")
                    });
                }
            }


            // Update the page with all ticket types, when the page is loaded
//            currentJFrame.addComponentListener(new ComponentAdapter() {
//                @Override
//                public void componentShown(java.awt.event.ComponentEvent evt) {
//                    //System.out.println("Frame is shown");
//                    // Update the table
//                    List<Dictionary<String, String>> ticketTypeTableData = TicketTypes.getInstance().getRecordsByEventId(event_id);
//                    if (ticketTypeTableData != null) {
//                        DefaultTableModel ticketTypeTableModel = (DefaultTableModel) ticketTypeTable.getModel();
//                        ticketTypeTableModel.setRowCount(0);
//                        for (Dictionary<String, String> ticketType : ticketTypeTableData) {
//                            ticketTypeTableModel.addRow(new Object[]{
//                                    ticketType.get("id"),
//                                    ticketType.get("price"),
//                                    ticketType.get("name"),
//                                    ticketType.get("how_many_tickets_in_total")
//                            });
//                        }
//                    }
//                }
//            });

            //changed table rendering logic on events
            currentJFrame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(java.awt.event.ComponentEvent e) {
                    //System.out.println("Frame is shown");
                    ((javax.swing.table.DefaultTableModel) ticketTypeTable.getModel()).setRowCount(0);
                    List<Dictionary<String, String>> ticketRecords = TicketTypes.getInstance().getRecordsByEventId(event_id);
                    if (ticketRecords != null) {
                        for (Dictionary<String, String> ticketRecord : ticketRecords) {
                            ((javax.swing.table.DefaultTableModel) ticketTypeTable.getModel()).addRow(new Object[]{
                                    ticketRecord.get("id"),
                                    ticketRecord.get("name"),
                                    ticketRecord.get("price"),
                                    ticketRecord.get("how_many_tickets_in_total")
                            });
                        }
                    }
                }
            });

            // If pressed on a row, then go to manage ticket type gui
            ticketTypeTable.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = ticketTypeTable.rowAtPoint(evt.getPoint());
                    int col = ticketTypeTable.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                        String ticket_type_id = (String) ticketTypeTable.getModel().getValueAt(row, 0);
                        currentJFrame.setVisible(false);
                        new ManageTicketTypeGUI(currentJFrame, universalHashtable, ticket_type_id);
                    }
                }
            });

            // If pressed create new ticket type, then go to create ticket type gui
            createNewTicketTypeButton.addActionListener(e -> {
                currentJFrame.setVisible(false);
                new CreateTicketTypeGUI(currentJFrame, universalHashtable, event_id);
            });

        }
}
