package com.hyc.algorithm.dynamicplan;

public class BagWeight {

	public static void main(String[] args) {
		int[] weight = new int[] { 2, 2, 4, 6, 3 };
		System.out.println(putBagWithMatrix(weight, 5, 9));
		System.out.println(putBagWithArray(weight, 5, 9));
	}

	/**
	 * 0-1背包问题，只考察在不超过背包总承重的情况下，背包内物品的最大重量（使用二维数组求解）
	 * 
	 * @param weight 物品重量
	 * @param n      物品个数
	 * @param w      背包最大承重
	 */
	public static int putBagWithMatrix(int[] weight, int n, int w) {
		boolean[][] states = new boolean[n][w + 1];
		states[0][0] = true;
		if (weight[0] <= w) {
			states[0][weight[0]] = true;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= w; ++j) {// 不把第 i 个物品放入背包
				if (states[i - 1][j] == true)
					states[i][j] = states[i - 1][j];
			}
			for (int j = 0; j <= w - weight[i]; ++j) {// 把第 i 个物品放入背包
				if (states[i - 1][j] == true)
					states[i][j + weight[i]] = true;
			}
//			for (int j = 0; j <= w; j++) {
//				if (states[i - 1][j]) {
//					states[i][j] = true;// 不放入背包
//					if (j + weight[i] <= w) {
//						states[i][j + weight[i]] = true;// 放入背包
//					}
//				}
//			}
		}

		for (int i = w; i >= 0; i--) {
			if (states[n - 1][i]) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 0-1背包问题，只考察在不超过背包总承重的情况下，背包内物品的最大重量（使用一维数组求解）
	 * 
	 * @param weight 物品重量
	 * @param n      物品个数
	 * @param w      背包最大承重
	 * @return
	 */
	public static int putBagWithArray(int[] weight, int n, int w) {
		boolean[] states = new boolean[w + 1];
		states[0] = true;
		if (weight[0] <= w) {
			states[weight[0]] = true;
		}
		for (int i = 1; i < n; i++) {
//			for (int j = 0; j <= w; j++) {
//				if (states[j] && j + weight[i] <= w) {
//					states[i + weight[i]] = true;
//				}
//			}
			for (int j = w - weight[i]; j >= 0; j--) {
				if (states[j]) {
					states[j + weight[i]] = true;
				}
			}
		}

		for (int i = w; i >= 0; i--) {
			if (states[i]) {
				return i;
			}
		}

		return 0;
	}

}
