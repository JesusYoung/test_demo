package com.yangshj.test_demo.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        int[] arr = {49, 38, 65, 97, 76, 13, 27, 50};
        int[] tmp = new int[arr.length];
        System.out.println("原始数据: " + Arrays.toString(arr));
        customMergeSort(arr, tmp, 0, arr.length - 1);
        System.out.println("归并排序: " + Arrays.toString(arr));
    }

    private static void customMergeSort(int[] a, int[] tmp, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            // 对左侧子序列进行递归排序
            customMergeSort(a, tmp, start, mid);
            // 对右侧子序列进行递归排序
            customMergeSort(a, tmp, mid + 1, end);
            // 合并
            customDoubleMerge(a, tmp, start, mid, end);
        }
    }

    private static void customDoubleMerge(int[] a, int[] tmp, int left, int mid, int right) {
        int p1 = left, p2 = mid + 1, k = left;
        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2])
                tmp[k++] = a[p1++];
            else
                tmp[k++] = a[p2++];
        }
        while (p1 <= mid)
            tmp[k++] = a[p1++];
        while (p2 <= right)
            tmp[k++] = a[p2++];
        // 复制回原素组
        for (int i = left; i <= right; i++)
            a[i] = tmp[i];
    }

}
