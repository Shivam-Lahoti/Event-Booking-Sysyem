package edu.neu.csye6200.GUI;

import edu.neu.csye6200.Utilities.Datetime;
import edu.neu.csye6200.dataAccessing.models.EventRegistration;
import edu.neu.csye6200.dataAccessing.models.EventSchedule;
import edu.neu.csye6200.dataAccessing.models.TicketTypes;

import javax.swing.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

public class ManageEventGUI extends BaseGUI{
    public ManageEventGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable, String event_id) {
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
        eventTitleLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 50, 800, 50);

        JTextField eventTitleTextField = new JTextField();
        eventTitleTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTitleTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventTitleTextField.setText("Event Title");
        currentJFrame.getContentPane().add(eventTitleTextField);
        eventTitleTextField.setBounds((currentJFrame.getWidth() - 800) / 2, 100, 800, 50);

        JLabel eventDescriptionLabel = new JLabel();
        eventDescriptionLabel.setText("Event Description:");
        eventDescriptionLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventDescriptionLabel);
        eventDescriptionLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 150, 800, 50);

        JTextField eventDescriptionTextField = new JTextField();
        eventDescriptionTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventDescriptionTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventDescriptionTextField.setText("Event Description");
        currentJFrame.getContentPane().add(eventDescriptionTextField);
        eventDescriptionTextField.setBounds((currentJFrame.getWidth() - 800) / 2, 200, 800, 50);

        JLabel eventLocationLabel = new JLabel();
        eventLocationLabel.setText("Event Location:");
        eventLocationLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventLocationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventLocationLabel);
        eventLocationLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 250, 800, 50);

        JTextField eventLocationTextField = new JTextField();
        eventLocationTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventLocationTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventLocationTextField.setText("Event Location");
        currentJFrame.getContentPane().add(eventLocationTextField);
        eventLocationTextField.setBounds((currentJFrame.getWidth() - 800) / 2, 300, 800, 50);

        JLabel eventTimeToStartLabel = new JLabel();
        eventTimeToStartLabel.setText("Event Time to Start: *");
        eventTimeToStartLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTimeToStartLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventTimeToStartLabel);
        eventTimeToStartLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 350, 800, 50);

        JTextField eventTimeToStartTextField = new JTextField();
        eventTimeToStartTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTimeToStartTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventTimeToStartTextField.setText("Event Time to Start");
        currentJFrame.getContentPane().add(eventTimeToStartTextField);
        eventTimeToStartTextField.setBounds((currentJFrame.getWidth() - 800) / 2, 400, 800, 50);

        JLabel eventTimeToEndLabel = new JLabel();
        eventTimeToEndLabel.setText("Event Time to End: *");
        eventTimeToEndLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTimeToEndLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventTimeToEndLabel);
        eventTimeToEndLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 450, 800, 50);

        JTextField eventTimeToEndTextField = new JTextField();
        eventTimeToEndTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventTimeToEndTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventTimeToEndTextField.setText("Event Time to End");
        currentJFrame.getContentPane().add(eventTimeToEndTextField);
        eventTimeToEndTextField.setBounds((currentJFrame.getWidth() - 800) / 2, 500, 800, 50);

        JLabel eventDeadlineLabel = new JLabel();
        eventDeadlineLabel.setText("Event Deadline: *");
        eventDeadlineLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventDeadlineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventDeadlineLabel);
        eventDeadlineLabel.setBounds((currentJFrame.getWidth() - 800) / 2, 550, 800, 50);

        JTextField eventDeadlineTextField = new JTextField();
        eventDeadlineTextField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventDeadlineTextField.setHorizontalAlignment(SwingConstants.CENTER);
        eventDeadlineTextField.setText("Event Deadline");
        currentJFrame.getContentPane().add(eventDeadlineTextField);
        eventDeadlineTextField.setBounds((currentJFrame.getWidth() - 800) / 2, 600, 800, 50);

        // The buttons should be in the same row, side by side
        int buttonWidth = 200;
        int gapBetweenButtons = 10; // Adjust this value based on the gap you want between buttons

