package com.hyc.algorithm.backtrack;

/**
 * 字符串编辑距离
 */
public class EditDist {
	private int minLwstDist = Integer.MAX_VALUE;

	public static void main(String[] args) {
		char[] a = "mitcmu".toCharArray();
		char[] b = "mtacnu".toCharArray();
		EditDist editDist = new EditDist();
		editDist.lwstBT(a, 0, 6, b, 0, 6, 0);
		System.out.println(editDist.minLwstDist);
	}

	/**
	 * 莱文斯坦距离-回溯法实现
	 * 
	 * @param a     第一个字符数组
	 * @param i     位置i
	 * @param n     第一个字符数组长度
	 * @param b     第二个字符数组
	 * @param j     位置j
	 * @param m     第一个字符数组长度
	 * @param edist 当前编辑距离
	 */
	private void lwstBT(char[] a, int i, int n, char[] b, int j, int m, int edist) {
		if (i == n - 1 || j == m - 1) {
			if (i < n - 1) {
				edist += n - i;
			}
			if (j < m - 1) {
				edist += m - j;
			}
			if (minLwstDist > edist) {
				minLwstDist = edist;
			}
			return;
		}
		if (a[i] == b[j]) {
			// 两个字符匹配则往后考察
			lwstBT(a, i + 1, n, b, j + 1, m, edist);
		} else {
			// 删除 a[i]或者 b[j]前添加一个字符
			lwstBT(a, i + 1, n, b, j + 1, m, edist + 1);
			// 删除 b[j]或者 a[i]前添加一个字符
			lwstBT(a, i, n, b, j + 1, m, edist + 1);
			// 将 a[i]和 b[j]替换为相同字符
			lwstBT(a, i + 1, n, b, j + 1, m, edist + 1);
		}
	}

}
