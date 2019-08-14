package com.hyc.structure.tree;

import com.hyc.structure.tree.node.TreeNode;

import lombok.Getter;
import lombok.Setter;

/**
 * 基于递归实现的二叉查找树
 */
public class BinarySearchTree {
	@Setter
	@Getter
	private TreeNode root;

	/**
	 * 中序遍历
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
	 * 查找节点
	 * 
	 * @param rootNode
	 * @param data
	 * @return
	 */
	public TreeNode search(TreeNode rootNode, int data) {
		if (rootNode == null) {
			return null;
		}

		if (rootNode.getData() == data) {
			return rootNode;
		} else if (rootNode.getData() < data) {
			return search(rootNode.getRight(), data);
		} else {
			return search(rootNode.getLeft(), data);
		}
	}

	/**
	 * 插入节点
	 * 
	 * @param rootNode
	 * @param node
	 */
	public void insert(TreeNode rootNode, TreeNode node) {
		if (root == null) {
			root = node;
		}

		if (rootNode.getData() < node.getData()) {
			if (rootNode.getRight() == null) {
				rootNode.setRight(node);
			} else {
				insert(rootNode.getRight(), node);
			}
		} else {
			if (rootNode.getLeft() == null) {
				rootNode.setLeft(node);
			} else {
				insert(rootNode.getLeft(), node);
			}
		}
	}

	/**
	 * 定位最小节点
	 * 
	 * @param rootNode
	 * @return
	 */
	public TreeNode getMin(TreeNode rootNode) {
		if (rootNode == null) {
			return null;
		}

		if (rootNode.getLeft() != null) {
			return getMin(rootNode.getLeft());
		} else {
			return rootNode;
		}
	}

	/**
	 * 定位最大节点
	 * 
	 * @param rootNode
	 * @return
	 */
	public TreeNode getMax(TreeNode rootNode) {
		if (rootNode == null) {
			return null;
		}

		if (rootNode.getRight() != null) {
			return getMax(rootNode.getRight());
		} else {
			return rootNode;
		}
	}
	
	/**
	 * 获取指定节点的父节点
	 * 
	 * @param root
	 * @param node
	 * @return
	 */
	public TreeNode getParent(TreeNode root, TreeNode node) {
		if (node == null) {
			return null;
		}
		
		if(root.getLeft() == node || root.getRight() == node) {
			return root;
		}
		if(root.getLeft() != null && node.getData() < root.getData()) {
			return getParent(root.getLeft(), node);
		}
		if(root.getRight() != null && node.getData() > root.getData()) {
			return getParent(root.getRight(), node);
		}
		
		return null;
	}
	
	/**
	 * 删除节点
	 * 
	 * @param node
	 * @return
	 */
	public TreeNode delete(int data) {
		TreeNode delNode = search(root, data);
		if(delNode == null) {
			return null;
		}
		
		TreeNode parentNode = getParent(root, delNode);
		if(delNode.getLeft() == null && delNode.getRight() == null) {
			// 要删除的节点没有孩子节点
			if(parentNode.getData() > delNode.getData()) {
				// 父节点数据比当前数据大则当前节点为父节点的左孩子
				parentNode.setLeft(null);
			} else {
				parentNode.setRight(null);
			}
		} else if(delNode.getLeft() == null || delNode.getRight() == null) {
			// 要删除的节点有一个孩子节点
			if(parentNode.getData() > delNode.getData()) {
				parentNode.setLeft(delNode.getLeft() != null ? delNode.getLeft() : delNode.getRight());
			} else {
				parentNode.setRight(delNode.getLeft() != null ? delNode.getLeft() : delNode.getRight());
			}
		} else {
			// 要删除的节点有两个孩子节点
			// 找到右子树的最小节点
			TreeNode rightMinNode = getMin(delNode.getRight());
			TreeNode minParent = getParent(root, rightMinNode);
			rightMinNode.setLeft(delNode.getLeft());
			if(parentNode.getData() > delNode.getData()) {
				parentNode.setLeft(rightMinNode);
			} else {
				parentNode.setRight(rightMinNode);
				minParent.setLeft(null);
				rightMinNode.setRight(delNode.getRight());
			}
		}
		
		return delNode;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(33);
		TreeNode node2 = new TreeNode(17);
		TreeNode node3 = new TreeNode(50);
		TreeNode node4 = new TreeNode(13);
		TreeNode node5 = new TreeNode(18);
		TreeNode node6 = new TreeNode(34);
		TreeNode node7 = new TreeNode(58);
		TreeNode node8 = new TreeNode(16);
		TreeNode node9 = new TreeNode(25);
		TreeNode node10 = new TreeNode(51);
		TreeNode node11 = new TreeNode(66);
		TreeNode node12 = new TreeNode(19);
		TreeNode node13 = new TreeNode(27);

		root.insertLeftAndRight(node2, node3);
		node2.insertLeftAndRight(node4, node5);
		node3.insertLeftAndRight(node6, node7);
		node4.insertLeftAndRight(null, node8);
		node5.insertLeftAndRight(null, node9);
		node7.insertLeftAndRight(node10, node11);
		node9.insertLeftAndRight(node12, node13);

		BinarySearchTree tree = new BinarySearchTree();
		tree.setRoot(root);

		tree.midOrder(tree.getRoot());
		System.out.println("\r\n=======查找========");
		TreeNode node = tree.search(tree.getRoot(), 19);
		System.out.println(node != null ? node.getData() : null);

		System.out.println("=======插入========");
		TreeNode node14 = new TreeNode(55);
		tree.insert(tree.getRoot(), node14);
		tree.midOrder(tree.getRoot());

		System.out.println("\r\n=======定位最大和最小========");
		System.out.println("最大：" + tree.getMax(tree.getRoot()).getData());
		System.out.println("最小：" + tree.getMin(tree.getRoot()).getData());
		
		System.out.println("=======获取父节点========");
		TreeNode parentNode = tree.getParent(tree.getRoot(), node13);
		System.out.println(parentNode);
		
		System.out.println("=======删除========");
		TreeNode deleteNode = tree.delete(50);
		System.out.println(deleteNode.getData());
	}

}
