package com.hyc.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 0-1背包问题（回溯法实现）
 */
@Data
public class BagWeightValueIndex {
	// 背包总承重量
	private int bagTotal;
	// 所有物品重量数组
	private int[] productWeightArr;
	// 所有物品价值数组
	private int[] producValueArr;
	// 背包中物品总重量
	private int productBagWeight;
	// 背包内物品总价值
	private int productBagValue;
	// 背包内所装物品序号
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
	 * 放入物品
	 * 
	 * @param curWeight当前背包中物品重量
	 * @param curItem当前放入的物品编号
	 */
	public void putBag(int curWeight, int curItem, int curVal) {
		// 最后一个物品或者超出背包总承重
		if (curItem > productWeightArr.length - 1 || curWeight >= bagTotal) {
			if (curVal > productBagValue) {
				productBagValue = curVal;
				printProducts();
				System.out.println("价值：" + curVal);
			}
			removeFromProductIndex(curItem);
			return;
		}

		// 不放入当前物品
		putBag(curWeight, curItem + 1, curVal);
		// 若放入后总重量不超过最大承重量则放入当前物品
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
