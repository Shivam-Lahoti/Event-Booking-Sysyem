package edu.neu.csye6200.GUI;

import edu.neu.csye6200.Utilities.Datetime;
import edu.neu.csye6200.dataAccessing.models.EventSchedule;

import javax.swing.*;
import java.util.Hashtable;

public class CreateEventGUI extends BaseGUI {
    public CreateEventGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable) {
        super(previousJFrame, universalHashtable);


        // Here you can create an event
        // First, the page title
        JLabel createEventLabel = new JLabel();
        createEventLabel.setText("Create Event");
        createEventLabel.setFont(new java.awt.Font("Verdana", 1, 25));
        createEventLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(createEventLabel);
        createEventLabel.setBounds(0, 0, 1024, 50);

        // Then, a form to fill in the event information
        // Event Name
        JLabel eventNameLabel = new JLabel();
        eventNameLabel.setText("Event Event Name: *");
        eventNameLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventNameLabel);
        eventNameLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 100, 200, 25);

        JTextField eventNameField = new JTextField();
        eventNameField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventNameField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventNameField);
        eventNameField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 100, 200, 25);

        // Event Description
        JLabel eventDescriptionLabel = new JLabel();
        eventDescriptionLabel.setText("Event Description:");
        eventDescriptionLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventDescriptionLabel);
        eventDescriptionLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 200, 200, 25);

        JTextField eventDescriptionField = new JTextField();
        eventDescriptionField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventDescriptionField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventDescriptionField);
        eventDescriptionField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 200, 200, 25);

        // Event Location
        JLabel eventLocationLabel = new JLabel();
        eventLocationLabel.setText("Event Location:");
        eventLocationLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        eventLocationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventLocationLabel);
        eventLocationLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 300, 200, 25);

        JTextField eventLocationField = new JTextField();
        eventLocationField.setFont(new java.awt.Font("Verdana", 1, 15));
        eventLocationField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(eventLocationField);
        eventLocationField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 300, 200, 25);


        // A line to tell the user that the time format is yyyy-MM-dd HH:mm:ss
        JLabel timeFormatLabel = new JLabel();
        timeFormatLabel.setText("Time Format: yyyy-MM-dd HH:mm:ss");
        timeFormatLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        timeFormatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(timeFormatLabel);
        timeFormatLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 400, 500, 25);

        // Start Time
        JLabel startTimeLabel = new JLabel();
        startTimeLabel.setText("Start Time: *");
        startTimeLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        startTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(startTimeLabel);
        startTimeLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 450, 200, 25);

        JTextField startTimeField = new JTextField();
        startTimeField.setFont(new java.awt.Font("Verdana", 1, 15));
        startTimeField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(startTimeField);
        startTimeField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 450, 200, 25);

        // End Time
        JLabel endTimeLabel = new JLabel();
        endTimeLabel.setText("End Time: *");
        endTimeLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        endTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(endTimeLabel);
        endTimeLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 500, 200, 25);

        JTextField endTimeField = new JTextField();
        endTimeField.setFont(new java.awt.Font("Verdana", 1, 15));
        endTimeField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(endTimeField);
        endTimeField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 500, 200, 25);

        // Deadline
        JLabel deadlineLabel = new JLabel();
        deadlineLabel.setText("Deadline: *");
        deadlineLabel.setFont(new java.awt.Font("Verdana", 1, 15));
        deadlineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(deadlineLabel);
        deadlineLabel.setBounds((currentJFrame.getWidth() - 500) / 2, 550, 200, 25);

        JTextField deadlineField = new JTextField();
        deadlineField.setFont(new java.awt.Font("Verdana", 1, 15));
        deadlineField.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(deadlineField);
        deadlineField.setBounds((currentJFrame.getWidth() - 500) / 2 + 200, 550, 200, 25);



        // Create Event Button
        JButton createEventButton = new JButton();
        createEventButton.setText("Create Event");
        createEventButton.setFont(new java.awt.Font("Verdana", 1, 15));
        createEventButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(createEventButton);
        createEventButton.setBounds((currentJFrame.getWidth() - 500) / 2, 700, 400, 25);

        // If pressed create event, then create the event
        createEventButton.addActionListener(e -> {
            // First check if the username and password are correct
            String eventName = eventNameField.getText();
            String eventDescription = eventDescriptionField.getText();
            String eventLocation = eventLocationField.getText();
            String startTime = startTimeField.getText();
            String endTime = endTimeField.getText();
            String deadline = deadlineField.getText();

            // trim the strings
            eventName = eventName.trim();
            eventDescription = eventDescription.trim();
            eventLocation = eventLocation.trim();
            startTime = startTime.trim();
            endTime = endTime.trim();
            deadline = deadline.trim();

            // check if the time format is correct
            if (!Datetime.isValidDatetime(startTime) || !Datetime.isValidDatetime(endTime) || !Datetime.isValidDatetime(deadline)) {
                JOptionPane.showMessageDialog(currentJFrame, "Please enter the time in the correct format.");
                return;
            }

            // check if the start time is before the end time
            if (Datetime.turn_yyyyMMddHHmmss_into_LocalDateTime(startTime).isAfter(Datetime.turn_yyyyMMddHHmmss_into_LocalDateTime(endTime))) {
                JOptionPane.showMessageDialog(currentJFrame, "The start time should be before the end time.");
                return;
            }


            // If correct, then open the main window
            if (eventName.equals("") || startTime.equals("") || endTime.equals("") || deadline.equals("")) {
                JOptionPane.showMessageDialog(currentJFrame, "Please fill in all the required fields.");
            } else {
                // Create the event
                String new_event_id = EventSchedule.getInstance().insertEventSchedule(
                        universalHashtable.get("user_id"),
                        eventName,
                        Datetime.localDateTime_toString(Datetime.turn_yyyyMMddHHmmss_into_LocalDateTime(startTime)),
                        Datetime.localDateTime_toString(Datetime.turn_yyyyMMddHHmmss_into_LocalDateTime(endTime)),
                        Datetime.localDateTime_toString(Datetime.turn_yyyyMMddHHmmss_into_LocalDateTime(deadline)),
                        Datetime.currentTime_toString(),
                        eventDescription,
                        eventLocation
                );
                if (new_event_id != null) {
                    JOptionPane.showMessageDialog(currentJFrame, "Event created successfully. Now we'll manage the tickets for this event.");
                    currentJFrame.dispose();
                    new ManageTicketGUI(previousJFrame, universalHashtable, new_event_id);
                } else {
                    JOptionPane.showMessageDialog(currentJFrame, "Event creation failed.");
                }
            }
        });







        // Cancel Button
        JButton cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFont(new java.awt.Font("Verdana", 1, 15));
        cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
        currentJFrame.getContentPane().setLayout(null);
        currentJFrame.getContentPane().add(cancelButton);
        cancelButton.setBounds((currentJFrame.getWidth() - 500) / 2, 750, 400, 25);

        // If pressed cancel, then go back to manage events gui
        cancelButton.addActionListener(e -> {
            currentJFrame.setVisible(false);
            new ManageEventsGUI(currentJFrame, universalHashtable);
        });


    }
}