package com.hyc.structure.tree;

import lombok.Data;

public class TrieTree {
	private TrieNode root;

	public TrieTree() {
		root = new TrieNode('/');
	}

	public void insert(char[] charArr) {
		TrieNode p = root;
		for (char cha : charArr) {
			int index = cha - 'a';
			if (p.children[index] == null) {
				TrieNode node = new TrieNode(cha);
				p.children[index] = node;
			}
			p = p.children[index];
		}
		p.isEndingChar = true;
	}

	public boolean find(char[] pattern) {
		boolean rs = true;
		TrieNode node = root;
		for (char cha : pattern) {
			int loc = cha - 'a';
			if (node.getChildren()[loc] == null) {
				rs = false;
				break;
			}
			node = node.getChildren()[loc];
		}
		// 不能完全匹配，只是前缀
		if (node.isEndingChar == false) {
			rs = false;
		} else {
			// 找到 pattern
			rs = true;
		}
		return rs;
	}

	public static void main(String[] args) {
		TrieTree tree = new TrieTree();
		tree.insert("how".toCharArray());
		tree.insert("her".toCharArray());
		tree.insert("hi".toCharArray());
		tree.insert("hello".toCharArray());
		
		System.out.println(tree.find("her".toCharArray()));
	}
}

@Data
class TrieNode {
	public char data;
	public TrieNode[] children = new TrieNode[26];
	public boolean isEndingChar = false;

	public TrieNode(char data) {
		this.data = data;
	}
}
