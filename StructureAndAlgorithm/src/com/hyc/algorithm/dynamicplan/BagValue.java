package com.hyc.algorithm.dynamicplan;

public class BagValue {

	public static void main(String[] args) {
		int[] weights = new int[] { 2, 2, 4, 6, 3 };
		int[] values = new int[] { 3, 4, 8, 9, 6 };
		System.out.println(putBagWithMatrix(weights, values, 5, 9));
		System.out.println(putBagWithArray(weights, values, 5, 9));

	}

	/**
	 * 0-1�������⣬�����ڲ����������ܳ��ص�����£���������Ʒ������ֵ��ʹ�ö�ά������⣩
	 * 
	 * @param weight ��Ʒ����
	 * @param value  ��Ʒ��ֵ
	 * @param n      ��Ʒ����
	 * @param w      ����������
	 * @return
	 */
	public static int putBagWithMatrix(int[] weight, int[] value, int n, int w) {
		int[][] states = new int[n][w + 1];
		states[0][0] = 0;
		if (weight[0] <= w) {
			states[0][weight[0]] = value[0];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= w; ++j) {// ���ѵ� i ����Ʒ���뱳��
				if (states[i - 1][j] >= 0) {
					states[i][j] = states[i - 1][j];
				}
			}
			for (int j = 0; j <= w - weight[i]; ++j) {// �ѵ� i ����Ʒ���뱳��
				if (states[i - 1][j] >= 0) {
					states[i][j + weight[i]] = Math.max(states[i - 1][j] + value[i], states[i][j + weight[i]]);
				}
			}
		}

		int maxValue = 0;
		for (int i = 0; i <= w; i++) {
			if (states[n - 1][i] > maxValue) {
				maxValue = states[n - 1][i];
			}
		}
		return maxValue;
	}

	/**
	 * 0-1�������⣬�����ڲ����������ܳ��ص�����£���������Ʒ������ֵ��ʹ��һά������⣩
	 * 
	 * @param weight ��Ʒ����
	 * @param value  ��Ʒ��ֵ
	 * @param n      ��Ʒ����
	 * @param w      ����������
	 * @return
	 */
	public static int putBagWithArray(int[] weight, int[] value, int n, int w) {
		int[] states = new int[w + 1];
		states[0] = 0;
		if (weight[0] <= w) {
			states[weight[0]] = value[0];
		}

		for (int i = 1; i < n; i++) {
			for (int j = w - weight[i]; j >= 0; j--) {
				if (states[j] >= 0) {
					states[j + weight[i]] = Math.max(states[j + weight[i]], states[j] + value[i]);
				}
			}
		}

		int maxValue = 0;
		for (int i = w; i >= 0; i--) {
			if (states[i] > maxValue) {
				maxValue = states[i];
			}
		}
		return maxValue;
	}

}
