package com.hyc.structure.tree;

import java.util.Arrays;

import com.hyc.util.SwapUtil;

/**
 * �±��0��ʼ�Ĵ󶥶� ���ӣ�2*i+1�� �Һ��ӣ�2*i+2�� ���ڵ㣺(i-1)/2
 * 
 * @author Administrator
 *
 */
public class BigHeapZero {
	private int[] a;
	// �����Ѵ�������
	private int size;
	// ���������
	private int capacity;

	public BigHeapZero(int capacity) {
		size = 0;
		this.capacity = capacity;
		a = new int[capacity];
	}

	public void insert(int data) {
		if (size >= capacity) {
			throw new IndexOutOfBoundsException("����Խ��");
		}
		a[size] = data;
		heapifyBottomToTop(size, data);
		size++;
	}

	public void deleteMax() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException("����Խ��");
		}

		size--;
		a[0] = a[size];
		heapifyTopToBottom(0);
	}

	/**
	 * ɾ��ʱ�������¶ѻ�
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
	 * ����ʱ�������϶ѻ�
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

		System.out.println("\r\n==========����==========");
		heap.insert(22);
		Arrays.stream(heap.getData()).forEach(x -> System.out.print(x + ","));
		
		System.out.println("\r\n==========ɾ��==========");
		heap.deleteMax();
		Arrays.stream(heap.getData()).forEach(x -> System.out.print(x + ","));

	}

}
