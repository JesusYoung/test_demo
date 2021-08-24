package com.yangshj.test_demo.algorithm.sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {

        int[] arr = { 2, 3, 5, 1, 23, 6, 78, 34 };
        System.out.println("原始数据: " + Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
        System.out.println("插入排序: " + Arrays.toString(arr));
    }
}
