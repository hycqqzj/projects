package com.hyc.algorithm.backtrack;

/**
 * �˻ʺ����⣬�����㷨ʵ��
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
	 * ������
	 * 
	 * @param row
	 */
	private void resetRow(int row) {
		for (int column = 0; column < 8; column++) {
			data[row][column] = 0;
		}
	}

	/**
	 * ����Ƿ����
	 * 
	 * @param x��
	 * @param y��
	 * @return
	 */
	public boolean isOK(int x, int y) {
		// ��
		for (int i = x - 1; i >= 0; i--) {
			if (data[i][y] == 1) {
				return false;
			}
		}

		// ��
		for (int j = y - 1; j >= 0; j--) {
			if (data[x][j] == 1) {
				return false;
			}
		}

		// �Խ���
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
	 * ��ӡ����
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
