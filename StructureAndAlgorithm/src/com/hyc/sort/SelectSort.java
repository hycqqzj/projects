package com.hyc.sort;

public class SelectSort {

	public static void main(String[] args) {
		int[] arr = new int[] {15,17,8,10,9,25,21,24};
		sort(arr);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * 选择排序：
	 * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换
	 * 第二次遍历n-2个数，找到最小的数值与第二个元素交换
	 * ...
	 * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成
	 * 
	 * @param arr
	 */
	private static void sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = arr[i];
			// 找出数组中的最小元素，并与当前位置的数据交换
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < min) {
					min = arr[j];
					arr[j] = arr[i];
					arr[i] = min;
				}
			}
		}
	}

}