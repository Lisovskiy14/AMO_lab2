package com.example.amo_lab2;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label countLabel;

    @FXML
    private TextField NTextField;

    @FXML
    private Button sortButton;

    @FXML
    private Label sortTimeLabel;

    @FXML
    void sortButtonClick(ActionEvent event) {
        // Отримання числа
        int N;
        try {
            N = validateInput(NTextField.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // Генерація масиву
        int[] array = generateArray(N);

        // Виклик сортування
        QuickSortClass quickSortClass = new QuickSortClass(array);
        long startTime = System.currentTimeMillis();
        int[] sortedArray = quickSortClass.start();
        long endTime = System.currentTimeMillis();
        long sortTime = endTime - startTime;
        int count = quickSortClass.getCount();

        // Виведення результатів
        sortTimeLabel.setText(sortTime + " мс");
        countLabel.setText(count + "");
    }

    private int validateInput(String input) {
        try {
            int number = Integer.parseInt(input);
            if (number < 1) {
                throw new NumberFormatException("Введіть коректний розмір масиву!");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Введіть число!");
        }
    }

    private int[] generateArray(int N) {
        int[] array = new int[N];

        Random random = new Random();
        int maxNumber;
        if (N < 10) {
            maxNumber = 10;
        } else {
            maxNumber = N;
        }
        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(maxNumber);
        }

        return array;
    }

    @FXML
    void initialize() {
    }
}