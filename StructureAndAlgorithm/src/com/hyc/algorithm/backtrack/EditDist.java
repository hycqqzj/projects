package com.hyc.algorithm.backtrack;

/**
 * �ַ����༭����
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
	 * ����˹̹����-���ݷ�ʵ��
	 * 
	 * @param a     ��һ���ַ�����
	 * @param i     λ��i
	 * @param n     ��һ���ַ����鳤��
	 * @param b     �ڶ����ַ�����
	 * @param j     λ��j
	 * @param m     ��һ���ַ����鳤��
	 * @param edist ��ǰ�༭����
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
			// �����ַ�ƥ�������󿼲�
			lwstBT(a, i + 1, n, b, j + 1, m, edist);
		} else {
			// ɾ�� a[i]���� b[j]ǰ���һ���ַ�
			lwstBT(a, i + 1, n, b, j + 1, m, edist + 1);
			// ɾ�� b[j]���� a[i]ǰ���һ���ַ�
			lwstBT(a, i, n, b, j + 1, m, edist + 1);
			// �� a[i]�� b[j]�滻Ϊ��ͬ�ַ�
			lwstBT(a, i + 1, n, b, j + 1, m, edist + 1);
		}
	}

}
