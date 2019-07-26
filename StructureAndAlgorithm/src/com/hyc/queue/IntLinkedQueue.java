package com.hyc.queue;

import lombok.Data;
import lombok.NoArgsConstructor;

public class IntLinkedQueue {
	@Data
	@NoArgsConstructor
	class Node {
		// 数据
		private int data;
		// 后继节点
		private Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	private int size = 0;
	private Node head;
	private Node tail;

	public IntLinkedQueue() {
		head = new Node();
		tail = head;
	}

	/**
	 * 入队
	 * 
	 * @param item
	 * @return
	 */
	public Boolean enqueue(int item) {
		Node node = new Node(item);
		tail.next = node;
		tail = node;
		size++;
		return true;
	}

	/**
	 * 出队
	 * 
	 * @return
	 */
	public int dequeue() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException("链表越界");
		}
		Node node = head.next;
		head.next = node.next;
		size--;
		return node.data;
	}

	public int getSize() {
		return size;
	}

	public static void main(String[] args) {
		IntLinkedQueue que = new IntLinkedQueue();
		que.enqueue(1);
		que.enqueue(2);
		que.enqueue(3);
		que.enqueue(4);

		System.out.println(que.dequeue());
		System.out.println(que.dequeue());

		que.enqueue(5);
		que.enqueue(6);

		System.out.println(que.dequeue());
		System.out.println(que.dequeue());
		System.out.println(que.dequeue());
		System.out.println(que.dequeue());
	}

}
