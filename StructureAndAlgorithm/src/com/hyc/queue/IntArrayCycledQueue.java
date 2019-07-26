package com.hyc.queue;

public class IntArrayCycledQueue {
	private static final int DEFAULT_CAPACITY = 20;

	// ����洢Ԫ��
	private int[] data;
	// ��С
	private int size;
	// ����
	private int head;
	// ��β
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
	 * ���
	 * 
	 * @param item
	 * @return
	 */
	public Boolean enqueue(int item) {
		if (isFull()) {
			throw new IndexOutOfBoundsException("����Խ��");
		}

		data[tail] = item;
		tail = (tail + 1) % data.length;
		size++;

		return true;
	}
	
	/**
	 * ����
	 * 
	 * @return
	 */
	public int dequeue() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("����Խ��");
		}
		
		size--;
		int ret = data[head];
		head = (head + 1) % data.length;
		return ret;
	}

	/**
	 * �����Ƿ�����
	 * 
	 * @return
	 */
	public Boolean isFull() {
		return (tail + 1) % data.length == head;
	}

	/**
	 * �����Ƿ�Ϊ��
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
