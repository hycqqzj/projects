package com.hyc.algorithm.search;

public class BinSearch {

	/**
	 * 二分法查询 循环写法
	 * 
	 * @param arr
	 * @param value
	 * @return
	 */
	public static int binSearch1(int[] arr, int value) {
		int lo = 0;
		int hi = arr.length - 1;

		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (arr[mid] == value) {
				return mid;
			}

			if (arr[mid] < value) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * 二分法查找 递归写法
	 * 
	 * @param arr
	 * @param lo
	 * @param hi
	 * @param value
	 * @return
	 */
	public static int binSearch2(int[] arr, int lo, int hi, int value) {
		if (lo > hi) {
			return -1;
		}

		int mid = (lo + hi) / 2;

		if (arr[mid] == value) {
			return mid;
		}

		if (arr[mid] < value) {
			lo = mid + 1;
		} else {
			hi = mid - 1;
		}
		return binSearch2(arr, lo, hi, value);
	}

	public static void main(String[] args) {
		int[] a = {1,5,9,11,12,15};
		System.out.println(binSearch1(a, 11));
		
		System.out.println(binSearch2(a, 0, a.length -1, 12));
	}
}
