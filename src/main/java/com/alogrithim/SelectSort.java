package com.alogrithim;

/**选择排序*/
import com.utils.ArraysPrint;

public class SelectSort {

    public static void main(String[] args) {
	int[] arr = { 29, 2, 45, 3, 5, 88, 66, 59, 34 };
	ArraysPrint.printf(arr);
	SelectSort.selectSort(arr);
	ArraysPrint.printf(arr);
    }

    public static void selectSort(int[] arr) {
	int len = arr.length;
	for (int i = 0; i < len; i++) {
	    int min_index = i;
	    int min = arr[i];
	    for (int j = i; j < len; j++) {
		if (arr[j] < min) {
		    min = arr[j];
		    min_index = j;
		}
	    }

	    if (min_index != i) {
		int temp = arr[min_index];
		arr[min_index] = arr[i];
		arr[i] = temp;
	    }
	}

    }
}
