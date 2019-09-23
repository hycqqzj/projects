package com.hyc.algorithm.dynamicplan;

public class MinDist {
	private int minDist = Integer.MAX_VALUE;

	/**
	 * 回溯法
	 * 
	 * @param i
	 * @param j
	 * @param dist i，j时路径长度
	 * @param w    数组
	 * @param n    数组最大下标
	 */
	private void minDistBT(int i, int j, int dist, int[][] w, int n) {
		if (i == n && j == n) {
			if (dist < minDist) {
				minDist = dist;
			}
			return;
		}
		if (i < n) {
			minDistBT(i + 1, j, dist + w[i + 1][j], w, n);
		}
		if (j < n) {
			minDistBT(i, j + 1, dist + w[i][j + 1], w, n);
		}
	}

	/**
	 * 动态规划（转移表）
	 * 
	 * @param matrix 数组
	 * @param n      数组行列大小,n*n
	 */
	private int minDistDpTable(int[][] matrix, int n) {
		int[][] state = new int[n][n];

		int sum = 0;
		for (int j = 0; j < n; j++) {
			sum = sum + matrix[0][j];
			state[0][j] = sum;
		}

		sum = 0;
		for (int i = 0; i < n; i++) {
			sum = sum + matrix[i][0];
			state[i][0] = sum;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				state[i][j] = Math.min(state[i][j - 1], state[i - 1][j]) + matrix[i][j];
			}
		}
		return state[n - 1][n - 1];
	}

	/**
	 * 动态规划（方程）
	 * 
	 * @param matrix
	 * @param n
	 * @return
	 */
	private int minDistDpFun(int[][] matrix, int n) {
		return doMinDistDpFun(matrix, new int[n][n], n - 1, n - 1);
	}

	private int doMinDistDpFun(int[][] matrix, int[][] mem, int i, int j) {
		if (i == 0 && j == 0) {
			return matrix[0][0];
		}

		if (mem[i][j] > 0) {
			return mem[i][j];
		}

		int minLeft = Integer.MAX_VALUE;
		if (j - 1 >= 0) {
			minLeft = doMinDistDpFun(matrix, mem, i, j - 1);
		}
		int minUp = Integer.MAX_VALUE;
		if (i - 1 >= 0) {
			minUp = doMinDistDpFun(matrix, mem, i - 1, j);
		}

		int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
		mem[i][j] = currMinDist;
		return currMinDist;
	}

	public static void main(String[] args) {
		int[][] w = { { 1, 3, 5, 9 }, { 2, 1, 3, 4, }, { 5, 2, 6, 7 }, { 6, 8, 4, 3 } };
		MinDist ins = new MinDist();
		ins.minDistBT(0, 0, 1, w, 3);
		System.out.println(ins.minDist);

		System.out.println(ins.minDistDpTable(w, 4));

		System.out.println(ins.minDistDpFun(w, 4));
	}

}
