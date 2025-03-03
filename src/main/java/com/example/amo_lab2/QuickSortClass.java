package com.example.amo_lab2;

public class QuickSortClass {
    private final int[] array;
    private int count = 0;

    public QuickSortClass(int[] array) {
        this.array = array;
    }

    public int getCount() {
        return count;
    }

    public int[] start() {
        int low = 0;
        int high = array.length - 1;
        count += 2;

        quickSort(array, low, high);

        return array;
    }

    private void quickSort(int[] arr, int low, int high) {
        count++;
        if (low >= high) {
            return;
        }

        int piv = arr[high];
        int wall = low;
        count += 2;

        count++;
        for (int i = low; i < high; i++) {
            if (arr[i] <= piv) {
                int temp = arr[i];
                arr[i] = arr[wall];
                arr[wall] = temp;
                wall++;
                count += 5;
            }
            count += 2;
        }


        arr[high] = arr[wall];
        arr[wall] = piv;
        count += 2;

        count++;
        quickSort(arr, low, wall - 1);
        count++;
        quickSort(arr, wall + 1, high);
    }
}
