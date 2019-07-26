package com.hyc.stack;

public class IntArrayStack {
	private static final int MAX_DEPTH = 50;

	private int[] data;
	private int size;

	public IntArrayStack() {
		data = new int[MAX_DEPTH];
	}

	public IntArrayStack(int cap) {
		if (cap <= 0 || cap > MAX_DEPTH) {
			data = new int[MAX_DEPTH];
		} else {
			data = new int[cap];
		}
	}

	public boolean push(int val) {
		if (size >= MAX_DEPTH) {
			throw new IndexOutOfBoundsException("数组越界");
		}
		data[size] = val;
		size++;
		return true;
	}
	
	public int getSize() {
		return size;
	}
	
	public int pop() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException("数组越界");
		}
		size--;
		return data[size];
	}

	public static void main(String[] args) {
		IntArrayStack stack = new IntArrayStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
