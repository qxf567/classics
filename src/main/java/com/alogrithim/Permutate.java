package com.alogrithim;

/** 全排列 */
public class Permutate {

    public static int total = 0;

    public static void swap(String[] str, int i, int j) {
	String temp = new String();
	temp = str[i];
	str[i] = str[j];
	str[j] = temp;
    }

    public static void arrange(String[] str, int begin, int len) {
	if (begin == len - 1) {
	    for (int i = 0; i < len; i++) {
		System.out.print(str[i] + "  ");
	    }
	    System.out.println();
	    total++;
	} else {
	    for (int i = begin; i < len; i++) {
		swap(str, begin, i);
		arrange(str, begin + 1, len);
		swap(str, begin, i);
	    }
	}

    }

    public static void main(String[] args) {
	String str[] = { "1", "2", "3", "4" };
	arrange(str, 0, str.length);
	System.out.println(total);

    }

}
