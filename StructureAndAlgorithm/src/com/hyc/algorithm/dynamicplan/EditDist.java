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

	/**
	 * ������Ӵ�����
	 * 
	 * @param a ��һ���ַ�����
	 * @param n ��һ���ַ����鳤��
	 * @param b �ڶ����ַ�����
	 * @param m �ڶ����ַ����鳤��
	 * @return
	 */
	public int lcs(char[] a, int n, char[] b, int m) {
		int[][] maxlcs = new int[n][m];
		// ��ʼ���� 0 �У�a[0..0] �� b[0..j] �� maxlcs
		for (int j = 0; j < m; ++j) {
			if (a[0] == b[j])
				maxlcs[0][j] = 1;
			else if (j != 0)
				maxlcs[0][j] = maxlcs[0][j - 1];
			else
				maxlcs[0][j] = 0;
		}
		// ��ʼ���� 0 �У�a[0..i] �� b[0..0] �� maxlcs
		for (int i = 0; i < n; ++i) {
			if (a[i] == b[0])
				maxlcs[i][0] = 1;
			else if (i != 0)
				maxlcs[i][0] = maxlcs[i - 1][0];
			else
				maxlcs[i][0] = 0;
		}
		// ���
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < m; ++j) {
				if (a[i] == b[j])
					maxlcs[i][j] = max(maxlcs[i - 1][j], maxlcs[i][j - 1], maxlcs[i - 1][j - 1] + 1);
				else
					maxlcs[i][j] = max(maxlcs[i - 1][j], maxlcs[i][j - 1], maxlcs[i - 1][j - 1]);
			}
		}
		return maxlcs[n - 1][m - 1];
	}

	private int max(int x, int y, int z) {
		int maxv = Integer.MIN_VALUE;
		if (x > maxv)
			maxv = x;
		if (y > maxv)
			maxv = y;
		if (z > maxv)
			maxv = z;
		return maxv;
	}

}
