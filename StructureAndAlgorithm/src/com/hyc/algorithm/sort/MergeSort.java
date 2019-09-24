package com.hyc.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] arr = { 5, 9, 3, 1, 6, 2, 4, 8 };

		sort(arr, 0, arr.length - 1);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * �鲢����
	 * ������ֳ�2��A��B�������2�����ڵ����ݶ�������ģ���ô�Ϳ��Ժܷ���Ľ���2�����ݽ�������
	 * ���Խ�A��B������ٷֳ�2�顣�������ƣ����ֳ�����С��ֻ��1������ʱ��������Ϊ���С�������Ѿ��ﵽ������Ȼ���ٺϲ����ڵ�2��С��Ϳ�����
	 * ƽ��ʱ�临�Ӷȣ�O(N*logN)
	 * 
	 * @param arr
	 * @param begin
	 * @param end
	 */
	private static void sort(int[] arr, int begin, int end) {
		if(begin >= end) {
			return;
		}
		
		int mid = (begin + end) / 2;

		sort(arr, begin, mid);
		sort(arr, mid + 1, end);
		mergeArr(arr, begin, end);
	}

	private static void mergeArr(int[] arr, int begin, int end) {
		int middle = (begin + end) / 2;

		int i = 0;
		int j = 0;
		int k = begin;

		int[] left = Arrays.copyOfRange(arr, begin, middle + 1);
		int[] right = Arrays.copyOfRange(arr, middle + 1, end + 1);
		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				// �˴�ʹ��<=�����������㷨���ȶ��������㷨
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}

		while (i < left.length) {
			arr[k++] = left[i++];
		}
		while (j < right.length) {
			arr[k++] = right[j++];
		}
	}

}
