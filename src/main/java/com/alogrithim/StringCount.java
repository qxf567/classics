package com.alogrithim;

public class StringCount {

    public int indexOf(String sub,String main){
	int subL = sub.length();
	int mainL = main.length();
	for(int i=0;i<mainL-subL;i++){
	    int j = 0;
	    while(j<subL){
		if(main.charAt(j+i) == sub.charAt(j)){
		    j++;
		}else{
		    break;
		}
	    }
	    if(j == subL){
		return i;
	    }
	}
	return -1;
    }
    public static void main(String[] args) {
	StringCount sc = new StringCount();
	String src = "this is a test example ";
	String partern = "te";
	System.out.println(sc.indexOf(partern, src));
    }
    
}
