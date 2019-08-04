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
	 * ϣ������ 
	 * ���ڲ������������ݻ�������������Ч�ʺܸ�
	 * ��Ҫ�����һ�����У�����ĳһ������Ϊ���������У����������зֱ���в�������
	 * Ȼ���𽥽�������С,���ظ��������̡�
	 * ֱ������Ϊ1,��ʱ�������л�������,�����в������� 
	 * ƽ��ʱ�临�Ӷȣ�O(n1.5)
	 * 
	 * @param arr
	 */
	private static void sort(int[] arr) {
		int totalGroup = arr.length;
		while (totalGroup > 1) {
			totalGroup = calcSeq(totalGroup);
			// ����������Ϊ���������У�Ӧ�ò�������
			for (int g = 0; g < totalGroup; g++) {
				for (int i = g; i < arr.length; i = i + totalGroup) {
					for (int j = i; j > g; j = j - totalGroup) {
						if (arr[j] < arr[j - totalGroup]) {
							SwapUtil.swap(arr, j - totalGroup, j);
						} else {
							// �Ѿ�������Ĳ���Ҫ����
							break;
						}
					}
				}
			}
		}
	}

	private static int calcSeq(int preLen) {
		// ����=����
		return preLen / 3 + 1;
	}
}
