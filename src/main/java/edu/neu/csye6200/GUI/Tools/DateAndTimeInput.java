package edu.neu.csye6200.GUI.Tools;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

// This is a tool for inputting date and time, it will pop up a window for you to select
// For year, month, day, hour, minute, second, they are all seperate menu items
public class DateAndTimeInput {

    public DateAndTimeInput() {
        JFrame frame = new JFrame("Date and Time Picker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create a date spinner
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        panel.add(dateSpinner);

        // Create a time spinner
        SpinnerDateModel timeModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        panel.add(timeSpinner);

        // Create a button to get the selected date and time
        JButton button = new JButton("Get Date and Time");
        button.addActionListener(e -> {
            Date selectedDate = (Date) dateSpinner.getValue();
            Date selectedTime = (Date) timeSpinner.getValue();

            LocalDateTime localDateTime = LocalDateTime.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault());
            LocalDateTime time = LocalDateTime.ofInstant(selectedTime.toInstant(), ZoneId.systemDefault());

            localDateTime = localDateTime.withHour(time.getHour())
                    .withMinute(time.getMinute())
                    .withSecond(0)
                    .withNano(0);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDateTime = localDateTime.format(formatter);
            JOptionPane.showMessageDialog(frame, "Selected Date and Time: " + formattedDateTime);
        });
        panel.add(button);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DateAndTimeInput();
    }

}
