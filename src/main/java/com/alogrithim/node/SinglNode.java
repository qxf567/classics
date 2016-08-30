package com.alogrithim.node;

/**
 * 单链表
 * 
 */
public class SinglNode {

    public SinglNode(int val) {
	this.value = val;
    }

    private int value;

    private SinglNode nextNode;

    public int getValue() {
	return value;
    }

    public void setValue(int value) {
	this.value = value;
    }

    public SinglNode getNextNode() {
	return nextNode;
    }

    public void setNextNode(SinglNode nextNode) {
	this.nextNode = nextNode;
    }
}
