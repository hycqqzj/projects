package com.hyc.algorithm.recursive;

/**
 * 台阶的走法
 * 假如这里有n个台阶，每次你可以跨1个台阶或者2个，请问走这n个台阶有多少种走法？
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
