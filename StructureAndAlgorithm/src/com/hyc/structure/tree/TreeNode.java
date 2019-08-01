package com.hyc.structure.tree;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TreeNode {
	private int data;
	private TreeNode left;
	private TreeNode right;

	public TreeNode(int data) {
		this.data = data;
	}

	public void insertLeftAndRight(TreeNode left, TreeNode right) {
		this.left = left;
		this.right = right;
	}
}