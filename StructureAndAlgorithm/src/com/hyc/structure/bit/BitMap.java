package com.hyc.structure.bit;

/**
 * λͼ
 */
public class BitMap {
	private char[] bytes;
	private int nbits;

	public BitMap(int nbits) {
		this.nbits = nbits;
		// charռ�������ֽڣ���16��������λ
		this.bytes = new char[nbits / 16 + 1];
	}

	/**
	 * ����k����λͼ
	 * 
	 * @param k
	 */
	public void set(int k) {
		if (k > nbits) {
			return;
		}
		// �����������char[]�����еĵڼ���Ԫ����
		int byteIndex = k / 16;
		// ���������λ��ƫ����
		int bitIndex = k % 16;
		// |=����λ���ƣ�����ߺ��ұ߽��а�λ�����㲢�������ֵ����߱���
		bytes[byteIndex] |= (1 << bitIndex);
	}

	/**
	 * �ж�����k�Ƿ���λͼ��
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
