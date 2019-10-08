package com.hyc.structure.bit;

/**
 * 位图
 */
public class BitMap {
	private char[] bytes;
	private int nbits;

	public BitMap(int nbits) {
		this.nbits = nbits;
		// char占用两个字节，即16个二进制位
		this.bytes = new char[nbits / 16 + 1];
	}

	/**
	 * 整数k放入位图
	 * 
	 * @param k
	 */
	public void set(int k) {
		if (k > nbits) {
			return;
		}
		// 计算该数字在char[]数组中的第几个元素中
		int byteIndex = k / 16;
		// 计算二进制位的偏移量
		int bitIndex = k % 16;
		// |=，按位或复制，将左边和右边进行按位或运算并将结果赋值给左边变量
		bytes[byteIndex] |= (1 << bitIndex);
	}

	/**
	 * 判断整数k是否在位图中
	 * 
	 * @param k
	 * @return
	 */
	public boolean get(int k) {
		if (k > nbits) {
			return false;
		}
		int byteIndex = k / 16;
		int bitIndex = k % 16;
		return (bytes[byteIndex] & (1 << bitIndex)) != 0;
	}
}
