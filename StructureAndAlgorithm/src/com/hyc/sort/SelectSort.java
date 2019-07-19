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
	 * ѡ������
	 * �ڳ���ΪN�����������У���һ�α���n-1�������ҵ���С����ֵ���һ��Ԫ�ؽ���
	 * �ڶ��α���n-2�������ҵ���С����ֵ��ڶ���Ԫ�ؽ���
	 * ...
	 * ��n-1�α������ҵ���С����ֵ���n-1��Ԫ�ؽ������������
	 * 
	 * @param arr
	 */
	private static void sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = arr[i];
			// �ҳ������е���СԪ�أ����뵱ǰλ�õ����ݽ���
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