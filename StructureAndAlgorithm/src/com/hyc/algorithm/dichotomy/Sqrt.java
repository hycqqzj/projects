package com.hyc.algorithm.dichotomy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sqrt {

	public static void main(String[] args) {
		System.out.println(getSqrt(26));
		System.out.println(square(new BigDecimal(26)));
	}

	private static BigDecimal square(BigDecimal item) {
		BigDecimal min = BigDecimal.valueOf(0);
		BigDecimal max = item;

		BigDecimal mid = BigDecimal.valueOf(-1);
		while (min.compareTo(max) <= 0) {
			mid = (max.add(min)).divide(BigDecimal.valueOf(2), 6, RoundingMode.HALF_UP);
			if (mid.multiply(mid).compareTo(item) < 0) {
				min = mid.add(BigDecimal.valueOf(0.000001));
			} else {
				max = mid.subtract(BigDecimal.valueOf(0.000001));
			}
		}

		return mid;
	}

	private static int getSqrt(int x) {
		int min = 0;
		int max = x;
		int mid = 0;

		while (min <= max) {
			mid = (min + max) / 2;
			if(mid * mid > x) {
				max = mid -1;
			} else if(mid * mid < x) {
				min = mid + 1;
			} else {
				return mid;
			}
		}
		
		return mid;
	}

}
