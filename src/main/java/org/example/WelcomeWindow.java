package org.example;

import javax.swing.*;
import java.awt.*;

public class WelcomeWindow {
    public static void welcomeWindow() {
        SwingUtilities.invokeLater(() -> {
            JFrame welcomeFrame = new JFrame("Welcome to Cities Game");
            welcomeFrame.setSize(400, 100);
            welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            welcomeFrame.setLayout(new FlowLayout());

            JLabel welcomeLabel = new JLabel("Welcome to Cities Game! Press Start to begin.");
            JButton startButton = new JButton("Start");

            startButton.addActionListener(e -> {
                welcomeFrame.setVisible(false);
                // Тут ви можете викликати метод для відображення гри
            });

            welcomeFrame.add(welcomeLabel);
            welcomeFrame.add(startButton);
            welcomeFrame.setLocationRelativeTo(null);
            welcomeFrame.setVisible(true);
        });
    }
}
