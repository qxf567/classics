package com.alogrithim;

import java.util.Random;

import com.utils.ArraysPrint;

/**
 * 随机从一个数组中取数，不重复
 * 
 * http://shamrock.blog.51cto.com/2079212/1337647
 * 
 */
public class RandomArray {

    public static void main(String[] args) {
	int[] arr = { 29, 2, 45, 3, 5, 88, 66, 59, 34 };
	ArraysPrint.printf(getRandomFromArray(arr,3));
	
    }

    public static int[] getRandomFromArray(int[] array, int count) {

	int[] a = array;
	int[] result = new int[count];
	boolean r[] = new boolean[array.length];
	Random random = new Random();
	int m = count; // 要随机取的元素个数
	if (m > a.length || m < 0)
	    return a;
	int n = 0;
	while (true) {
	    int temp = random.nextInt(a.length);
	    if (!r[temp]) {
		if (n == m) // 取到足量随机数后退出循环
		    break;
		n++;
		System.out.println("得到的第" + n + "个随机数为：" + a[temp]);
		result[n - 1] = a[temp];
		r[temp] = true;
	    }
	}
	return result;

    }

}
