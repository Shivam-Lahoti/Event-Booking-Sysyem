package edu.neu.csye6200.GUI;

import java.awt.event.ComponentAdapter;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import edu.neu.csye6200.dataAccessing.models.TicketTypes;
import edu.neu.csye6200.dataAccessing.models.User;

public class EventBookTicketGUI extends BaseGUI {
	EventBookTicketGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable, String event_id,JFrame backpage) {
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
                         "Ticket Type ID", "Ticket Type Price", "Ticket Type Name", "Ticket Type Quantity"
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
         ticketTypeTable.setBounds((currentJFrame.getWidth() - 800) / 2, 100, 800, 300);
         currentJFrame.getContentPane().add(ticketTypeTable);

         // Then, a scroll bar
         JScrollPane ticketTypeScrollPane = new JScrollPane();
         ticketTypeScrollPane.setViewportView(ticketTypeTable);
         currentJFrame.getContentPane().add(ticketTypeScrollPane);
         ticketTypeScrollPane.setBounds((currentJFrame.getWidth() - 800) / 2, 100, 800, 300);

  
 
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
         currentJFrame.addComponentListener(new ComponentAdapter() {
             @Override
             public void componentShown(java.awt.event.ComponentEvent evt) {
                 //System.out.println("Frame is shown");
                 // Update the table
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
             }
         });

         // If pressed on a row, then go to manage ticket type gui
         ticketTypeTable.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
            	 
                 int row = ticketTypeTable.rowAtPoint(evt.getPoint());
                 int col = ticketTypeTable.columnAtPoint(evt.getPoint());
                 
                 if (row >= 0 && col >= 0) {
                     String ticket_type_id = (String) ticketTypeTable.getModel().getValueAt(row, 0);
                     Dictionary<String, String> ticketTypeTableData = TicketTypes.getInstance().getRecordById(ticket_type_id);
                     if(Integer.parseInt(ticketTypeTableData.get("how_many_tickets_in_total") )> 0) {
                     User.getInstance().updateUser(
                             universalHashtable.get("user_id"),
                             new Hashtable<String, String>() {{
                                 put("event-id", event_id);
                                 put("ticket_type_id", ticket_type_id);
 
                             }}
      
                     );
                     
                     TicketTypes.getInstance().updateTicketType(
                    		 
                    		 ticket_type_id,
                             new Hashtable<String, String>() {{
                                 put( "how_many_tickets_in_total", Integer.toString(Integer.parseInt(ticketTypeTableData.get("how_many_tickets_in_total") )- 1) );
                             }}
      
                     );
                     JOptionPane.showMessageDialog(currentJFrame, "Event Book Success!");
                     currentJFrame.setVisible(false);
                     currentJFrame.dispose();
                     new EventBookingPageGUI( backpage, universalHashtable);
                     }
                     else {
                    	 JOptionPane.showMessageDialog(currentJFrame, "No enough Ticket!"); 
                     }
                 }
             }
         });


     }
}
