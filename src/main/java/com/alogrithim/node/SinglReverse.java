package com.alogrithim.node;



public class SinglReverse {

    /**
     * 使用递归方式逆序输出单链表
     */
    public SinglNode recursion(SinglNode singleNode){
	if(singleNode == null || singleNode.getNextNode() == null){
	    return singleNode;
	}
	SinglNode node = recursion(singleNode.getNextNode());
	singleNode.getNextNode().setNextNode(singleNode);
	singleNode.setNextNode(null);
	return node;
    }
    
    
    /**
     * 遍历，将当前节点的下一个节点缓存后更改当前节点指针
     */
    public static SinglNode reverse(SinglNode head) {
	SinglNode pre = head;
	SinglNode cur = head.getNextNode();
	SinglNode next;
	while (cur != null) {
	    next = cur.getNextNode();
	    cur.setNextNode(pre);
	    pre = cur;
	    cur = next;

	}
	head.setNextNode(null);
	head = pre;
	return head;
    }
    
    
    public static void main(String[] args) {
	SinglNode head = new SinglNode(0);
	SinglNode tmp = null;
	SinglNode cur = null;
	// 构造一个长度为10的链表，保存头节点对象head
	for (int i = 1; i < 10; i++) {
	    tmp = new SinglNode(i);
	    if (1 == i) {
		head.setNextNode(tmp);
	    } else {
		cur.setNextNode(tmp);
	    }
	    cur = tmp;
	}
	// 打印反转前的链表
	SinglNode h = head;
	while (null != h) {
	    System.out.print(h.getValue() + " ");
	    h = h.getNextNode();
	}
	SinglReverse r =new SinglReverse();
	head = r.recursion(head);
	// 调用反转方法
	System.out.println("\n**************************");
	// 打印反转后的结果
	while (null != head) {
	    System.out.print(head.getValue() + " ");
	    head = head.getNextNode();
	}
	
    }
}
