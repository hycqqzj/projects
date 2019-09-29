package com.hyc.algorithm.backtrack;

public class BagValue {
	private static int maxV = Integer.MIN_VALUE;

	public static void main(String[] args) {
		int[] items = new int[] { 2, 2, 4, 6, 3 };
		int[] values = new int[] { 3, 4, 8, 9, 6 };
		putBag(0, 0, 0, items, items.length, values, 9);
		System.out.println(maxV);

	}

	/**
	 * 0-1背包问题，只考察在不超过背包总承重的情况下，背包内物品的最大价值
	 * 
	 * @param i      第i个物品
	 * @param cw     当前已经装进去的物品的重量和
	 * @param cv     当前已经装进去的物品的价值和
	 * @param items  物品重量数组
	 * @param n      物品个数
	 * @param values 物品价值数组
	 * @param w      背包最大承重
	 */
	public static void putBag(int i, int cw, int cv, int[] items, int n, int[] values, int w) {
		if (w == cw || i == n) {
			maxV = Math.max(maxV, cv);
			return;
		}

		putBag(i + 1, cw, cv, items, n, values, w);
		if (items[i] + cw <= w) {
			putBag(i + 1, cw + items[i], cv + values[i], items, n, values, w);
		}
	}

}
