package com.hyc.sort;

import com.hyc.util.SwapUtil;

public class InsertSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 16, 15, 17, 8, 10, 9, 25, 21, 24, 22, 28 };
		sort(arr);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * �������� ��Ҫ�����һ�����У��ٶ�ǰn-1�����Ѿ��ź������ڽ���n�����嵽ǰ������������У�ʹ����n����Ҳ���ź�˳��� 
	 * ��˷���ѭ����ֱ��ȫ���ź�˳��
	 * ƽ��ʱ�临�Ӷȣ�O(n2)
	 * 
	 * @param arr
	 */
	private static void sort(int[] arr) {
		for (int i = 1; i <= arr.length - 1; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					SwapUtil.swap(arr, j - 1, j);
				} else {
					// �Ѿ�������Ĳ���Ҫ����
					break;
				}
			}
		}

	}
}
