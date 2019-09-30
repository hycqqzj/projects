package com.hyc.algorithm.dynamicplan;

import java.util.HashMap;

public class CoinAmount {

	public static void main(String[] args) {
		int[] coins = { 1, 2, 5 };
		int amount = 11;
		CoinAmount qu = new CoinAmount();
		System.out.println(qu.coinChange(coins, amount));
	}

	public int coinChange(int[] coins, int amount) {
		return doCoinChange(coins, amount, new HashMap<Integer, Integer>());
	}

	private int doCoinChange(int[] coins, int amount, HashMap<Integer, Integer> mem) {
		if (coins == null || coins.length == 0) {
			return -1;
		}
		if (amount <= 0) {
			return 0;
		}
		for (int c : coins) {
			if (c == amount) {
				return 1;
			}
		}

		if (mem.containsKey(amount)) {
			return mem.get(amount);
		}

		int[] minArr = new int[coins.length];
		for (int i = 0; i < coins.length; i++) {
			if (amount - coins[i] >= 0) {
				minArr[i] = doCoinChange(coins, amount - coins[i], mem);
			} else {
				minArr[i] = -1;
			}
		}

		int minCoins = min(minArr);
		minCoins = minCoins < 0 ? -1 : minCoins + 1;
		mem.put(amount, minCoins);

		return minCoins;
	}

	public int min(int... arr) {
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= 0 && arr[i] < minValue) {
				minValue = arr[i];
			}
		}
		return minValue >= 0 && minValue != Integer.MAX_VALUE ? minValue : -1;
	}
}