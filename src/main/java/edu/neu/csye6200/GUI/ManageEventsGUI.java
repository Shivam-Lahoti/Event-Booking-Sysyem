package edu.neu.csye6200.GUI;

import edu.neu.csye6200.Utilities.Datetime;
import edu.neu.csye6200.dataAccessing.models.EventSchedule;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ManageEventsGUI extends BaseGUI{
    public ManageEventsGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable) {
        super(previousJFrame, universalHashtable);


        // Here you can manage events you have created, you can also create new events
        // First, the page title
        JLabel manageEventsLabel = new JLabel();
        manageEventsLabel.setText("Manage Events");
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
                       "Event ID", "Event Title", "Time to Start", "Time to End", "Deadline", "Creation Time"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        // Fetch data
        List<Dictionary<String,String>> eventRecords = EventSchedule.getInstance().getRecordsByOrganizerId(universalHashtable.get("user_id"));
        if (eventRecords != null) {
            for (Dictionary<String, String> eventRecord : eventRecords) {
                ((javax.swing.table.DefaultTableModel) eventTable.getModel()).addRow(new Object[]{
                        eventRecord.get("id"),
                        eventRecord.get("event_title"),
                        Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("time_to_start"))),
                        Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("time_to_end"))),
                        Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("deadline"))),
                        Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("creation_time")))
                });
            }
        }

        // If pressed on a row, then go to manage event gui
        eventTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = eventTable.rowAtPoint(evt.getPoint());
                int col = eventTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    String event_id = (String) eventTable.getModel().getValueAt(row, 0);
                    currentJFrame.setVisible(false);
                    new ManageEventGUI(currentJFrame, universalHashtable, event_id);
                }
            }
        });

        // Then, a button to create a new event
        JButton createEventButton = new JButton();
        createEventButton.setText("Create Event");
        createEventButton.setFont(new java.awt.Font("Verdana", 1, 15));
        createEventButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(createEventButton);
        createEventButton.setBounds(100, 650, 824, 50);

        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.setFont(new java.awt.Font("Verdana", 1, 15));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(backButton);
        backButton.setBounds(100, 720, 824, 50);

        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Go back to the previous page
                currentJFrame.setVisible(false);
                previousJFrame.setVisible(true);
            }
        });

        // If pressed create event, then go to create event gui
        createEventButton.addActionListener(e -> {
            currentJFrame.setVisible(false);
            new CreateEventGUI(currentJFrame, universalHashtable);
        });

        // To update the table when you come back from manage event gui
        currentJFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                //System.out.println("Frame is shown");
                ((javax.swing.table.DefaultTableModel) eventTable.getModel()).setRowCount(0);
                List<Dictionary<String, String>> eventRecords = EventSchedule.getInstance().getRecordsByOrganizerId(universalHashtable.get("user_id"));
                if (eventRecords != null) {
                    for (Dictionary<String, String> eventRecord : eventRecords) {
                        ((javax.swing.table.DefaultTableModel) eventTable.getModel()).addRow(new Object[]{
                                eventRecord.get("id"),
                                eventRecord.get("event_title"),
                                Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("time_to_start"))),
                                Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("time_to_end"))),
                                Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("deadline"))),
                                Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("creation_time")))
                        });
                    }
                }
            }
        });

    }
}
