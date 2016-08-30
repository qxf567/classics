package com.alogrithim;

import com.utils.ArraysPrint;

public class QuickSortClient {

    public static void main(String[] args) {
	QuickSort qs = new QuickSort();
	int[] arr = { 29, 2, 45, 3, 5, 88, 66, 59, 34 };
	ArraysPrint.printf(arr);
	qs.quickSort(arr, 0, arr.length - 1);
	ArraysPrint.printf(arr);
    }
}
