package com.alogrithim;

import com.utils.ArraysPrint;

public class MergeSortClient {

    public static void main(String[] args) {
	MergeSort qs = new MergeSort();
	int[] arr = { 29, 2, 45, 3, 5, 88, 66, 59, 34 };
	ArraysPrint.printf(arr);
	qs.merge(arr, 0, arr.length - 1);
	ArraysPrint.printf(arr);

    }

}
