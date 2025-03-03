package com.example.amo_lab2;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
    private Stage primaryStage;
    private final String ARRAY_FOLDER_PATH = "src/main/resources/arrays/";
    private final String SORTED_ARRAY_FOLDER_PATH = "src/main/resources/sorted_arrays/";

    private MyArray currentArray;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label arrNameLabel;

    @FXML
    private TextField arrNameTF;

    @FXML
    private TextField arrSizeTF;

    @FXML
    private Label countLabel;

    @FXML
    private Label sortTimeLabel;

    @FXML
    void createArr(ActionEvent event) {
        // Отримання вхідних данних
        String fileName;
        int arrSize;
        try {
            fileName = validateArrayNameInput(arrNameTF.getText());
            arrSize = validateArraySizeInput(arrSizeTF.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // Генерація масиву
        int[] array = generateArray(arrSize);

        // Збереження масиву у файл
        try {
            FileManager fileManager = new FileManager();
            fileManager.setPath(ARRAY_FOLDER_PATH + fileName + ".txt");
            fileManager.saveArray(array);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void uploadArr(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Виберіть файл з масивом");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile == null) {
            System.out.println("Файл не вибрано!");
            return;
        }
        currentArray = new MyArray();
        currentArray.setFilename(selectedFile.getName());
        System.out.println(currentArray.getFilename());

        String path = selectedFile.getPath();
        System.out.println(path);
        try {
            FileManager fileManager = new FileManager();
            fileManager.setPath(path);
            int[] array = fileManager.loadArray();
            currentArray.setArray(array);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        arrNameLabel.setText(currentArray.getFilename());
        arrNameLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        System.out.println(Arrays.toString(currentArray.getArray()));
    }

    @FXML
    void sortArr(ActionEvent event) {
        // Перевірка на присутність масиву
        if (currentArray == null) {
            System.out.println("Загрузіть файл масиву!");
            return;
        }

        // Перевірка чи масив уже відсортований
        if (currentArray.isSorted()) {
            System.out.println("Даний масив уже відсортований! Збережіть його!");
            return;
        }

        // Виклик сортування
        QuickSortClass quickSortClass = new QuickSortClass(currentArray.getArray());
        long startTime = System.currentTimeMillis();
        currentArray.setSortedArray(quickSortClass.start());
        long endTime = System.currentTimeMillis();
        long sortTime = endTime - startTime;
        int count = quickSortClass.getCount();

        System.out.println("Масив відсортовано!");

        // Виведення результатів
        sortTimeLabel.setText(sortTime + " мс");
        countLabel.setText(count + "");
    }

    @FXML
    void saveSortedArr(ActionEvent event) {
        // Перевірка чи існує масив у пам'яті
        if (currentArray == null) {
            System.out.println("Спочатку загрузіть файл масиву!");
            return;
        }

        // Перевірка чи існує відсортований масив
        if (!currentArray.isSorted()) {
            System.out.println("Спочатку відсортуйте масив!");
            return;
        }

        try {
            FileManager fileManager = new FileManager();
            fileManager.setPath(SORTED_ARRAY_FOLDER_PATH + "sorted_" + currentArray.getFilename());
            fileManager.saveArray(currentArray.getSortedArray());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Відсортований масив збережено!");
        arrNameLabel.setText("Загрузіть масив!");
        arrNameLabel.setTextFill(Color.web("#b23e3e"));
        currentArray = null;
    }

    private String validateArrayNameInput(String fileName) {
        // Перевірка введеної назви масиву
        if (!fileName.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Використовуйте латиницю у назві масиву!");
        }

        return fileName;
    }

    private int validateArraySizeInput(String arraySize) {
        // Перевірка введеного розміру масиву
        try {
            int number = Integer.parseInt(arraySize);
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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}