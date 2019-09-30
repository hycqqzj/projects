package com.hyc.algorithm.dynamicplan;

import lombok.Data;

/**
 * 0-1背包问题（动态规划算法实现）
 */
@Data
public class BagWeightValueIndex {
	// 背包总承重量
	private int bagTotal;
	// 所有物品重量数组
	private int[] productWeightArr;
	// 所有物品价值数组
	private int[] producValueArr;

	public BagWeightValueIndex(int bagTotal, int[] productWeightArr, int[] producValueArr) {
		this.bagTotal = bagTotal;
		this.productWeightArr = productWeightArr;
		this.producValueArr = producValueArr;
	}

	public void showTwoDimensionalArr(int[][] states) {
		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < states[i].length; j++) {
				System.out.print(states[i][j] + "|");
			}
			System.out.println("\r\n===============================");
		}
	}

	/**
	 * 利用二维数组
	 */
	public void putBag() {
		// 动态规划数组
		int[][] states = new int[productWeightArr.length][bagTotal + 1];
		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < states[i].length; j++) {
				states[i][j] = -1;
			}
		}

		states[0][0] = 0;
		if (productWeightArr[0] <= bagTotal) {
			states[0][productWeightArr[0]] = producValueArr[0];
		}

		// 动态规划状态转移
		for (int i = 1; i < productWeightArr.length; i++) {
			// 不放入
			for (int j = 0; j < bagTotal; j++) {
				if (states[i - 1][j] >= 0) {
					states[i][j] = states[i - 1][j];
				}
			}
			// 放入，由于j + productArr[i] <= bagTotal推导出j <= bagTotal - productArr[i]
			for (int j = 0; j <= bagTotal - productWeightArr[i]; j++) {
				if (states[i - 1][j] >= 0) {
					int pre = states[i][j + productWeightArr[i]];
					states[i][j + productWeightArr[i]] = Math.max(pre, states[i - 1][j] + producValueArr[i]);
				}
			}
		}
		
		// 找出最大值和最大值对应的位置
		int max = 0;
		int loc = 0;
		for (int i = 0; i <= bagTotal; i++) {
			if (states[productWeightArr.length - 1][i] > max) {
				max = states[productWeightArr.length - 1][i];
				loc = i;
			}
		}
		System.out.println("价值最大值为：" + max);
		
		for (int i = productWeightArr.length - 1; i >= 1; i--) {
			// 选择了第i个物品
			if(states[i - 1][loc - productWeightArr[i]] >= 0 && states[i - 1][loc] != states[i][loc] && loc - productWeightArr[i] >= 0) {
				loc = loc - productWeightArr[i];
				System.out.println(i);
			} //else 没有选择这个物品loc不变
		}
		if(loc != 0) {
			System.out.println(0);
		}
	}

	/**
	 * 使用一维数组
	 * 
	 * @return
	 */
	public int putBag2() {
		// 动态规划数组
		int[] states = new int[bagTotal + 1];
		for (int i = 0; i < states.length; i++) {
			states[i] = -1;
		}

		states[0] = 0;
		if (productWeightArr[0] <= bagTotal) {
			states[productWeightArr[0]] = producValueArr[0];
		}

		for (int i = 1; i < productWeightArr.length; i++) {
			// 把第 i个物品放入背包
			for (int j = bagTotal - productWeightArr[i]; j >= 0; --j) {
				if (states[j] >= 0) {
					states[j + productWeightArr[i]] = Math.max(states[j] + producValueArr[i], states[j + productWeightArr[i]]);
				}
			}
		}

		int max = 0;
		for (int i = bagTotal; i >= 0; i--) {
			if (states[i] > max) {
				max = states[i];
			}
		}

		return max;
	}

	public static void main(String[] args) {
		int bagTotal = 9;
		int[] productWeightArr = new int[] { 2, 2, 4, 6, 3 };
		int[] producValueArr = new int[] { 3, 4, 8, 9, 6 };
		BagWeightValueIndex bag = new BagWeightValueIndex(bagTotal, productWeightArr, producValueArr);
		//bag.putBag();
		System.out.println(bag.putBag2());
	}

}
