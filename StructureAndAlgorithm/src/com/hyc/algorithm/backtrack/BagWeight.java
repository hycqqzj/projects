package com.hyc.algorithm.backtrack;

public class BagWeight {
	private static int maxW = Integer.MIN_VALUE;

	public static void main(String[] args) {
		int[] items = new int[] { 2, 2, 4, 6, 3 };
		putBag(0, 0, items, items.length, 9);
		System.out.println(maxW);
	}

	/**
	 * 0-1背包问题，只考察在不超过背包总承重的情况下，背包内物品的最大重量
	 * 
	 * @param i     第i个物品
	 * @param cw    当前已经装进去的物品的重量和
	 * @param items 物品数组
	 * @param n     物品个数
	 * @param w     背包最大承重
	 */
	public static void putBag(int i, int cw, int[] items, int n, int w) {
		if (w == cw || i == n) {
			maxW = Math.max(maxW, cw);
			return;
		}

		putBag(i + 1, cw, items, n, w);
		if (items[i] + cw <= w) {
			putBag(i + 1, cw + items[i], items, n, w);
		}
	}

}
