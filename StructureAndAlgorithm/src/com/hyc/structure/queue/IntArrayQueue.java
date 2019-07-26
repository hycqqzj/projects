package com.hyc.structure.queue;

public class IntArrayQueue {
	private static final int DEFAULT_CAPACITY = 20;
	
	// ����洢Ԫ��
	private int[] data;
	// ��С
	private int size;
	// ����
	private int head;
	// ��β
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
	 * ���
	 * 
	 * @param item
	 * @return
	 */
	public Boolean enqueue(int item) {
		// ��������
		if(size == data.length) {
			throw new IndexOutOfBoundsException("����Խ��");
		}
	    // ����δ����Ȼ��βָ���Ƶ�ĩβ����ʱ��Ҫ�����������
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
	 * ����
	 * 
	 * @return
	 */
	public int dequeue() {
		if(head == tail) {
			throw new IndexOutOfBoundsException("����Խ��");
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
