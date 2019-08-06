package com.hyc.algorithm.patmatch;

/**
 *  字符串匹配BM算法
 */
public class StringMatchBM {
	private final static int SIZE = 256;

	/**
	 * 生成模式串散列表
	 * 
	 * @param b
	 * @param m
	 * @param bc
	 */
	public static void generateBC(char[] b, int m, int[] bc) {
		for (int i = 0; i < SIZE; i++) {
			// 初始化 bc
			bc[i] = -1;
		}
		for (int i = 0; i < m; i++) {
			// 计算 b[i] 的 ASCII 值
			int ascii = (int) b[i];
			bc[ascii] = i;
		}
	}

	/**
	 * 在模式串散列表中查询字符位置
	 * 
	 * @param bc
	 * @param c
	 * @return
	 */
	public static int searchCharInBC(int[] bc, char c) {
		int ascii = (int) c;
		return bc[ascii];
	}

	/**
	 * 生成后缀子串
	 * 
	 * @param b模式串
	 * @param m长度
	 * @param suffix
	 * @param prefix
	 */
	public static void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
		// 初始化
		for (int i = 0; i < m; ++i) {
			suffix[i] = -1;
			prefix[i] = false;
		}

		// b[0, i]
		for (int i = 0; i < m - 1; i++) {
			int j = i;
			// 公共后缀子串长度
			int k = 0;
			// 与 b[0, m-1] 求公共后缀子串
			while (j >= 0 && b[j] == b[m - 1 - k]) {
				k++;
				// j+1 表示公共后缀子串在 b[0, i] 中的起始下标
				suffix[k] = j--;
			}

			// 如果公共后缀子串也是模式串的前缀子串
			if (j == -1)
				prefix[k] = true;
		}
	}

	/**
	 * 根据好后缀计算位移
	 * 
	 * @param j坏字符对应的模式串中的字符下标
	 * @param m表示模式串长度
	 * @param suffix
	 * @param prefix
	 * @return
	 */
	private static int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
		// 好后缀长度
		int k = m - 1 - j;
		// 好后缀在模式串中有全部匹配子串
		if (suffix[k] != -1)
			return j - suffix[k] + 1;
		// 查找好后缀最大匹配子串
		for (int r = j + 2; r < m - 1; r++) {
			if (prefix[m - r]) return r;
		}
		// 以上两种情况都不满足，移动模式串长度位
		return m;
	}

	public static int bm(char[] a, int n, char[] b, int m) {
		// 记录模式串中每个字符最后出现的位置
		int[] bc = new int[SIZE];
		// 构建坏字符散列表
		generateBC(b, m, bc);

		// 构建好后缀匹配数组
		int[] suffix = new int[m];
		boolean[] prefix = new boolean[m];
		generateGS(b, m, suffix, prefix);

		// i 表示主串与模式串对齐的第一个字符
		int i = 0;
		while (i <= n - m) {
			// j表示坏字符的位置
			int j = 0;
			// 模式串从后往前匹配
			for (j = m - 1; j >= 0; j--) {
				// 坏字符对应模式串中的下标是 j
				if (a[i + j] != b[j])
					break;
			}

			// 匹配成功，返回主串与模式串第一个匹配的字符的位置
			if (j < 0) {
				return i;
			}

			// 坏字符计算出的位移数量
			int x = j - searchCharInBC(bc, a[i + j]);
			// 好后缀计算出的位移数量
			int y = 0;
			if (j < m - 1) { // 如果有好后缀的话
				y = moveByGS(j, m, suffix, prefix);
			}

			// 这里等同于将模式串往后滑动 j-bc[(int)a[i+j]] 位
			i = i + Math.max(x, y);
		}

		return -1;
	}

	public static void main(String[] args) {
		String master = "abcdefgh";
		String pat = "cde";

		char[] masterCharArr = master.toCharArray();
		char[] patCharArr = pat.toCharArray();
		System.out.println(bm(masterCharArr, masterCharArr.length, patCharArr, patCharArr.length));
	}

}
