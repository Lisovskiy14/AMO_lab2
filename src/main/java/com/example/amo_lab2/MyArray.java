package com.example.amo_lab2;

public class MyArray {
    private int[] array;
    private String filename;
    private int[] sortedArray;

    public void setArray(int[] array) {
        this.array = array;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setSortedArray(int[] sortedArray) {
        this.sortedArray = sortedArray;
    }

    public int[] getArray() {
        return array;
    }

    public String getFilename() {
        return filename;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    public boolean isSorted() {
        return !(sortedArray == null);
    }
}
