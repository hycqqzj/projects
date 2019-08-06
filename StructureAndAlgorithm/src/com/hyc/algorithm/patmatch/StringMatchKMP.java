package com.hyc.algorithm.patmatch;

public class StringMatchKMP {

	/**
	 * KMP�㷨
	 * 
	 * @param a����
	 * @param n��������
	 * @param bģʽ��
	 * @param mģʽ������
	 * @return
	 */
	public static int kmp(char[] a, int n, char[] b, int m) {
		int[] next = getNexts(b, m);
		int j = 0;
		for (int i = 0; i < n; ++i) {
			// һֱ�ҵ� a[i] �� b[j]
			while (j > 0 && a[i] != b[j]) {
				j = next[j - 1] + 1;
			}

			if (a[i] == b[j])
				++j;

			// �ҵ�ƥ��ģʽ������
			if (j == m)
				return i - m + 1;
		}
		return -1;
	}

	/**
	 * ����next����
	 * 
	 * @param bģʽ��
	 * @param mģʽ���ĳ���
	 * @return
	 */
	private static int[] getNexts(char[] b, int m) {
		int[] next = new int[m];
		next[0] = -1;
		int k = -1;
		for (int i = 1; i < m; i++) {
			while (k != -1 && b[k + 1] != b[i]) {
				// Ѱ�Ҵδ�ƥ��ǰ׺
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
