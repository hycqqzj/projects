package com.hyc.queue;

public class IntArrayCycledQueue {
	private static final int DEFAULT_CAPACITY = 20;

	// 数组存储元素
	private int[] data;
	// 大小
	private int size;
	// 队首
	private int head;
	// 队尾
	private int tail;

	public IntArrayCycledQueue() {
		data = new int[DEFAULT_CAPACITY];
		head = tail = size = 0;
	}

	public IntArrayCycledQueue(int capacity) {
		if (capacity > 0 && capacity < Integer.MAX_VALUE) {
			data = new int[capacity];
		} else {
			data = new int[DEFAULT_CAPACITY];
		}
		head = tail = size = 0;
	}

	/**
	 * 入队
	 * 
	 * @param item
	 * @return
	 */
	public Boolean enqueue(int item) {
		if (isFull()) {
			throw new IndexOutOfBoundsException("数组越界");
		}

		data[tail] = item;
		tail = (tail + 1) % data.length;
		size++;

		return true;
	}
	
	/**
	 * 出队
	 * 
	 * @return
	 */
	public int dequeue() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("数组越界");
		}
		
		size--;
		int ret = data[head];
		head = (head + 1) % data.length;
		return ret;
	}

	/**
	 * 队列是否已满
	 * 
	 * @return
	 */
	public Boolean isFull() {
		return (tail + 1) % data.length == head;
	}

	/**
	 * 队列是否为空
	 * 
	 * @return
	 */
	public Boolean isEmpty() {
		return head == tail;
	}

	public int getSize() {
		return size;
	}

	public static void main(String[] args) {
		IntArrayCycledQueue que = new IntArrayCycledQueue(5);
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
