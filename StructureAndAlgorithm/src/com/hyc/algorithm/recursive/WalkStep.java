package com.hyc.algorithm.recursive;

/**
 * ̨�׵��߷�
 * ����������n��̨�ף�ÿ������Կ�1��̨�׻���2������������n��̨���ж������߷���
 * @author Administrator
 *
 */
public class WalkStep {

	public int walk(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		return walk(n - 2) + walk(n - 1);
	}

	public static void main(String[] args) {
		WalkStep ins = new WalkStep();
		System.out.println(ins.walk(10));
	}
}
