package com.alogrithim;

/** 翻转句子中单词的顺序 */
public class ReverseString {

    public static void main(String[] args) {
	//String src = "I am a student.";
	// String []arr=src.split(" ");
	// int size = arr.length;
	// for(int i = size-1;i>=0;i--){
	// System.out.print(arr[i]+" ");
	// }
	// StringBuilder nsrc = new StringBuilder();
	// int len = src.length();
	// for(int i=len -1;i>=0;i--){
	// nsrc.append(src.charAt(i));
	// }
	// System.out.println(nsrc);
	//
	// char [] t = new char[]{'1','2','3','4','5'};
	// reverseChar(t,0,t.length-1);
	// System.out.println(t);

	char[] centense = { 'i', ' ', 'l', 'o', 'v', 'e', ' ', 'y', 'o', 'u' };

	reverseChar(centense, 0, centense.length - 1);

	reverseWord(centense);

	System.out.print(centense);

    }

    public static void reverseChar(char[] src, int start, int end) {
	while (start < end) {
	    // char temp = src[end];
	    // src[end]=src[start];
	    // src[start]=temp;
	    // end --;
	    // start++;

	    char temp = src[start];

	    src[start] = src[end];

	    src[end] = temp;

	    start++;

	    end--;

	}
    }

    public static void reverseWord(char[] src) {
	int count = 0;
	int start = 0;
	for (int i = 0, size = src.length; i < size; i++) {
	    if (src[i] != ' ') {
		count++;
	    } else {
		reverseChar(src, start, count - 1);
		start = count + 1;
		count++;
	    }
	}
    }
}
