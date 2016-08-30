package com.alogrithim;
/**
 * 堆排序(jdk1.7之前使用,之后 改进成DimSort)
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。
 * 效率O（nlogn），归并的最佳、平均和最糟用例效率之间没有差异。 适用于排序大列表，基于分治法。 */
public class MergeSort {

    public void merge(int []arr,int low,int high){
	if(low<high){
	    int m = (low+high)/2;
	    merge(arr,0,m);
	    merge(arr,m+1,high);
	    sort(arr,low,m,high);
	}
    }

    public void sort(int[] arr, int i, int m, int j) {
	int []tempArr = new int[arr.length];
	int k = i;
	int left = i,right = m+1;
	while(left<=m && right <=j){
	    if(arr[left]<arr[right]){
		tempArr[k++]=arr[left++];
	    }else{
		tempArr[k++]=arr[right++];
	    }
	}
	while(left<=m){
	    tempArr[k++]=arr[left++];
	}
	while(right<=j){
	    tempArr[k++]=arr[right++];
	}
	
	//将临时数组放还原数组
	while(i<=j){
	    arr[i]=tempArr[i++];
	}
    }
}
