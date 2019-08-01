package com.hyc.structure.tree;

import java.util.LinkedList;

/**
 * 二叉树
 */
public class BinTree {
	private TreeNode root;

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * 前序遍历，data-left-right
	 * 
	 * @param root
	 */
	public void preOrder(TreeNode root) {
		if (root == null) {
			return;
		}

		System.out.print(root.getData() + ",");
		preOrder(root.getLeft());
		preOrder(root.getRight());
	}

	/**
	 * 中序遍历，left-data-right
	 * 
	 * @param root
	 */
	public void midOrder(TreeNode root) {
		if (root == null) {
			return;
		}

		midOrder(root.getLeft());
		System.out.print(root.getData() + ",");
		midOrder(root.getRight());
	}

	/**
	 * 后序遍历，left-right-data
	 * 
	 * @param root
	 */
	public void postOrder(TreeNode root) {
		if (root == null) {
			return;
		}

		postOrder(root.getLeft());
		postOrder(root.getRight());
		System.out.print(root.getData() + ",");
	}

	/**
	 * 层序遍历
	 * 
	 * @param queue
	 */
	public void levelOrder(LinkedList<TreeNode> queue) {
		if(queue == null) {
			queue = new LinkedList<TreeNode>();
			if(root != null) {
				queue.add(root);
			}
		}
		
		TreeNode node = queue.poll();
		if(node == null) {
			return;
		}
		
		System.out.println(node.getData());
		if(node.getLeft() != null) {
			queue.add(node.getLeft());
		}
		if(node.getRight() != null) {
			queue.add(node.getRight());
		}
		
		levelOrder(queue);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(10);
		TreeNode node11 = new TreeNode(11);
		TreeNode node12 = new TreeNode(12);
		TreeNode node13 = new TreeNode(13);

		root.insertLeftAndRight(node2, node3);
		node2.insertLeftAndRight(node4, node5);
		node3.insertLeftAndRight(node6, node7);
		node4.insertLeftAndRight(node8, node9);
		node5.insertLeftAndRight(null, node10);
		node7.insertLeftAndRight(node11, null);
		node8.insertLeftAndRight(node12, null);
		node11.insertLeftAndRight(null, node13);

		BinTree tree = new BinTree();
		tree.setRoot(root);

		tree.levelOrder(null);
	}

}
