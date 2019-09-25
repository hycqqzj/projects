package com.hyc.algorithm.sort;

/**
 * ��������
 */
public class CountSort {

	public static void main(String[] args) {
		int[] arr = { 2, 5, 3, 0, 2, 3, 0, 3 };
		countingSort(arr, 8);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * ��������
	 * 
	 * @param a ����
	 * @param n �����С
	 */
	public static void countingSort(int[] a, int n) {
		if (n <= 1) {
			return;
		}

		// �������������ݵķ�Χ
		int max = a[0];
		for (int i = 1; i < n; i++) {
			if (max < a[i]) {
				max = a[i];
			}
		}

		// ����һ���������� c���±��С [0,max]
		int[] c = new int[max + 1];
		for (int i = 0; i <= max; i++) {
			c[i] = 0;
		}

		// ����ÿ��Ԫ�صĸ��������� c ��
		for (int i = 0; i < n; i++) {
			c[a[i]]++;
		}

		// �����ۼ�
		for (int i = 1; i <= max; i++) {
			c[i] = c[i - 1] + c[i];
		}

		// ��ʱ���� r���洢����֮��Ľ��
		int[] r = new int[n];
		
		// ��������Ĺؼ����裬�е������
		for (int i = n - 1; i >= 0; i--) {
			int index = c[a[i]] - 1;
			r[index] = a[i];
			c[a[i]]--;
		}

		// ����������� a ����
		for (int i = 0; i < n; i++) {
			a[i] = r[i];
		}
	}

}
