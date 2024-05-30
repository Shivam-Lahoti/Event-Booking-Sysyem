package edu.neu.csye6200.GUI;

import javax.swing.*;

public class Test {
    private JPanel ThePanel;
    private JTextField WWTextField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
BaseGUI a =new LoginGUI();
        frame.setContentPane(new Test().ThePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
