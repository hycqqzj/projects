package com.hyc.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 0-1�������⣨���ݷ�ʵ�֣�
 */
@Data
public class BagWeightValueIndex {
	// �����ܳ�����
	private int bagTotal;
	// ������Ʒ��������
	private int[] productWeightArr;
	// ������Ʒ��ֵ����
	private int[] producValueArr;
	// ��������Ʒ������
	private int productBagWeight;
	// ��������Ʒ�ܼ�ֵ
	private int productBagValue;
	// ��������װ��Ʒ���
	private List<Integer> producIndexArr;

	public BagWeightValueIndex(int bagTotal, int[] productWeightArr, int[] producValueArr) {
		this.bagTotal = bagTotal;
		this.productWeightArr = productWeightArr;
		this.producValueArr = producValueArr;
		this.productBagWeight = 0;
		this.productBagValue = 0;
		this.producIndexArr = new ArrayList<Integer>();
	}

	private void printProducts() {
		for (Integer e : producIndexArr) {
			System.out.print(e + "-");
		}
		System.out.println();
	}
	
	private void removeFromProductIndex(int curItem) {
		if (producIndexArr.size() > 0 && producIndexArr.get(producIndexArr.size() - 1) == curItem) {
			producIndexArr.remove(producIndexArr.size() - 1);
		}
	}

	/**
	 * ������Ʒ
	 * 
	 * @param curWeight��ǰ��������Ʒ����
	 * @param curItem��ǰ�������Ʒ���
	 */
	public void putBag(int curWeight, int curItem, int curVal) {
		// ���һ����Ʒ���߳��������ܳ���
		if (curItem > productWeightArr.length - 1 || curWeight >= bagTotal) {
			if (curVal > productBagValue) {
				productBagValue = curVal;
				printProducts();
				System.out.println("��ֵ��" + curVal);
			}
			removeFromProductIndex(curItem);
			return;
		}

		// �����뵱ǰ��Ʒ
		putBag(curWeight, curItem + 1, curVal);
		// �������������������������������뵱ǰ��Ʒ
		if (productWeightArr[curItem] + curWeight <= bagTotal) {
			producIndexArr.add(curItem);
			putBag(productWeightArr[curItem] + curWeight, curItem + 1, producValueArr[curItem] + curVal);
		}

		removeFromProductIndex(curItem);
	}

	public static void main(String[] args) {
		int bagTotal = 9;
		int[] productWeightArr = new int[] { 2, 2, 4, 6, 3 };
		int[] producValueArr = new int[] { 3, 4, 8, 9, 6 };
		BagWeightValueIndex bag = new BagWeightValueIndex(bagTotal, productWeightArr, producValueArr);
		bag.putBag(0, 0, 0);
		System.out.println(bag.productBagValue);
	}
}
