package com.hyc.algorithm.dichotomy;

/**
 * 二分算法及其变形问题
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
	 * 查找值等于给定值的位置
	 * 
	 * @param a     数组
	 * @param n     数组长度
	 * @param value 值
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
	 * 查找第一个值等于给定值的位置
	 * 
	 * @param a     数组
	 * @param n     数组长度
	 * @param value 值
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
	 * 查找最后一个值等于给定值的位置
	 * 
	 * @param a     数组
	 * @param n     数组长度
	 * @param value 值
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
	 * 查找第一个值大于等于给定值的位置
	 * 
	 * @param a     数组
	 * @param n     数组长度
	 * @param value 值
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
	 * 查找最后一个值小于等于给定值的位置
	 * 
	 * @param a     数组
	 * @param n     数组长度
	 * @param value 值
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
