package com.hyc.algorithm.dynamicplan;

import lombok.Data;

/**
 * 整型数组的最大增长子序列
 */
@Data
public class MaxSeq {
	private int[] data;
	private int[] seq;

	public MaxSeq(int[] data) {
		this.data = data;
		this.seq = new int[data.length];
		this.seq[0] = 1;
	}

	public int getMaxSeq() {
		for (int i = 1; i < data.length; i++) {
			int preMax = maxSeqFirstLessOrEq(data[i], i);
			seq[i] = preMax + 1;
		}

		int max = 0;
		for (int j = 0; j < seq.length; j++) {
			if (seq[j] > max)
				max = seq[j];
		}

		return max;
	}

	/**
	 * 获取小于等于指定值的最大子序列
	 * 
	 * @param a指定值
	 * @param aLoc指定值在数组中的位置
	 * @return
	 */
	private int maxSeqFirstLessOrEq(int a, int aLoc) {
		int loc = -1;
		int[] temp = new int[aLoc];
		for (int i = aLoc - 1; i >= 0; i--) {
			if (data[i] <= a) {
				temp[++loc] = seq[i];
			}
		}

		if (loc < 0) {
			return 0;
		}

		int max = temp[0];
		for (int i = 1; i < temp.length; i++) {
			if (temp[i] > max)
				max = temp[i];
		}
		return max;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 9, 3, 6, 5, 1, 7 };
		MaxSeq maxSeq = new MaxSeq(arr);
		System.out.println(maxSeq.getMaxSeq());
	}

}
