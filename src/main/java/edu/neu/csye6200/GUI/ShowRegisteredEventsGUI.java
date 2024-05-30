package edu.neu.csye6200.GUI;

import edu.neu.csye6200.Utilities.Datetime;
import edu.neu.csye6200.dataAccessing.models.EventRegistration;
import edu.neu.csye6200.dataAccessing.models.EventSchedule;
import edu.neu.csye6200.dataAccessing.models.TicketTypes;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ComponentAdapter;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ShowRegisteredEventsGUI extends BaseGUI{
    public ShowRegisteredEventsGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable, String user_id) {

        super(previousJFrame, universalHashtable);


        // Here you can manage events you have created, you can also create new events
        // First, the page title
        JLabel manageEventsLabel = new JLabel();
        manageEventsLabel.setText("Registered Events");
        manageEventsLabel.setFont(new java.awt.Font("Verdana", 1, 25));
        manageEventsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(manageEventsLabel);
        manageEventsLabel.setBounds(0, 0, 1024, 50);

        // Then, display all events you have created, and you can click on them to manage them


        // An interactive table with a scroll bar
        // The table contains event_id, event_title,time_to_start,time_to_end,deadline,creation_time
        JTable eventTable = new JTable();
        eventTable.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                       "Event ID", "Event Title", "Time to Start", "Time to End", "Creation Time", "Ticket Type"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }

        });
        eventTable.setRowHeight(30);
        eventTable.getTableHeader().setReorderingAllowed(false);
        eventTable.getTableHeader().setFont(new java.awt.Font("Verdana", 1, 15));
        eventTable.getTableHeader().setOpaque(false);
        eventTable.getTableHeader().setBackground(new java.awt.Color(0, 0, 0));
        eventTable.getTableHeader().setForeground(new java.awt.Color(255, 255, 255));
        eventTable.setFont(new java.awt.Font("Verdana", 1, 15));

        // Then, a scroll bar
        JScrollPane eventScrollPane = new JScrollPane();
        eventScrollPane.setViewportView(eventTable);
        currentJFrame.getContentPane().add(eventScrollPane);
        eventScrollPane.setBounds(50, 100, 900, 500);

        int buttonWidth = 200;

        // Then, a button to go back to the previous page
        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.setFont(new java.awt.Font("Verdana", 1, 15));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(backButton);
        backButton.setBounds(100, 650, buttonWidth, 50);

        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Go back to the previous page
                currentJFrame.setVisible(false);
                previousJFrame.setVisible(true);
            }
        });


        // Fetch data
        List<Dictionary<String,String>> eventRecords = EventRegistration.getInstance().getRecordsBy_user_id(user_id);
        if (eventRecords != null) {
            for (Dictionary<String, String> eventRecord : eventRecords) {
                System.out.println(eventRecord);
                Dictionary<String, String> ticketType = TicketTypes.getInstance().getRecordById(eventRecord.get("ticket_type_id"));
                System.out.println(ticketType);
                if (ticketType != null) {
                    Dictionary<String, String> eventType = EventSchedule.getInstance().getRecordById(ticketType.get("event_id"));
                    System.out.println(eventType);
                    if (eventType != null) {
                        ((javax.swing.table.DefaultTableModel) eventTable.getModel()).addRow(new Object[]{
                                eventType.get("id"),
                                eventType.get("event_title"),
                                Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventType.get("time_to_start"))),
                                Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventType.get("time_to_end"))),
                                Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("purchase_date"))),
                                ticketType.get("name")
                        });
                    }
                }
            }
        }

        // If pressed on a row, then go to manage event gui
//        eventTable.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                int row = eventTable.rowAtPoint(evt.getPoint());
//                int col = eventTable.columnAtPoint(evt.getPoint());
//                if (row >= 0 && col >= 0) {
//                    String event_id = (String) eventTable.getModel().getValueAt(row, 0);
//                    currentJFrame.setVisible(false);
//                    new ManageEventGUI(currentJFrame, universalHashtable, event_id);
//                }
//            }
//        });

        // To update the table when you come back from manage event gui
        currentJFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                //System.out.println("Frame is shown");
                ((javax.swing.table.DefaultTableModel) eventTable.getModel()).setRowCount(0);
                List<Dictionary<String, String>> eventRecords = EventRegistration.getInstance().getRecordsBy_user_id(universalHashtable.get("user_id"));
                if (eventRecords != null) {
                    for (Dictionary<String, String> eventRecord : eventRecords) {
                        ((javax.swing.table.DefaultTableModel) eventTable.getModel()).addRow(new Object[]{
                                //, "user_id", "ticket_type_id", "isItAvailable", "description", "purchase_date"
                                eventRecord.get("user_id"),
                                eventRecord.get("ticket_type_id"),
                                Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("isItAvailable"))),
                                Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("description"))),
                                Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("purchase_date"))),
                        });
                    }
                }
            }
        });

    }
}
