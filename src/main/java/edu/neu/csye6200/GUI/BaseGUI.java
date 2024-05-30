package edu.neu.csye6200.GUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Hashtable;

abstract public class BaseGUI {
    // This generates a basic JFrame

    JFrame currentJFrame;
    JFrame previousJFrame;

    // This is a universal hashtable for storing data
    public static Hashtable<String, String> universalHashtable;

    BaseGUI(JFrame previousJFrame, Hashtable<String, String> universalHashtable) {
        this.previousJFrame = previousJFrame;
        if (previousJFrame != null) {
            previousJFrame.setVisible(false);
        }

        this.universalHashtable = universalHashtable;

        currentJFrame = new JFrame(); // creating instance of JFrame
        currentJFrame.setSize(1024, 768);
        currentJFrame.setLayout(null); // using no layout managers
        currentJFrame.setResizable(true);
        currentJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        currentJFrame.setVisible(true); // making the frame visible
        currentJFrame.setTitle("Event Ticket Management System");

        // Make previous window appear when this window is closed
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (previousJFrame != null) {
                    reload(previousJFrame);
                    previousJFrame.setVisible(true);
                }
            }
        };

        currentJFrame.addWindowListener(exitListener);
    }

    public void reload(JFrame theJFrame) {
        theJFrame.revalidate();
        theJFrame.repaint();
    }
}
