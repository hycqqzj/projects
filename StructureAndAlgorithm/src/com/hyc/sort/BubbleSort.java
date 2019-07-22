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
	 * ð������ 
	 * �Ƚ����ڵ��������ݣ�����ڶ�����С���ͽ���λ��
	 * �Ӻ���ǰ�����Ƚϣ�һֱ���Ƚ���ǰ�������ݡ�������С������������ʼ��λ�ã�������һ����С����λ�þ��ź���
	 * �����ظ��������̣����ν���2.3...n-1����С���ź�λ��
	 * ƽ��ʱ�临�Ӷȣ�O(n2)
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
			
			// ���뱾��ѭ��δ�������ݽ�����˵�������Ѿ��ź����ˣ���ʱӦ���������ѭ��
			if (!flag) {
				System.out.println("����ѭ����i=" + (i + 1));
				break;
			}
		}
	}
}
