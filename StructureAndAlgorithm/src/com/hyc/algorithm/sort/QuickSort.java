package com.hyc.algorithm.sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 15, 17, 8, 10, 9, 25, 21, 24, 6 };
		sort(arr, 0, arr.length - 1);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * ��������
	 * ƽ��ʱ�临�Ӷȣ�O(N*logN)
	 * @param arr
	 * @param low
	 * @param high
	 */
	private static void sort(int[] arr, int low, int high) {
		if (low >= high) {
            return;
        }
		
		int i = low;
		int j = high;
		int index = arr[i];
		while (i < j) {
			while (i < j && arr[j] >= index) {
				j--;
			}
			if(i < j) {
				arr[i++] = arr[j];
			}
			
			while(i < j && arr[i] <= index) {
				i++;
			}
			if(i < j) {
				arr[j--] = arr[i];
			}
		}
		arr[i] = index; // ����׼���������Ŀ�
		sort(arr, low, i - 1); // �ݹ���ã�����
		sort(arr, i + 1, high); // �ݹ���ã�����
	}

}
