package com.hyc.structure.tree;

import java.util.Arrays;

import com.hyc.util.SwapUtil;

/**
 * 下标从0开始的大顶堆 左孩子：2*i+1， 右孩子：2*i+2， 父节点：(i-1)/2
 * 
 * @author Administrator
 *
 */
public class BigHeapZero {
	private int[] a;
	// 堆中已存数据量
	private int size;
	// 堆最大容量
	private int capacity;

	public BigHeapZero(int capacity) {
		size = 0;
		this.capacity = capacity;
		a = new int[capacity];
	}

	public void insert(int data) {
		if (size >= capacity) {
			throw new IndexOutOfBoundsException("数组越界");
		}
		a[size] = data;
		heapifyBottomToTop(size, data);
		size++;
	}

	public void deleteMax() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException("数组越界");
		}

		size--;
		a[0] = a[size];
		heapifyTopToBottom(0);
	}

	/**
	 * 删除时从上往下堆化
	 * 
	 * @param loc
	 */
	private void heapifyTopToBottom(int loc) {
		while (true) {
			int maxPos = loc;
			if (2 * loc + 1 < size && a[2 * loc + 1] > a[loc]) {
				maxPos = 2 * loc + 1;
			}
			if (2 * loc + 2 < size && a[2 * loc + 2] > a[loc]) {
				maxPos = 2 * loc + 2;
			}
			if(maxPos == loc) {
				break;
			}
			SwapUtil.swap(a, maxPos, loc);
			loc = maxPos;
		}
	}

	/**
	 * 插入时从下往上堆化
	 * 
	 * @param loc
	 * @param data
	 */
	private void heapifyBottomToTop(int loc, int data) {
		while (loc > 0 && data > a[(loc - 1) / 2]) {
			SwapUtil.swap(a, loc, (loc - 1) / 2);
			loc = (loc - 1) / 2;
		}
	}

	public static void main(String[] args) {
		int[] temp = new int[] { 0, 33, 27, 21, 16, 13, 15, 9, 5, 6, 7, 8, 1, 2 };
		BigHeap heap = new BigHeap(20);
		heap.setSize(temp.length);
		System.arraycopy(temp, 0, heap.getData(), 0, temp.length);
		Arrays.stream(heap.getData()).forEach(x -> System.out.print(x + ","));

		System.out.println("\r\n==========插入==========");
		heap.insert(22);
		Arrays.stream(heap.getData()).forEach(x -> System.out.print(x + ","));
		
		System.out.println("\r\n==========删除==========");
		heap.deleteMax();
		Arrays.stream(heap.getData()).forEach(x -> System.out.print(x + ","));

	}

}
