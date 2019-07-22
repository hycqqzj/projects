package com.hyc.sort;

import com.hyc.util.SwapUtil;

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 15, 17, 8, 10, 9, 25, 21, 24, 22, 28 };
		sort(arr);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * 冒泡排序： 
	 * 比较相邻的两个数据，如果第二个数小，就交换位置
	 * 从后向前两两比较，一直到比较最前两个数据。最终最小数被交换到起始的位置，这样第一个最小数的位置就排好了
	 * 继续重复上述过程，依次将第2.3...n-1个最小数排好位置
	 * 平均时间复杂度：O(n2)
	 * 
	 * @param arr
	 */
	private static void sort(int[] arr) {
		for (int i = 0; i <= arr.length - 1; i++) {
			boolean flag = false;
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					SwapUtil.swap(arr, j - 1, j);
					flag = true;
				}
			}
			
			// 加入本次循环未发生数据交换则说明数组已经排好序了，此时应跳出最外层循环
			if (!flag) {
				System.out.println("跳出循环，i=" + (i + 1));
				break;
			}
		}
	}
}
