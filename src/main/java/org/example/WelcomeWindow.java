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
    private static int playerScore = 0;
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
    //показується вікно гри
    public static void showGameWindow() {
        JFrame gameFrame = new JFrame("Cities Game");
        gameFrame.setSize(600, 400);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());

        JTextField userInputField = new JTextField(20);
        JButton submitButton = new JButton("Make a move");
        JLabel computerResponseLabel = new JLabel();

        // Ініціалізація DefaultListModel
        DefaultListModel<String> citiesListModel = new DefaultListModel<>();
        JList<String> citiesList = new JList<>(citiesListModel);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter city name: "));
        inputPanel.add(userInputField);
        inputPanel.add(submitButton);

        gameFrame.add(inputPanel, BorderLayout.NORTH);
        gameFrame.add(computerResponseLabel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {



            // метод, який показує що відбувається при натисканні на "Make a move"
            @Override
            public void actionPerformed(ActionEvent e) {
                //отримання введеного імені з поля userInputField, видалення пробілів
                String userCity = userInputField.getText().trim();
                if (!userCity.isEmpty()) {
                    //отримання останньої літери введеного міста
                    char lastLetter = userCity.charAt(userCity.length() - 1);
                    String filePath = "C:\\Users\\Finance\\IdeaProjects\\project_cities\\src\\cities.txt";
                    String computerCity = getComputerCity(lastLetter, filePath);

                    computerResponseLabel.setText("Computer's response: " + computerCity);

                    citiesListModel.addElement(computerCity);
                    updatePlayerScore(); // Оновлення рахунку гравця
                }
            }
        });


        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    public static void updatePlayerScore() {
        playerScore++; // Рахунок гравця збільшується на 1 при вірній відповіді
        JFrame scoreFrame = new JFrame("Player Score");
        scoreFrame.setSize(200, 100);
        scoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        scoreFrame.setLayout(new FlowLayout());

        JLabel scoreLabel = new JLabel("Player Score: " + playerScore);
        scoreFrame.add(scoreLabel);
        scoreFrame.setLocationRelativeTo(null);
        scoreFrame.setVisible(true);
    }

    public static String getComputerCity(char lastLetter, String filePath) {
        List<String> cities = loadCities(filePath);
        List<String> availableCities = new ArrayList<>();

        lastLetter = Character.toLowerCase(lastLetter); // Перетворити останню літеру на нижній регістр

        //перевірка наявності доступу та правильного шляху до файлу cities
//        System.out.println("Loading cities:");
//        for (String city : cities) {
//            System.out.println(city);
//        }

        for (String city : cities) {

            if (Character.toLowerCase(city.charAt(0)) == lastLetter) {
                availableCities.add(city);

            }
        }
        //тут використовую Math аби не виникало проблем з довжиною списку після видалення міста
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

