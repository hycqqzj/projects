package com.hyc.algorithm.patmatch;

/**
 * ×Ö·û´®Æ¥ÅäBFËã·¨£¨±©Á¦Æ¥Åä·¨£©
 */
public class StringMatchBF {
	public static int indexOf(String master, String pat) {
		if (master.length() < pat.length()) {
			return -1;
		}

		char[] masterCharArr = master.toCharArray();
		char[] patCharArr = pat.toCharArray();

		for (int i = 0; i < masterCharArr.length; i++) {
			if (i + patCharArr.length >= masterCharArr.length) {
				return -1;
			}

			int j = 0;
			while (j < patCharArr.length) {
				if (masterCharArr[i + j] == patCharArr[j++]) {
					if (j == patCharArr.length) {
						return i;
					}
				} else {
					break;
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		String master = "abcdefgh";
		String pat = "bc";
		System.out.println(indexOf(master, pat));
	}
}
