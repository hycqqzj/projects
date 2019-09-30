package com.hyc.algorithm.dynamicplan;

public class EditDist {

	public static void main(String[] args) {
		char[] a = "mitcmu".toCharArray();
		char[] b = "mtacnu".toCharArray();
		EditDist editDist = new EditDist();
		System.out.println(editDist.lwstDP(a, a.length, b, b.length));
	}

	/**
	 * ����˹̹����-��̬�滮��״̬ת�Ʊ�
	 * 
	 * @param a ��һ���ַ�����
	 * @param n ��һ���ַ����鳤��
	 * @param b �ڶ����ַ�����
	 * @param m ��һ���ַ����鳤��
	 * 
	 * @return
	 */
	public int lwstDP(char[] a, int n, char[] b, int m) {
		int[][] minDist = new int[n][m];

		// ��ʼ����0��
		for (int i = 0; i < m; i++) {
			if (b[i] == a[0]) {
				minDist[0][i] = i;
			} else if (i != 0) {
				minDist[0][i] = minDist[0][i - 1] + 1;
			} else {
				minDist[0][i] = 1;
			}
		}

		// ��ʼ����0��
		for (int i = 0; i < n; i++) {
			if (a[i] == b[0]) {
				minDist[i][0] = i;
			} else if (i != 0) {
				minDist[i][0] = minDist[i - 1][0] + 1;
			} else {
				minDist[i][0] = 1;
			}
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (a[i] == b[j]) {
					minDist[i][j] = min(minDist[i - 1][j - 1], minDist[i][j - 1] + 1, minDist[i - 1][j] + 1);
				} else {
					minDist[i][j] = min(minDist[i - 1][j - 1] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j] + 1);
				}
			}
		}
		return minDist[n - 1][m - 1];
	}

	private int min(int x, int y, int z) {
		int minv = Integer.MAX_VALUE;
		if (x < minv) {
			minv = x;
		}

		if (y < minv) {
			minv = y;
		}
		if (z < minv) {
			minv = z;
		}
		return minv;
	}

}
