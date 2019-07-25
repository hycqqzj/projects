package com.hyc.list;

import java.util.Arrays;

/**
 *  基于数组实现的list
 */
public class IntArrayList {
	private static final int DEFAULT_CAPACITY = 10;

	// 数组存储元素
	private int[] data;
	// list大小
	private int size;

	public IntArrayList() {
		data = new int[DEFAULT_CAPACITY];
		size = 0;
	}

	public IntArrayList(int capacity) {
		if (capacity > 0 && capacity < Integer.MAX_VALUE) {
			data = new int[capacity];
		} else {
			data = new int[DEFAULT_CAPACITY];
		}
		size = 0;
	}

	public int get(int index) {
		rangeCheck(index);
		return data[index];
	}

	public int indexOf(int val) {
		int index = -1;
		for (int i = 0; i < data.length; i++) {
			if (data[i] == val) {
				index = i;
				break;
			}
		}

		return index;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public boolean add(int e) {
		if (data.length == size) {
			grow();
		}
		data[size++] = e;
		return true;
	}
	
	public boolean add(int index, int e) {
		rangeCheck(index);
		if (data.length == size) {
			grow();
		}
		// 数组元素后移
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = e;
		size++;
		return true;
	}

	public int remove(int index) {
		rangeCheck(index);

		int old = data[index];
		int numMove = size - index - 1;
		if (numMove > 0) {
			// 数组元素前移
			System.arraycopy(data, index + 1, data, index, numMove);
		}
		data[--size] = 0;

		return old;
	}

	private void grow() {
		int oldCapacity = data.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		data = Arrays.copyOf(data, newCapacity);
	}

	private void rangeCheck(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("数组越界");
		}
	}

	public static void main(String[] args) {
		IntArrayList list = new IntArrayList(3);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(8);
		list.add(6, 7);

		System.out.println(list.get(3));
		System.out.println(list.indexOf(5));

		int val = list.remove(4);
		System.out.println(val);
		System.out.println(list.isEmpty());
	}

}
