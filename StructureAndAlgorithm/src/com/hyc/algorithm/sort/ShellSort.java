package com.hyc.algorithm.sort;

import com.hyc.util.SwapUtil;

public class ShellSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 15, 17, 8, 10, 9, 25, 21, 24, 6 };
		sort(arr);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * 希尔排序： 
	 * 由于插入排序在数据基本有序的情况下效率很高
	 * 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序
	 * 然后逐渐将增量减小,并重复上述过程。
	 * 直至增量为1,此时数据序列基本有序,最后进行插入排序 
	 * 平均时间复杂度：O(n1.5)
	 * 
	 * @param arr
	 */
	private static void sort(int[] arr) {
		int totalGroup = arr.length;
		while (totalGroup > 1) {
			totalGroup = calcSeq(totalGroup);
			// 根据增量分为若干子序列，应用插入排序
			for (int g = 0; g < totalGroup; g++) {
				for (int i = g; i < arr.length; i = i + totalGroup) {
					for (int j = i; j > g; j = j - totalGroup) {
						if (arr[j] < arr[j - totalGroup]) {
							SwapUtil.swap(arr, j - totalGroup, j);
						} else {
							// 已经是有序的不需要遍历
							break;
						}
					}
				}
			}
		}
	}

	private static int calcSeq(int preLen) {
		// 步长=组数
		return preLen / 3 + 1;
	}
}
