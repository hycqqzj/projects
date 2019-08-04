package com.hyc.structure.tree;

import java.util.Arrays;

import com.hyc.util.SwapUtil;

import lombok.Data;

@Data
public class BigHeap {
	private int[] data;
	// 数组中数据量(含data[0])
	private int size;
	// 数组最大容量
	private int capacity;
	
	public BigHeap() {
		this.capacity = 0;
		size = 0;
	}
	
	public BigHeap(int capacity) {
		this.capacity = capacity;
		data = new int[this.capacity];
	}

	public void insert(int val) {
		if (size >= capacity) {
			throw new IndexOutOfBoundsException("数组越界");
		}
		// 插入节点
		data[size] = val;
		// 堆化
		int loc = size;
		while (loc > 1 && data[loc / 2] < val) {
			SwapUtil.swap(data, loc / 2, loc);
			loc = loc / 2;
		}
		size++;
	}

	public void deleteMax() {
		if (size <= 1) {
			throw new IndexOutOfBoundsException("数组越界");
		}
		data[1] = data[size - 1];
		size--;
		heapify(1);
	}

	public void heapify(int loc) {
		while (true) {
			int maxpos = loc;
			if (loc * 2 < size && data[loc] < data[loc * 2]) {
				maxpos = loc * 2;
			}
			if (loc * 2 + 1 < size &&  data[maxpos] < data[loc * 2 + 1]) {
				maxpos = loc * 2 + 1;
			}
			if(maxpos == loc) {
				break;
			}
			SwapUtil.swap(data, loc, maxpos);
			loc = maxpos;
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
		
//		System.out.println("\r\n==========删除==========");
//		int[] temp2 = new int[] { 0, 33, 27, 21, 16, 13, 15, 19, 5, 6, 7, 8, 1, 2, 12 };
//		BigHeap heap2 = new BigHeap(20);
//		heap2.setSize(temp2.length);
//		System.arraycopy(temp2, 0, heap2.getData(), 0, temp2.length);
//		heap2.deleteMax();
//		Arrays.stream(heap2.getData()).forEach(x -> System.out.print(x + ","));
//		System.out.println(heap2.size);
	}
}
