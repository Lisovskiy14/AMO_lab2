package com.example.amo_lab2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public void saveArray(int[] array) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (int i : array) {
                writer.write(i + "\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int[] loadArray() {
        // Підрахунок розміру масиву
        int arrSize = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                arrSize++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int[] array = new int[arrSize];

        // Зчитування

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            for (int i = 0; i < arrSize; i++) {
                line = reader.readLine();
                array[i] = Integer.parseInt(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return array;
    }
}
