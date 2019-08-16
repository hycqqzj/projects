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
		// ��������
		if (node == null) {
			return null;
		}

		if (node.left == null) {
			// ���û������,��ôt������С��
			return node;
		}

		return findMin(node.left);
	}

	public AVLTreeNode<T> findMax(AVLTreeNode<T> node) {
		if (node == null) {
			return null;
		}

		if (node.right == null) {
			// ���û���ҽ��,��ôt��������
			return node;
		}

		return findMax(node.right);
	}

	/**
	 * ����
	 * 
	 * @param data
	 */
	public void insert(T data) {
		if (data == null) {
			throw new RuntimeException("Ҫ���������Ϊ��");
		}
		this.root = insert(data, root);
	}

	/**
	 * ����
	 * 
	 * @param dataҪ�����ֵ
	 * @param rootNode�������ڵ�
	 * @return ������������ĸ��ڵ�
	 */
	private AVLTreeNode<T> insert(T data, AVLTreeNode<T> rootNode) {
		if (rootNode == null) {
			return new AVLTreeNode<T>(data);
		}

		// �Ƚ�Ҫ�����ֵ���������ڵ�ֵ�Ĵ�С
		int cmp = data.compareTo(rootNode.data);
		if (cmp < 0) {
			// ���뵽������
			rootNode.left = insert(data, rootNode.left);
		} else if (cmp > 0) {
			// ���뵽������
			rootNode.right = insert(data, rootNode.right);
		}

		return balance(rootNode);
	}

	public AVLTreeNode<T> remove(T data) {
		if (data == null) {
			throw new RuntimeException("Ҫɾ��������Ϊ��");
		}

		return remove(data, root);
	}

	/**
	 * ɾ��
	 * 
	 * @param data     Ҫɾ����ֵ
	 * @param rootNode �������ڵ�
	 * @return ɾ�����������ĸ��ڵ�
	 */
	private AVLTreeNode<T> remove(T data, AVLTreeNode<T> rootNode) {
		if (rootNode == null) {
			return null;
		}

		int cmp = data.compareTo(rootNode.data);
		if (cmp < 0) {
			// ����������ɾ��
			rootNode.left = remove(data, rootNode.left);
		} else if (cmp > 0) {
			// ����������ɾ��
			rootNode.right = remove(data, rootNode.right);
		} else if (rootNode.left != null && rootNode.right != null) {
			// �������ӽڵ㣬�ҵ�����������С�ڵ㲢��ǰ�ڵ�ֵ������С�ڵ�ֵ
			rootNode.data = findMin(rootNode).data;
			// ɾ������������С�ڵ�
			rootNode.right = remove(rootNode.data, rootNode.right);
		} else {
			// ֻ��һ���ӽڵ�
			rootNode = rootNode.left != null ? rootNode.left : rootNode.right;
		}

		return balance(rootNode);
	}

	/**
	 * AVL����ƽ��
	 * 
	 * @param rʧ���
	 * @return
	 */
	private AVLTreeNode<T> balance(AVLTreeNode<T> r) {
		if (r == null) {
			return r;
		}

		if (height(r.left) - height(r.right) > ALLOWED_IMBALANCE) {
			// ������ʧ��
			if (height(r.left.left) >= height(r.left.right)) {
				// LL��
				r = rotateWithLeftChild(r);
			} else {
				// LR��
				r = doubleWithLeftChild(r);
			}
		} else if (height(r.right) - height(r.left) > ALLOWED_IMBALANCE) {
			// ������ʧ��
			if (height(r.right.right) >= height(r.right.left)) {
				// RR��
				r = rotateWithRightChild(r);
			} else {
				// RL��
				r = doubleWithRightChild(r);
			}
		}

		r.height = Math.max(height(r.left), height(r.right)) + 1;
		return r;
	}

	/**
	 * LL����ת
	 * 
	 * @param rʧ���
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
	 * RR����ת
	 * 
	 * @param rʧ���
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
	 * LR˫��ת
	 * 
	 * @param rʧ���
	 * @return
	 */
	private AVLTreeNode<T> doubleWithLeftChild(AVLTreeNode<T> r) {
		r.left = rotateWithRightChild(r.left);
		return rotateWithLeftChild(r);
	}

	/**
	 * RL˫��ת
	 * 
	 * @param rʧ���
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
