package com.hyc.algorithm.backtrack;

public class BagValue {
	private static int maxV = Integer.MIN_VALUE;

	public static void main(String[] args) {
		int[] items = new int[] { 2, 2, 4, 6, 3 };
		int[] values = new int[] { 3, 4, 8, 9, 6 };
		putBag(0, 0, 0, items, items.length, values, 9);
		System.out.println(maxV);

	}

	/**
	 * 0-1�������⣬ֻ�����ڲ����������ܳ��ص�����£���������Ʒ������ֵ
	 * 
	 * @param i      ��i����Ʒ
	 * @param cw     ��ǰ�Ѿ�װ��ȥ����Ʒ��������
	 * @param cv     ��ǰ�Ѿ�װ��ȥ����Ʒ�ļ�ֵ��
	 * @param items  ��Ʒ��������
	 * @param n      ��Ʒ����
	 * @param values ��Ʒ��ֵ����
	 * @param w      ����������
	 */
	public static void putBag(int i, int cw, int cv, int[] items, int n, int[] values, int w) {
		if (w == cw || i == n) {
			maxV = Math.max(maxV, cv);
			return;
		}

		putBag(i + 1, cw, cv, items, n, values, w);
		if (items[i] + cw <= w) {
			putBag(i + 1, cw + items[i], cv + values[i], items, n, values, w);
		}
	}

}
