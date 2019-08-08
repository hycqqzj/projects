package com.hyc.algorithm.backtrack;

/**
 * 八皇后问题，回溯算法实现
 *
 */
public class EightQueen {
	private int[][] data = new int[8][8];

	public void putQueen(int row) {
		if (row >= 8) {
			showData();
			return;
		}

		for (int column = 0; column < 8; column++) {
			resetRow(row);
			if (isOK(row, column)) {
				data[row][column] = 1;
				putQueen(row + 1);
			}
		}
	}

	/**
	 * 行重置
	 * 
	 * @param row
	 */
	private void resetRow(int row) {
		for (int column = 0; column < 8; column++) {
			data[row][column] = 0;
		}
	}

	/**
	 * 检查是否可以
	 * 
	 * @param x行
	 * @param y列
	 * @return
	 */
	public boolean isOK(int x, int y) {
		// 列
		for (int i = x - 1; i >= 0; i--) {
			if (data[i][y] == 1) {
				return false;
			}
		}

		// 行
		for (int j = y - 1; j >= 0; j--) {
			if (data[x][j] == 1) {
				return false;
			}
		}

		// 对角线
		for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
			if (data[i][j] == 1) {
				return false;
			}
		}
		for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
			if (data[i][j] == 1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 打印数组
	 */
	public void showData() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (j != data.length - 1) {
					System.out.print(data[i][j] + "-");
				} else {
					System.out.print(data[i][j]);
				}

			}
			System.out.println();
		}

		System.out.println("================");
	}

	public static void main(String[] args) {
		EightQueen qu = new EightQueen();
		qu.putQueen(0);
	}

}
