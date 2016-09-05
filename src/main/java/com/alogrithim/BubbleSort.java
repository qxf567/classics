package com.alogrithim;

import com.utils.ArraysPrint;
/**
 * 冒泡排序
 */
public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = { 29, 2, 45, 3, 5, 88, 66, 59, 34 };
		ArraysPrint.printf(arr);//数组顺序遍历打印
		BubbleSort.bubbleSort(arr);
		ArraysPrint.printf(arr);
	}

	public static void bubbleSort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				if (arr[j] < arr[i]) {
					swap(arr, j, i);
				}
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
