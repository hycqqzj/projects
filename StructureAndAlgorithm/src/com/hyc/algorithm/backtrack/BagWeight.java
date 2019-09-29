package com.hyc.algorithm.backtrack;

public class BagWeight {
	private static int maxW = Integer.MIN_VALUE;

	public static void main(String[] args) {
		int[] items = new int[] { 2, 2, 4, 6, 3 };
		putBag(0, 0, items, items.length, 9);
		System.out.println(maxW);
	}

	/**
	 * 0-1�������⣬ֻ�����ڲ����������ܳ��ص�����£���������Ʒ���������
	 * 
	 * @param i     ��i����Ʒ
	 * @param cw    ��ǰ�Ѿ�װ��ȥ����Ʒ��������
	 * @param items ��Ʒ����
	 * @param n     ��Ʒ����
	 * @param w     ����������
	 */
	public static void putBag(int i, int cw, int[] items, int n, int w) {
		if (w == cw || i == n) {
			maxW = Math.max(maxW, cw);
			return;
		}

		putBag(i + 1, cw, items, n, w);
		if (items[i] + cw <= w) {
			putBag(i + 1, cw + items[i], items, n, w);
		}
	}

}
