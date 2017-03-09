package com.alogrithim.collection;

import java.util.ArrayList;
import java.util.List;

/**
 *  两个有序的List合并 并保持有序 
 * 
 */
public class SortList {

    public static List<Integer> sort(List<Integer> aList,List<Integer> bList){
	int aSize = aList.size();
	int bSize = bList.size();
	List<Integer> cList = new ArrayList<Integer>(aSize+bSize);
	int i = 0,j=0;
	while(i<aSize && j<bSize){
	    if(aList.get(i)<bList.get(j)){
		cList.add(aList.get(i++));
	    }else{
		cList.add(bList.get(j++));
	    }
	}
	
	while(i<aSize){
	    cList.add(aList.get(i++));
	}
	
	while(j<bSize){
	    cList.add(bList.get(j++));
	}
	return cList;
    }
    
    public static void main(String[] args) {  
        List<Integer> list1 = new ArrayList<Integer>();  
        list1.add(1);  
        list1.add(2);  
        list1.add(4);  
        list1.add(7);  
        list1.add(9);  
        list1.add(11);  
        list1.add(13);  
        list1.add(16);  
        list1.add(19);  
          
        List<Integer> list2 = new ArrayList<Integer>();  
        list2.add(0);  
        list2.add(3);  
        list2.add(5);  
        list2.add(8);  
        list2.add(11);  
        list2.add(17);  
        list2.add(25);  
        list2.add(30);  
          
        System.out.println(SortList.sort(list1, list2));  
    }  
}
