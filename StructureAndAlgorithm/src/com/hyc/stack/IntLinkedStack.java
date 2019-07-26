package com.hyc.stack;

import lombok.Data;
import lombok.NoArgsConstructor;

public class IntLinkedStack {
	@Data
	@NoArgsConstructor
	class Node {
		// 数据
		private int data;
		// 前驱节点
		private Node pre;

		public Node(int data) {
			this.data = data;
		}
	}
	
	private int size = 0;
	private Node tail;
	
	public IntLinkedStack () {
		tail = new Node();
	}
	
	public Boolean push(int val) {
		Node node = new Node(val);
		Node last = tail.pre;
		node.pre = last;
		tail.pre = node;
		size++;
		
		return true;
	}
	
	public int pop() {
		if(tail.pre == null) {
			throw new IndexOutOfBoundsException("链表越界");
		}
		Node last = tail.pre;
		Node lastNew = last.pre;
		tail.pre = lastNew;
		size--;
		return last.data;
	}
	
	public int getSize() {
		return size;
	}

	public static void main(String[] args) {
		IntLinkedStack stack = new IntLinkedStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
