package com.utils;

import java.util.Arrays;

/** 数组输出 */
public class ArraysPrint {
    public static void printf(int[] arr) {
	for (int a : arr) {
	    System.out.print(a + " ");
	}
	System.out.println();
    }
    
    public static void printf(String[] arr) {
	for (String a : arr) {
	    System.out.print(a + " ");
	}
	System.out.println();
    }
    public static void print(String [] arr){
        System.out.println(Arrays.toString(arr));
    }
}