//        button1.setBounds((currentJFrame.getWidth() - (4 * buttonWidth + 3 * gapBetweenButtons)) / 2, 600, buttonWidth, 50);
//        button2.setBounds(button1.getX() + buttonWidth + gapBetweenButtons, 600, buttonWidth, 50);
//        button3.setBounds(button2.getX() + buttonWidth + gapBetweenButtons, 600, buttonWidth, 50);
//        button4.setBounds(button3.getX() + buttonWidth + gapBetweenButtons, 600, buttonWidth, 50);


        // Then, a button to manage ticket types
        JButton manageTicketButton = new JButton();
        manageTicketButton.setText("Manage Tickets");
        manageTicketButton.setFont(new java.awt.Font("Verdana", 1, 15));
        manageTicketButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(manageTicketButton);
        manageTicketButton.setBounds((currentJFrame.getWidth() - (4 * buttonWidth + 3 * gapBetweenButtons)) / 2, 650, buttonWidth, 50);

        // Then, a button to update the event information
        JButton updateEventButton = new JButton();
        updateEventButton.setText("Update Event");
        updateEventButton.setFont(new java.awt.Font("Verdana", 1, 15));
        updateEventButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(updateEventButton);
        updateEventButton.setBounds(manageTicketButton.getX() + buttonWidth + gapBetweenButtons, 650, buttonWidth, 50);


        // Then, a button to delete the event
        JButton deleteEventButton = new JButton();
        deleteEventButton.setText("Delete Event");
        deleteEventButton.setFont(new java.awt.Font("Verdana", 1, 15));
        deleteEventButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(deleteEventButton);
        deleteEventButton.setBounds(updateEventButton.getX() + buttonWidth + gapBetweenButtons, 650, buttonWidth, 50);

        // Then, a button to go back to the previous page
        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.setFont(new java.awt.Font("Verdana", 1, 15));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(backButton);
        backButton.setBounds(deleteEventButton.getX() + buttonWidth + gapBetweenButtons, 650, buttonWidth, 50);

        // Add action listeners
        manageTicketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Go to the manage ticket page
                ManageTicketGUI manageTicketGUI = new ManageTicketGUI(currentJFrame, universalHashtable, event_id);
            }
        });

        updateEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Update the event information
                // First, check if the information is valid
                // Then, update the event information
                // Then, go back to the previous page

                // Get the information from the form
                String event_title = eventTitleTextField.getText();
                String event_description = eventDescriptionTextField.getText();
                String event_location = eventLocationTextField.getText();
                String event_time_to_start = eventTimeToStartTextField.getText();
                String event_time_to_end = eventTimeToEndTextField.getText();
                String event_deadline = eventDeadlineTextField.getText();

                // trim
                event_title = event_title.trim();
                event_description = event_description.trim();
                event_location = event_location.trim();
                event_time_to_start = event_time_to_start.trim();
                event_time_to_end = event_time_to_end.trim();
                event_deadline = event_deadline.trim();

                // Check if the information is valid
                if (event_title.equals("")) {
                    JOptionPane.showMessageDialog(currentJFrame, "Event Title cannot be empty!");
                    return;
                }
                if (event_time_to_start.equals("")) {
                    JOptionPane.showMessageDialog(currentJFrame, "Event Time to Start cannot be empty!");
                    return;
                }
                if (event_time_to_end.equals("")) {
                    JOptionPane.showMessageDialog(currentJFrame, "Event Time to End cannot be empty!");
                    return;
                }
                if (event_deadline.equals("")) {
                    JOptionPane.showMessageDialog(currentJFrame, "Event Deadline cannot be empty!");
                    return;
                }

                // Check if the time is valid
                if (!Datetime.isValidDatetime(event_time_to_start)) {
                    JOptionPane.showMessageDialog(currentJFrame, "Event Time to Start is not valid!");
                    return;
                }
                if (!Datetime.isValidDatetime(event_time_to_end)) {
                    JOptionPane.showMessageDialog(currentJFrame, "Event Time to End is not valid!");
                    return;
                }
                if (!Datetime.isValidDatetime(event_deadline)) {
                    JOptionPane.showMessageDialog(currentJFrame, "Event Deadline is not valid!");
                    return;
                }

                // Check if the time is reasonable
                if (Datetime.turn_yyyyMMddHHmmss_into_LocalDateTime(event_time_to_start).isAfter(Datetime.turn_yyyyMMddHHmmss_into_LocalDateTime(event_time_to_end))) {
                    JOptionPane.showMessageDialog(currentJFrame, "Event Time to Start cannot be after Event Time to End!");
                    return;
                }

                // Update the event information
                String finalEvent_title = event_title;
                String finalEvent_description = event_description;
                String finalEvent_location = event_location;
                String finalEvent_time_to_start = event_time_to_start;
                String finalEvent_time_to_end = event_time_to_end;
                String finalEvent_deadline = event_deadline;
                if (EventSchedule.getInstance().updateEventSchedule(
                        event_id,
                        new Hashtable<String, String>(){{
                            put("event_title", finalEvent_title);
                            put("description", finalEvent_description);
                            put("location", finalEvent_location);
                            put("time_to_start", Datetime.localDateTime_toString(Datetime.turn_yyyyMMddHHmmss_into_LocalDateTime(finalEvent_time_to_start)));
                            put("time_to_end", Datetime.localDateTime_toString(Datetime.turn_yyyyMMddHHmmss_into_LocalDateTime(finalEvent_time_to_end)));
                            put("deadline", Datetime.localDateTime_toString(Datetime.turn_yyyyMMddHHmmss_into_LocalDateTime(finalEvent_deadline)));
                        }})
                ) {
                    JOptionPane.showMessageDialog(currentJFrame, "Event Updated Successfully!");
                } else {
                    JOptionPane.showMessageDialog(currentJFrame, "Event Updated Failed!");
                }
            }
        });

        deleteEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Then delete the event
                int confirmation = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this event?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    for (Dictionary<String, String> temp : TicketTypes.getInstance().getRecordsByEventId(event_id)) {
                        EventRegistration.getInstance().deleteEventRegistrationRecord(temp.get("id"));
                        TicketTypes.getInstance().deleteTicketType(temp.get("id"));
                    }
                    EventSchedule.getInstance().deleteEventSchedule(event_id);
                    currentJFrame.setVisible(false);
                    previousJFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Event not deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
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

        // Fetch data
        Dictionary<String, String> eventRecord = EventSchedule.getInstance().getRecordById(event_id);
        if (eventRecord != null) {
            eventTitleTextField.setText(eventRecord.get("event_title"));
            eventDescriptionTextField.setText(eventRecord.get("description"));
            eventLocationTextField.setText(eventRecord.get("location"));
            eventTimeToStartTextField.setText(Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("time_to_start"))));
            eventTimeToEndTextField.setText(Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("time_to_end"))));
            eventDeadlineTextField.setText(Datetime.turnLocalDateTimeIntoFriendlyString(Datetime.string_toLocalDateTime(eventRecord.get("deadline"))));
        }
    }
}
