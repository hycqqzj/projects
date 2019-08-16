package com.hyc.structure.tree;

import com.hyc.structure.tree.node.AVLTreeNode;

import lombok.Getter;
import lombok.Setter;

public class AVLTree<T extends Comparable<T>> {
	private static final int ALLOWED_IMBALANCE = 1;

	@Getter
	@Setter
	private AVLTreeNode<T> root;

	public void printTree(AVLTreeNode<T> t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.data);
			printTree(t.right);
		}
	}

	public AVLTreeNode<T> findMin(AVLTreeNode<T> node) {
		// 结束条件
		if (node == null) {
			return null;
		}

		if (node.left == null) {
			// 如果没有左结点,那么t就是最小的
			return node;
		}

		return findMin(node.left);
	}

	public AVLTreeNode<T> findMax(AVLTreeNode<T> node) {
		if (node == null) {
			return null;
		}

		if (node.right == null) {
			// 如果没有右结点,那么t就是最大的
			return node;
		}

		return findMax(node.right);
	}

	/**
	 * 插入
	 * 
	 * @param data
	 */
	public void insert(T data) {
		if (data == null) {
			throw new RuntimeException("要插入的数据为空");
		}
		this.root = insert(data, root);
	}

	/**
	 * 插入
	 * 
	 * @param data要插入的值
	 * @param rootNode子树根节点
	 * @return 插入后新子树的根节点
	 */
	private AVLTreeNode<T> insert(T data, AVLTreeNode<T> rootNode) {
		if (rootNode == null) {
			return new AVLTreeNode<T>(data);
		}

		// 比较要插入的值和子树根节点值的大小
		int cmp = data.compareTo(rootNode.data);
		if (cmp < 0) {
			// 插入到左子树
			rootNode.left = insert(data, rootNode.left);
		} else if (cmp > 0) {
			// 插入到右子树
			rootNode.right = insert(data, rootNode.right);
		}

		return balance(rootNode);
	}

	public AVLTreeNode<T> remove(T data) {
		if (data == null) {
			throw new RuntimeException("要删除的数据为空");
		}

		return remove(data, root);
	}

	/**
	 * 删除
	 * 
	 * @param data     要删除的值
	 * @param rootNode 子树根节点
	 * @return 删除后新子树的根节点
	 */
	private AVLTreeNode<T> remove(T data, AVLTreeNode<T> rootNode) {
		if (rootNode == null) {
			return null;
		}

		int cmp = data.compareTo(rootNode.data);
		if (cmp < 0) {
			// 在左子树中删除
			rootNode.left = remove(data, rootNode.left);
		} else if (cmp > 0) {
			// 在右子树中删除
			rootNode.right = remove(data, rootNode.right);
		} else if (rootNode.left != null && rootNode.right != null) {
			// 有两个子节点，找到右子树中最小节点并另当前节点值等于最小节点值
			rootNode.data = findMin(rootNode).data;
			// 删除右子树中最小节点
			rootNode.right = remove(rootNode.data, rootNode.right);
		} else {
			// 只有一个子节点
			rootNode = rootNode.left != null ? rootNode.left : rootNode.right;
		}

		return balance(rootNode);
	}

	/**
	 * AVL树的平衡
	 * 
	 * @param r失衡点
	 * @return
	 */
	private AVLTreeNode<T> balance(AVLTreeNode<T> r) {
		if (r == null) {
			return r;
		}

		if (height(r.left) - height(r.right) > ALLOWED_IMBALANCE) {
			// 左子树失衡
			if (height(r.left.left) >= height(r.left.right)) {
				// LL型
				r = rotateWithLeftChild(r);
			} else {
				// LR型
				r = doubleWithLeftChild(r);
			}
		} else if (height(r.right) - height(r.left) > ALLOWED_IMBALANCE) {
			// 右子树失衡
			if (height(r.right.right) >= height(r.right.left)) {
				// RR型
				r = rotateWithRightChild(r);
			} else {
				// RL型
				r = doubleWithRightChild(r);
			}
		}

		r.height = Math.max(height(r.left), height(r.right)) + 1;
		return r;
	}

	/**
	 * LL单旋转
	 * 
	 * @param r失衡点
	 * @return
	 */
	private AVLTreeNode<T> rotateWithLeftChild(AVLTreeNode<T> r) {
		AVLTreeNode<T> node = r.left;
		r.left = node.right;
		node.right = r;

		r.height = Math.max(height(r.left), height(r.right)) + 1;
		node.height = Math.max(height(node.left), r.height) + 1;

		return node;
	}

	/**
	 * RR单旋转
	 * 
	 * @param r失衡点
	 * @return
	 */
	private AVLTreeNode<T> rotateWithRightChild(AVLTreeNode<T> r) {
		AVLTreeNode<T> node = r.right;
		r.right = node.left;
		node.left = r;

		r.height = Math.max(height(r.left), height(r.right)) + 1;
		node.height = Math.max(height(node.right), r.height) + 1;
		return node;
	}

	/**
	 * LR双旋转
	 * 
	 * @param r失衡点
	 * @return
	 */
	private AVLTreeNode<T> doubleWithLeftChild(AVLTreeNode<T> r) {
		r.left = rotateWithRightChild(r.left);
		return rotateWithLeftChild(r);
	}

	/**
	 * RL双旋转
	 * 
	 * @param r失衡点
	 * @return
	 */
	private AVLTreeNode<T> doubleWithRightChild(AVLTreeNode<T> r) {
		r.right = rotateWithLeftChild(r.right);
		return rotateWithRightChild(r);
	}

	private int height(AVLTreeNode<T> node) {
		return node == null ? -1 : node.height;
	}

	public static void main(String[] args) {
		AVLTree<Integer> avlTree = new AVLTree<>();
		for (int i = 1; i < 10; i++) {
			avlTree.insert(i);
		}
		avlTree.printTree(avlTree.root);
		System.out.println("================");
		avlTree.remove(5);
		avlTree.printTree(avlTree.root);
	}

}
