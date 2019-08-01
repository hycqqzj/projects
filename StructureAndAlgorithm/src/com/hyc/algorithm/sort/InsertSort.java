package com.hyc.algorithm.sort;

public class InsertSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 16, 15, 17, 8, 10, 9, 25, 21, 24, 22, 28 };
		sort(arr);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * �������� ��Ҫ�����һ�����У��ٶ�ǰn-1�����Ѿ��ź������ڽ���n�����嵽ǰ������������У�ʹ����n����Ҳ���ź�˳��� ��˷���ѭ����ֱ��ȫ���ź�˳��
	 * ƽ��ʱ�临�Ӷȣ�O(n2)
	 * 
	 * @param arr
	 */
	private static void sort(int[] arr) {
		for (int i = 1; i <= arr.length - 1; i++) {
			int value = arr[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (arr[j] > value) {
					arr[j + 1] = arr[j]; // �����ƶ�
				} else {
					// �Ѿ�������Ĳ���Ҫ����
					break;
				}
			}
			
			arr[j + 1] = value;
		}

	}
}
