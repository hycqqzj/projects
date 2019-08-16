package com.hyc.algorithm.dynamicplan;

import lombok.Data;

/**
 * 杨辉三角
 */
@Data
public class YHTriangle {
	private int[][] data;

	public YHTriangle(int[][] data) {
		this.data = data;
	}

	/**
	 * 计算最短路径（贪心方法：第i,j个位置最小路径值为其左边最小路径和右边最小路径中的最小值+当前节点的值）
	 * 非动态规划方法，结果不一定是最优解
	 * 
	 * @param line行
	 * @param column列
	 * @return
	 */
	public int minPathGreedy(int line, int column) {
		if (line == data.length - 1) {
			return data[line][column];
		}

		return data[line][column] + Math.min(minPathGreedy(line + 1, column), minPathGreedy(line + 1, column + 1));
	}

	/**
	 * 计算最短路径（动态规划方法-状态转移表法：第i,j个位置最小路径值为data[i - 1][j]和data[i - 1][j - 1]中的最小值+当前节点的值）
	 * 
	 * @return
	 */
	public int minPathPlan() {
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (j == 0) {
					data[i][0] = data[i][0] + data[i - 1][0];
				} else {
					if (data[i - 1][j] > 0 && data[i - 1][j - 1] > 0 && data[i][j] > 0) {
						data[i][j] = Math.min(data[i - 1][j], data[i - 1][j - 1]) + data[i][j];
					} else if (data[i][j] > 0) {
						data[i][j] = data[i - 1][j - 1] + data[i][j];
					}
				}
			}
		}

		int min = data[data.length - 1][0];
		for (int m = 0; m < data[data.length - 1].length; m++) {
			if (data[data.length - 1][m] > 0 && data[data.length - 1][m] < min) {
				min = data[data.length - 1][m];
			}
		}
		return min;
	}

	public static void main(String[] args) {
		int[][] data1 = new int[][] { { 5, -1, -1, -1, -1 }, { 7, 8, -1, -1, -1 }, { 2, 3, 4, -1, -1 },
				{ 4, 9, 6, 1, -1 }, { 2, 7, 9, 4, 5 } };
		YHTriangle yh1 = new YHTriangle(data1);
		System.out.println(yh1.minPathGreedy(0, 0));

		int[][] data2 = new int[][] { { 5, -1, -1, -1, -1 }, { 7, 8, -1, -1, -1 }, { 2, 3, 4, -1, -1 },
				{ 4, 9, 6, 1, -1 }, { 2, 7, 9, 4, 5 } };
		YHTriangle yh2 = new YHTriangle(data2);
		System.out.println(yh2.minPathPlan());
	}

}
