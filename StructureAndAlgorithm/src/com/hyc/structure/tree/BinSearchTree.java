package com.hyc.structure.tree;

import com.hyc.structure.tree.node.TreeNode;

import lombok.Getter;
import lombok.Setter;

/**
 * 基于循环实现的二叉查找树
 */
public class BinSearchTree {
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
	 * 查找
	 * 
	 * @param data
	 * @return
	 */
	public TreeNode search(int data) {
		TreeNode p = root;
		while (p != null) {
			if (data > p.getData()) {
				p = p.getRight();
			} else if (data < p.getData()) {
				p = p.getLeft();
			} else {
				return p;
			}
		}

		return null;
	}

	public void insert(int data) {
		TreeNode p = root;
		while (p != null) {
			if (data > p.getData()) {
				if (p.getRight() == null) {
					p.setRight(new TreeNode(data));
					return;
				}
				p = p.getRight();
			}
			if (data < p.getData()) {
				if (p.getLeft() == null) {
					p.setLeft(new TreeNode(data));
					return;
				}
				p = p.getLeft();
			}
		}
	}

	public void delete(int data) {
		// 要删除的节点
		TreeNode del = root;
		// 要删除节点的父节点
		TreeNode delParent = null;
		while (del != null && data != del.getData()) {
			delParent = del;
			if (data > del.getData()) {
				del = del.getRight();
			} else {
				del = del.getLeft();
			}
		}
		// 未找到直接返回
		if(del == null) {
			return;
		}
		
		// 要删除的节点有两个子节点
		if(del.getLeft() != null && del.getRight() != null) {
			// 右子树最小节点
			TreeNode rightMin = del.getRight();
			// 右子树最小节点的父节点
			TreeNode rightMinParent = del;
			while (rightMin.getLeft() != null) {
				rightMinParent = rightMin;
				rightMin = rightMin.getLeft();
			}
			// 交换右子树最小节点的值到要删除节点中
			del.setData(rightMin.getData());
			// 设置要删除的节点为右子树最小节点
			del = rightMin;
			// 设置父节点
			delParent = rightMinParent;
		}
		
		// 要删除的节点有一个或零个子节点
		// 要删除节点的子节点
		TreeNode child = null;
		if(del.getLeft() != null) {
			child = del.getLeft();
		} else if(del.getRight() != null) {
			child = del.getRight();
		}
		
		// 要删除的是根节点
		if(delParent == null) {
			root = child;
		} else if(delParent.getLeft() == del) {
			// 要删除的节点是父节点的左节点
			delParent.setLeft(child);
		} else {
			// 要删除的节点不是父节点的左节点（右节点）
			delParent.setRight(child);
		}
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

		BinSearchTree tree = new BinSearchTree();
		tree.setRoot(root);

		tree.midOrder(tree.getRoot());

		System.out.println("\r\n=======查找========");
		TreeNode node = tree.search(19);
		System.out.println(node != null ? node.getData() : null);

		System.out.println("=======插入========");
		tree.insert(55);
		tree.midOrder(tree.getRoot());
		
		System.out.println("\r\n=======删除========");
		tree.delete(50);
		tree.midOrder(tree.getRoot());
	}

}
