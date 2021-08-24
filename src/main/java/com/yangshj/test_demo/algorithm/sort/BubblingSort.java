package com.yangshj.test_demo.algorithm.sort;

import java.util.Arrays;

public class BubblingSort {


    public static void main(String[] args) {
        int[] arr = { 1, 0, 3, 4, 5, -6, 7, 8, 9, 10 };
        System.out.println("原始数据: " + Arrays.toString(arr));

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("冒泡排序: " + Arrays.toString(arr));
    }
}
