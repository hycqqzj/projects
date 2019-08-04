package com.hyc.structure.queue;

public class IntArrayQueue {
	private static final int DEFAULT_CAPACITY = 20;
	
	// 数组存储元素
	private int[] data;
	// 大小
	private int size;
	// 队首
	private int head;
	// 队尾
	private int tail;
	
	public IntArrayQueue() {
		data = new int[DEFAULT_CAPACITY];
		head = tail = size = 0;
	}
	
	public IntArrayQueue(int capacity) {
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
		// 队列已满
		if(size == data.length) {
			throw new IndexOutOfBoundsException("数组越界");
		}
	    // 队列未满，然而尾指针移到末尾，此时需要进行数组紧凑
		if(tail == data.length) {
			System.arraycopy(data, head, data, 0, size);
			head = 0;
			tail = size;
		}
		
		data[tail++] = item;
		size++;
		
		return true;
	}
	
	/**
	 * 出队
	 * 
	 * @return
	 */
	public int dequeue() {
		if(head == tail) {
			throw new IndexOutOfBoundsException("数组越界");
		}
		size--;
		return data[head++];
	}
	
	public int getSize() {
		return size;
	}
	

	public static void main(String[] args) {
		IntArrayQueue que = new IntArrayQueue(4);
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
