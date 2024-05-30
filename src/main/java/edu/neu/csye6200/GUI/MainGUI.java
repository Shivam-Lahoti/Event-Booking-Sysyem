package edu.neu.csye6200.GUI;

import edu.neu.csye6200.Services.AccountService;
import edu.neu.csye6200.Utilities.Datetime;
import edu.neu.csye6200.dataAccessing.models.EventSchedule;
import edu.neu.csye6200.dataAccessing.models.User;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowListener;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class MainGUI extends BaseGUI{
        MainGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable) {
            super(previousJFrame, universalHashtable);

            // To tell the current user's status, first welcome by their names (if display name not exists, then username)
            // Then display if they are a customer or an organizer
            // If display name exists, let String nameString = display_name, otherwise let String nameString = username
            String nameString = User.getInstance().getRecordById(universalHashtable.get("user_id")).get("display_name");
            if (nameString == null || nameString.equals("")) {
                nameString = User.getInstance().getRecordById(universalHashtable.get("user_id")).get("username");
            }
            JLabel welcomeLabel = new JLabel();
            welcomeLabel.setText(
                    "Welcome, " + nameString + "!");
            welcomeLabel.setFont(new java.awt.Font("Verdana", 1, 25));
            welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(welcomeLabel);
            welcomeLabel.setBounds(0, 0, 1024, 50);


            JLabel statusLabel = new JLabel();

            statusLabel.setText("You are currently logged in as: " + (AccountService.isAnOrganizer(universalHashtable) ? "Organizer" : "Customer"));
            statusLabel.setFont(new java.awt.Font("Verdana", 1, 15));
            statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(statusLabel);
            statusLabel.setBounds(0, 50, 1024, 50);

            if (AccountService.isAnOrganizer(universalHashtable)) {
                organizerGUI();
            } else {
                customerGUI();
            }


            // When profile is managed, the display name should be updated
            currentJFrame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    //System.out.println("Frame is shown");
                    String nameString = User.getInstance().getRecordById(universalHashtable.get("user_id")).get("display_name");
                    if (nameString == null || nameString.equals("")) {
                        nameString = User.getInstance().getRecordById(universalHashtable.get("user_id")).get("username");
                    }
                    welcomeLabel.setText(
                            "Welcome, " + nameString + "!");
                }

                @Override
                public void componentHidden(ComponentEvent e) {
                    //System.out.println("Frame is hidden");
                }
            });
        }

        // This is the gui for event organizers
        private void organizerGUI() {
            // Organizers can manage their own profiles and manage their events

            // Don't make buttons so long, give them some spaces!
            JButton manageProfileButton = new JButton();
            manageProfileButton.setText("Manage Profile");
            manageProfileButton.setFont(new java.awt.Font("Verdana", 1, 15));
            manageProfileButton.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(manageProfileButton);
            manageProfileButton.setBounds(100, 100, 824, 50);

            JButton manageEventsButton = new JButton();
            manageEventsButton.setText("Manage Events");
            manageEventsButton.setFont(new java.awt.Font("Verdana", 1, 15));
            manageEventsButton.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(manageEventsButton);
            // Make the button centered and with margins left and right, symmetrical
            manageEventsButton.setBounds(100, 200, 824, 50);

            // If pressed manage profile, then go to manage profile gui
            manageProfileButton.addActionListener(e -> {
                currentJFrame.setVisible(false);
                new ManageProfileGUI(currentJFrame, universalHashtable);
            });

            // If pressed manage events, then go to manage events gui
            manageEventsButton.addActionListener(e -> {
                currentJFrame.setVisible(false);
                new ManageEventsGUI(currentJFrame, universalHashtable);
            });
        }

        // This is the gui for customers
        private void customerGUI() {
            // First manage profile
            JButton manageProfileButton = new JButton();
            manageProfileButton.setText("Manage Profile");
            manageProfileButton.setFont(new java.awt.Font("Verdana", 1, 15));
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(manageProfileButton);
            manageProfileButton.setBounds(100, 100, 824, 50);

            JButton showRegisteredButton = new JButton();
            showRegisteredButton.setText("Show Registered Events");
            showRegisteredButton.setFont(new java.awt.Font("Verdana", 1, 15));
            showRegisteredButton.setHorizontalAlignment(SwingConstants.CENTER);
            currentJFrame.getContentPane().setLayout(null);
            currentJFrame.getContentPane().add(showRegisteredButton);
            showRegisteredButton.setBounds(100, 170, 824, 50);

            showRegisteredButton.addActionListener(e -> {
                currentJFrame.setVisible(false);
                new ShowRegisteredEventsGUI(currentJFrame, universalHashtable,universalHashtable.get("user_id"));
            });


            // Manage Profile Button should open a new window
            manageProfileButton.addActionListener(e -> {
                currentJFrame.setVisible(false);
                new ManageProfileGUI(currentJFrame, universalHashtable);

            } );

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
            eventScrollPane.setBounds(50, 250, 900, 500);

            // Fetch data
            List<Dictionary<String,String>> eventRecords = EventSchedule.getInstance().getRecords();
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
                        new RegisterEventGUI(currentJFrame, universalHashtable, event_id);
                    }
                }
            });

        }
}
