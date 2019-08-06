package com.hyc.algorithm.patmatch;

/**
 *  �ַ���ƥ��BM�㷨
 */
public class StringMatchBM {
	private final static int SIZE = 256;

	/**
	 * ����ģʽ��ɢ�б�
	 * 
	 * @param b
	 * @param m
	 * @param bc
	 */
	public static void generateBC(char[] b, int m, int[] bc) {
		for (int i = 0; i < SIZE; i++) {
			// ��ʼ�� bc
			bc[i] = -1;
		}
		for (int i = 0; i < m; i++) {
			// ���� b[i] �� ASCII ֵ
			int ascii = (int) b[i];
			bc[ascii] = i;
		}
	}

	/**
	 * ��ģʽ��ɢ�б��в�ѯ�ַ�λ��
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
	 * ���ɺ�׺�Ӵ�
	 * 
	 * @param bģʽ��
	 * @param m����
	 * @param suffix
	 * @param prefix
	 */
	public static void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
		// ��ʼ��
		for (int i = 0; i < m; ++i) {
			suffix[i] = -1;
			prefix[i] = false;
		}

		// b[0, i]
		for (int i = 0; i < m - 1; i++) {
			int j = i;
			// ������׺�Ӵ�����
			int k = 0;
			// �� b[0, m-1] �󹫹���׺�Ӵ�
			while (j >= 0 && b[j] == b[m - 1 - k]) {
				k++;
				// j+1 ��ʾ������׺�Ӵ��� b[0, i] �е���ʼ�±�
				suffix[k] = j--;
			}

			// ���������׺�Ӵ�Ҳ��ģʽ����ǰ׺�Ӵ�
			if (j == -1)
				prefix[k] = true;
		}
	}

	/**
	 * ���ݺú�׺����λ��
	 * 
	 * @param j���ַ���Ӧ��ģʽ���е��ַ��±�
	 * @param m��ʾģʽ������
	 * @param suffix
	 * @param prefix
	 * @return
	 */
	private static int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
		// �ú�׺����
		int k = m - 1 - j;
		// �ú�׺��ģʽ������ȫ��ƥ���Ӵ�
		if (suffix[k] != -1)
			return j - suffix[k] + 1;
		// ���Һú�׺���ƥ���Ӵ�
		for (int r = j + 2; r < m - 1; r++) {
			if (prefix[m - r]) return r;
		}
		// ������������������㣬�ƶ�ģʽ������λ
		return m;
	}

	public static int bm(char[] a, int n, char[] b, int m) {
		// ��¼ģʽ����ÿ���ַ������ֵ�λ��
		int[] bc = new int[SIZE];
		// �������ַ�ɢ�б�
		generateBC(b, m, bc);

		// �����ú�׺ƥ������
		int[] suffix = new int[m];
		boolean[] prefix = new boolean[m];
		generateGS(b, m, suffix, prefix);

		// i ��ʾ������ģʽ������ĵ�һ���ַ�
		int i = 0;
		while (i <= n - m) {
			// j��ʾ���ַ���λ��
			int j = 0;
			// ģʽ���Ӻ���ǰƥ��
			for (j = m - 1; j >= 0; j--) {
				// ���ַ���Ӧģʽ���е��±��� j
				if (a[i + j] != b[j])
					break;
			}

			// ƥ��ɹ�������������ģʽ����һ��ƥ����ַ���λ��
			if (j < 0) {
				return i;
			}

			// ���ַ��������λ������
			int x = j - searchCharInBC(bc, a[i + j]);
			// �ú�׺�������λ������
			int y = 0;
			if (j < m - 1) { // ����кú�׺�Ļ�
				y = moveByGS(j, m, suffix, prefix);
			}

			// �����ͬ�ڽ�ģʽ�����󻬶� j-bc[(int)a[i+j]] λ
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
