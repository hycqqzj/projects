package com.hyc.algorithm.patmatch;

public class StringMatchKMP {

	/**
	 * KMP算法
	 * 
	 * @param a主串
	 * @param n主串长度
	 * @param b模式串
	 * @param m模式串长度
	 * @return
	 */
	public static int kmp(char[] a, int n, char[] b, int m) {
		int[] next = getNexts(b, m);
		int j = 0;
		for (int i = 0; i < n; ++i) {
			// 一直找到 a[i] 和 b[j]
			while (j > 0 && a[i] != b[j]) {
				j = next[j - 1] + 1;
			}

			if (a[i] == b[j])
				++j;

			// 找到匹配模式串的了
			if (j == m)
				return i - m + 1;
		}
		return -1;
	}

	/**
	 * 构建next数组
	 * 
	 * @param b模式串
	 * @param m模式串的长度
	 * @return
	 */
	private static int[] getNexts(char[] b, int m) {
		int[] next = new int[m];
		next[0] = -1;
		int k = -1;
		for (int i = 1; i < m; i++) {
			while (k != -1 && b[k + 1] != b[i]) {
				// 寻找次大匹配前缀
				k = next[k];
			}
			if (b[k + 1] == b[i]) {
				k++;
			}
			next[i] = k;
		}
		
		return next;
	}

	public static void main(String[] args) {
		String master = "abcdefgh";
		String pat = "cde";

		char[] masterCharArr = master.toCharArray();
		char[] patCharArr = pat.toCharArray();
		System.out.println(kmp(masterCharArr, masterCharArr.length, patCharArr, patCharArr.length));
	}


}
