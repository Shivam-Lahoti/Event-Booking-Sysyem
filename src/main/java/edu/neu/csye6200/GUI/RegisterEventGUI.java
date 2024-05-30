package edu.neu.csye6200.GUI;

import edu.neu.csye6200.Services.AccountService;
import edu.neu.csye6200.Utilities.Datetime;
import edu.neu.csye6200.dataAccessing.models.EventRegistration;
import edu.neu.csye6200.dataAccessing.models.EventSchedule;
import edu.neu.csye6200.dataAccessing.models.TicketTypes;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class RegisterEventGUI extends BaseGUI{
    public RegisterEventGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable, String event_id) {
        super(previousJFrame, universalHashtable);

        // Here you can manage an event
        // First, the page title
        JLabel manageEventLabel = new JLabel();
        manageEventLabel.setText("Manage Event");
        manageEventLabel.setFont(new java.awt.Font("Verdana", 1, 25));
        manageEventLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(manageEventLabel);
        manageEventLabel.setBounds(0, 0, 1024, 50);

        // Then, a form to fill in the event information, similar to the create event page, but the information is already filled in, and there is a button to manage ticket types
        // The form contains event_title, event_description, event_location, event_time_to_start, event_time_to_end, event_deadline
        JLabel eventTitleLabel = new JLabel();
        eventTitleLabel.setText("Event Title: *");
        eventTitleLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventTitleLabel);
        eventTitleLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 50, eventTitleLabel.getPreferredSize().width, 50);

        JLabel eventTitleTextField = new JLabel();
        eventTitleTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTitleTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventTitleTextField.setText("Event Title");
        currentJFrame.getContentPane().add(eventTitleTextField);
        eventTitleTextField.setBounds(eventTitleLabel.getX() + eventTitleLabel.getWidth() + 50, 50, eventTitleTextField.getPreferredSize().width, 50);

        JLabel eventDescriptionLabel = new JLabel();
        eventDescriptionLabel.setText("Event Description:");
        eventDescriptionLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventDescriptionLabel);
        eventDescriptionLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 100, eventDescriptionLabel.getPreferredSize().width, 50);

        JLabel eventDescriptionTextField = new JLabel();
        eventDescriptionTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventDescriptionTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventDescriptionTextField.setText("Event Description");
        currentJFrame.getContentPane().add(eventDescriptionTextField);
        eventDescriptionTextField.setBounds(eventDescriptionLabel.getX() + eventDescriptionLabel.getWidth() + 50, 100, eventDescriptionTextField.getPreferredSize().width, 50);

        JLabel eventLocationLabel = new JLabel();
        eventLocationLabel.setText("Event Location:");
        eventLocationLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventLocationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventLocationLabel);
        eventLocationLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 150, eventLocationLabel.getPreferredSize().width, 50);

        JLabel eventLocationTextField = new JLabel();
        eventLocationTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventLocationTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventLocationTextField.setText("Event Location");
        currentJFrame.getContentPane().add(eventLocationTextField);
        eventLocationTextField.setBounds(eventLocationLabel.getX() + eventLocationLabel.getWidth() + 50, 150, eventLocationTextField.getPreferredSize().width, 50);

        JLabel eventTimeToStartLabel = new JLabel();
        eventTimeToStartLabel.setText("Event Time to Start: *");
        eventTimeToStartLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTimeToStartLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventTimeToStartLabel);
        eventTimeToStartLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 200, eventTimeToStartLabel.getPreferredSize().width, 50);

        JLabel eventTimeToStartTextField = new JLabel();
        eventTimeToStartTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTimeToStartTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventTimeToStartTextField.setText("Event Time to Start");
        currentJFrame.getContentPane().add(eventTimeToStartTextField);
        eventTimeToStartTextField.setBounds(eventTimeToStartLabel.getX() + eventTimeToStartLabel.getWidth() + 50, 200, eventTimeToStartTextField.getPreferredSize().width, 50);

        JLabel eventTimeToEndLabel = new JLabel();
        eventTimeToEndLabel.setText("Event Time to End: *");
        eventTimeToEndLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTimeToEndLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventTimeToEndLabel);
        eventTimeToEndLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 250, eventTimeToEndLabel.getPreferredSize().width, 50);

        JLabel eventTimeToEndTextField = new JLabel();
        eventTimeToEndTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTimeToEndTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventTimeToEndTextField.setText("Event Time to End");
        currentJFrame.getContentPane().add(eventTimeToEndTextField);
        eventTimeToEndTextField.setBounds(eventTimeToEndLabel.getX() + eventTimeToEndLabel.getWidth() + 50, 250, eventTimeToEndTextField.getPreferredSize().width, 50);

        JLabel eventDeadlineLabel = new JLabel();
        eventDeadlineLabel.setText("Event Deadline: *");
        eventDeadlineLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventDeadlineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventDeadlineLabel);
        eventDeadlineLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 300, eventDeadlineLabel.getPreferredSize().width, 50);

        JLabel eventDeadlineTextField = new JLabel();
        eventDeadlineTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventDeadlineTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventDeadlineTextField.setText("Event Deadline");
        currentJFrame.getContentPane().add(eventDeadlineTextField);
        eventDeadlineTextField.setBounds(eventDeadlineLabel.getX() + eventDeadlineLabel.getWidth() + 50, 300, eventDeadlineTextField.getPreferredSize().width, 50);

        JLabel eventTicketTypeLabel = new JLabel();
        eventTicketTypeLabel.setText("Event Deadline: *");
        eventTicketTypeLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTicketTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventTicketTypeLabel);
        eventTicketTypeLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 350, eventTicketTypeLabel.getPreferredSize().width, 50);

        class ComboBoxData {
            private String id;
            private String name;

            public ComboBoxData(String id, String name){
                this.id=id;
                this.name=name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return getId();

            }

        }
        class MyObjectListCellRenderer extends DefaultListCellRenderer {

            public Component getListCellRendererComponent(
                    JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {
                if (value instanceof ComboBoxData) {
                    value = ((ComboBoxData)value).getName();
                }
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                return this;
            }
        }


        JComboBox<ComboBoxData> eventTicketTypeField = new JComboBox<>();
        eventTicketTypeField.setFont(new java.awt.Font("Verdana", 1, 15));
        currentJFrame.getContentPane().add(eventTicketTypeField);
        eventTicketTypeField.setRenderer(new MyObjectListCellRenderer());
        eventTicketTypeField.setBounds(eventTicketTypeLabel.getX() + eventTicketTypeLabel.getWidth() + 50, 350, 200, 50);

        int buttonWidth = 200;
        int gapBetweenButtons = 10; // Adjust this value based on the gap you want between buttons

        // Then, a button to manage ticket types
        JButton registerTicketButton = new JButton();
        registerTicketButton.setText("Register");
        registerTicketButton.setFont(new java.awt.Font("Verdana", 1, 15));
        registerTicketButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(registerTicketButton);
        registerTicketButton.setBounds((currentJFrame.getWidth() - (4 * buttonWidth + 3 * gapBetweenButtons)) / 2, 400, buttonWidth, 50);

        // Then, a button to go back to the previous page
        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.setFont(new java.awt.Font("Verdana", 1, 15));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(backButton);
        backButton.setBounds(registerTicketButton.getX() + buttonWidth + gapBetweenButtons, 400, buttonWidth, 50);


        // Fetch data
        Dictionary<String, String> eventRecord = EventSchedule.getInstance().getRecordById(event_id);
        List<Dictionary<String, String>> eventTicketTypes = TicketTypes.getInstance().getRecordsByEventId(event_id);
        if (eventRecord != null) {
            System.out.println(eventTicketTypes);
            Iterator<Dictionary<String, String>> ticketTypesIt = eventTicketTypes.iterator();
            while (ticketTypesIt.hasNext()) {
                Dictionary<String,String> temp = ticketTypesIt.next();
                eventTicketTypeField.addItem(new ComboBoxData(temp.get("id"), temp.get("name")));
            }
            eventTitleTextField.setText(eventRecord.get("event_title"));
            eventDescriptionTextField.setText(eventRecord.get("description"));
            eventLocationTextField.setText(eventRecord.get("location"));
            eventTimeToStartTextField.setText(Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("time_to_start"))));
            eventTimeToEndTextField.setText(Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("time_to_end"))));
            eventDeadlineTextField.setText(Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("deadline"))));
        }

        registerTicketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Go back to the previous page
                boolean val= EventRegistration.getInstance().insertEventRegistrationRecord(
                        universalHashtable.get("user_id"),
                        eventTicketTypeField.getSelectedItem().toString(),
                        true,
                        "",
                        Datetime.localDateTime_toString(LocalDateTime.now())
                );
                if (val){
                    JOptionPane.showMessageDialog(currentJFrame, "Registered for event successfully");
                    currentJFrame.setVisible(false);
                    previousJFrame.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(currentJFrame, "Failed to register for event");
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
    }
}
