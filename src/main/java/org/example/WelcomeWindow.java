package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        gameFrame.setSize(600, 400);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());

        JTextField userInputField = new JTextField(20);
        JButton submitButton = new JButton("Make a move");
        JLabel computerResponseLabel = new JLabel();

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter city name: "));
        inputPanel.add(userInputField);
        inputPanel.add(submitButton);

        gameFrame.add(inputPanel, BorderLayout.NORTH);
        gameFrame.add(computerResponseLabel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userCity = userInputField.getText().trim();
                if (!userCity.isEmpty()) {
                    char lastLetter = userCity.charAt(userCity.length() - 1);
                    String filePath = "C:\\Users\\Finance\\IdeaProjects\\project_cities\\src\\main\\java\\org\\example\\cities.txt";
                    String computerCity = getComputerCity(lastLetter, "cities.txt");
                    computerResponseLabel.setText("Computer's response: " + computerCity);
                }
            }
        });


        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    public static String getComputerCity(char lastLetter, String filePath) {
        List<String> cities = loadCities(filePath);
        List<String> availableCities = new ArrayList<>();

        for (String city : cities) {

            if (city.charAt(0) == lastLetter) {
                availableCities.add(city);

            }
        }

        if (!availableCities.isEmpty()) {
            int selectedIndex = (int) (Math.random() * availableCities.size());
            String selectedCity = availableCities.get(selectedIndex);
            cities.remove(selectedCity);
            return selectedCity;
        } else {
            return "No city found";
        }

    }
//    метод для завантаження міст
    public static List<String> loadCities(String filePath) {
        List<String> cities = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                cities.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

}
