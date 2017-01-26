package com.alogrithim;

/**
 * 快排序 总结："挖坑填数"
 * 选定一个枢纽元素，对待排序序列进行分割，分割之后的序列一个部分小于枢纽元素，一个部分大于枢纽元素，再对这两个分割好的子序列进行上述的过程
 * <p>
 * 平均效率O（nlogn），适用于排序大列表。
 * 此算法的总时间取决于枢纽值的位置；选择第一个元素作为枢纽，可能导致O（n²）的最糟用例效率。若数基本有序，效率反而最差
 * 。选项中间值作为枢纽，效率是O（nlogn）。 基于分治法。
 */
public class QuickSort {

    public int partition(int[] arr, int low, int high) {
	int pivot = arr[low];// 采用子序列的第一个元素作为枢纽元素
	while (low < high) {
	    // 从后往前栽后半部分中寻找第一个小于枢纽元素的元素
	    while (low < high && arr[high] >= pivot) {
		high--;
	    }
	    // 将这个比枢纽元素小的元素交换到前半部分
	    arr[low] = arr[high];
	    // 从前往后在前半部分中寻找第一个大于枢纽元素的元素
	    while (low < high && arr[low] < pivot) {
		low++;
	    }
	    // 将这个枢纽元素大的元素交换到后半部分
	    arr[high] = arr[low];
	}
	arr[low] = pivot;
	// 返回枢纽元素所在的位置
	return low;
    }

    public void quickSort(int[] arr, int low, int high) {
	// 退出条件，递归一定要有退出条件
	if (low < high) {
	    int m = partition(arr, low, high);
	    quickSort(arr, 0, m);
	    quickSort(arr, m + 1, high);
	}
    }

}
