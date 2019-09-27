package com.hyc.algorithm.dynamicplan;

import lombok.Data;

/**
 * ���������������������� a[0...i] ���������Ϊ: a[i] ֮ǰ���б���С��Ԫ���������г������� + 1
 * 
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
	 * ��ȡС�ڵ���ָ��ֵ�����������
	 * 
	 * @param aָ��ֵ
	 * @param aLocָ��ֵ�������е�λ��
	 * @return
	 */
	private int maxSeqFirstLessOrEq(int a, int aLoc) {
		int loc = -1;
		int[] temp = new int[aLoc];
		for (int i = aLoc - 1; i >= 0; i--) {
			if (data[i] < a) {
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
	
	/**
	 * ����������г��ȼ򵥽ⷨ
	 * 
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length <= 0) {
			return 0;
		}
		if (nums.length == 1) {
			return 1;
		}

		int[] maxSeqArr = new int[nums.length];
		maxSeqArr[0] = 1;

		for (int i = 1; i < nums.length; i++) {
			int maxSeq = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < nums[i]) {
					if (maxSeqArr[j] > maxSeq) {
						maxSeq = maxSeqArr[j];
					}
				}
			}
			maxSeqArr[i] = maxSeq + 1;
		}

		int max = maxSeqArr[0];
		for (int i = 1; i < maxSeqArr.length; i++) {
			if (maxSeqArr[i] > max) {
				max = maxSeqArr[i];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] arr = { 10, 9, 2, 5, 3, 7, 101, 18 };
		MaxSeq maxSeq = new MaxSeq(arr);
		System.out.println(maxSeq.getMaxSeq());
	}

}
