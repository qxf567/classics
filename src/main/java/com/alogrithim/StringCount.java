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
    
    
    
    public int lastIndexOf(String src, String tar) {
	for (int i = (src.length() - tar.length()); i > 0; i--) {

	    int j = 0;
	    while (j < tar.length()) {
		if (src.charAt(i + j) != tar.charAt(j))
		    break;
		j++;
	    }

	    if (j == tar.length()) {
		return i;
	    }

	}
	return -1;
    }

    public String maxSub(String lstr, String sstr) {
	int maxLen = 0;
	int pos = 0;
	for (int i = 0, len = sstr.length(); i < len; i++) {

	    for (int j = len; j > 0; j--) {
		
		if (i < j) {
		    System.out.println("i:" + i + " j:" + j);
		    int tpos = indexOf(lstr, sstr.substring(i, j));

		    if (tpos > 0 && maxLen < (j-i) ) {
			System.out.println("tpos:"+tpos);
			maxLen = j-i;
			pos = tpos;
		    }
		}
	    }
	}
	if (maxLen > 0) {
	    System.out.println("post:"+pos+" maxLen:"+maxLen);
	    return lstr.substring(pos, pos+maxLen);
	}
	return "";
    }

    
    
    public static void main(String[] args) {
	StringCount sc = new StringCount();
	String src = "this is a test example ";
	String partern = "te";
	System.out.println(sc.indexOf(partern, src));
    }
    
}
