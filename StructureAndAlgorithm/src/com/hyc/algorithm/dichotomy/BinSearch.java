package com.hyc.algorithm.dichotomy;

/**
 * �����㷨�����������
 */
public class BinSearch {

	public static void main(String[] args) {
		int[] a = { 1, 4, 7, 11, 15 };
		System.out.println(bsearch(a, 5, 8));
		System.out.println(bsearchFist(a, 5, 8));
		System.out.println(bsearchLast(a, 5, 8));
		System.out.println(bsearchFisrtGTE(a, 5, 7));
		System.out.println(bsearchLastLTE(a, 5, 5));
	}

	/**
	 * ����ֵ���ڸ���ֵ��λ��
	 * 
	 * @param a     ����
	 * @param n     ���鳤��
	 * @param value ֵ
	 * @return
	 */
	public static int bsearch(int[] a, int n, int value) {
		int low = 0;
		int high = n - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid] > value) {
				high = mid - 1;
			} else if (a[mid] < value) {
				low = mid + 1;
			} else {
				return mid;
			}
		}

		return -1;
	}

	/**
	 * ���ҵ�һ��ֵ���ڸ���ֵ��λ��
	 * 
	 * @param a     ����
	 * @param n     ���鳤��
	 * @param value ֵ
	 * @return
	 */
	public static int bsearchFist(int[] a, int n, int value) {
		int low = 0;
		int high = n - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid] > value) {
				high = mid - 1;
			} else if (a[mid] < value) {
				low = mid + 1;
			} else {
				if ((mid == 0) || (a[mid - 1] != value))
					return mid;
				else
					high = mid - 1;
			}
		}

		return -1;
	}

	/**
	 * �������һ��ֵ���ڸ���ֵ��λ��
	 * 
	 * @param a     ����
	 * @param n     ���鳤��
	 * @param value ֵ
	 * @return
	 */
	public static int bsearchLast(int[] a, int n, int value) {
		int low = 0;
		int high = n - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid] > value) {
				high = mid - 1;
			} else if (a[mid] < value) {
				low = mid + 1;
			} else {
				if ((mid == n - 1) || (a[mid + 1] != value))
					return mid;
				else
					low = mid + 1;
			}
		}

		return -1;
	}

	/**
	 * ���ҵ�һ��ֵ���ڵ��ڸ���ֵ��λ��
	 * 
	 * @param a     ����
	 * @param n     ���鳤��
	 * @param value ֵ
	 * @return
	 */
	public static int bsearchFisrtGTE(int[] a, int n, int value) {
		int low = 0;
		int high = n - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid] >= value) {
				if (mid == 0 || a[mid - 1] < value) {
					return mid;
				}
				high = mid - 1;
			} else if (a[mid] < value) {
				low = mid + 1;
			}
		}

		return -1;
	}

	/**
	 * �������һ��ֵС�ڵ��ڸ���ֵ��λ��
	 * 
	 * @param a     ����
	 * @param n     ���鳤��
	 * @param value ֵ
	 * @return
	 */
	public static int bsearchLastLTE(int[] a, int n, int value) {
		int low = 0;
		int high = n - 1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if (a[mid] > value) {
				high = mid - 1;
			} else if (a[mid] <= value) {
				if (mid == n - 1 || a[mid + 1] > value) {
					return mid;
				}
				low = mid + 1;
			}
		}

		return -1;
	}

}
