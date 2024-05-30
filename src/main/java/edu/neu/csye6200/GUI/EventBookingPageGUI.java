package edu.neu.csye6200.GUI;

import java.awt.event.ComponentAdapter;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import edu.neu.csye6200.Utilities.Datetime;
import edu.neu.csye6200.dataAccessing.models.EventSchedule;

public class EventBookingPageGUI extends BaseGUI {

	EventBookingPageGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable) {
		super(previousJFrame, universalHashtable);
		// TODO Auto-generated constructor stub
		
		
		 JLabel manageEventsLabel = new JLabel();
	        manageEventsLabel.setText("Book Events");
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
	                       "Event ID", "Event Title", "Time to Start", "Time to End", "Deadline" ,
	                }
	        ) {
	            Class[] types = new Class [] {
	                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
	        List<Dictionary<String,String>> eventRecords = EventSchedule.getInstance().getAllevents();
	        if (eventRecords != null) {
	            for (Dictionary<String, String> eventRecord : eventRecords) {
	                ((javax.swing.table.DefaultTableModel) eventTable.getModel()).addRow(new Object[]{
	                        eventRecord.get("id"),
	                        eventRecord.get("event_title"),
	                        Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("time_to_start"))),
	                        Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("time_to_end"))),
	                        Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("deadline"))),
	                       
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
	                    new EventBookTicketGUI(currentJFrame, universalHashtable, event_id,previousJFrame);
	                }
	            }
	        });

	        // Then, a button to create a new event
	      

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
	                              
	                        });
	                    }
	                }
	            }
	        });

	    }
	
		
	}


