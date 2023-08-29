package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow {
    public static void welcomeWindow() {
        SwingUtilities.invokeLater(() -> {
            JFrame welcomeFrame = new JFrame("Welcome to Cities Game");
            welcomeFrame.setSize(400, 100);
            welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            welcomeFrame.setLayout(new FlowLayout());

            JLabel welcomeLabel = new JLabel("Welcome to Cities Game! Press Start to begin.");
            JButton startButton = new JButton("Start");

            startButton.addActionListener(new ActionListener() {

                @Override
                        public void actionPerformed(ActionEvent e) {
                    welcomeFrame.setVisible(false); //Приховання вікна WelcomeWindow
                    showGameWindow();
                }


            });

            welcomeFrame.add(welcomeLabel);
            welcomeFrame.add(startButton);
            welcomeFrame.setLocationRelativeTo(null);
            welcomeFrame.setVisible(true);
        });
    }

    public static void showGameWindow() {
        JFrame gameFrame = new JFrame("Cities Game");
        gameFrame.setSize(800, 600);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());

        JTextField userInputField = new JTextField(20);
        JButton submitButton = new JButton("Make a move");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter city name: "));
        inputPanel.add(userInputField);
        inputPanel.add(submitButton);

        gameFrame.add(inputPanel, BorderLayout.NORTH);

        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
}
