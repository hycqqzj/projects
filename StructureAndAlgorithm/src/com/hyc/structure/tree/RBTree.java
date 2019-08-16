package com.hyc.structure.tree;

import com.hyc.structure.tree.node.RBTreeNode;

import lombok.Data;

/**
 * ��������ڵ�ֵ���ظ���
 */
@Data
public class RBTree<T extends Comparable<T>> {
	private RBTreeNode<T> root;
	// ȫ��NIL�ڵ�
	private RBTreeNode<T> nilNode;

	public RBTree() {
		this.root = null;
		nilNode = new RBTreeNode<T>(null, RBTreeNode.BLACK);
	}

	/**
	 * �������
	 * 
	 * @param root
	 */
	public void midOrder(RBTreeNode<T> root) {
		if (root == nilNode) {
			return;
		}

		midOrder(root.left);
		System.out.print(root.data + ",");
		midOrder(root.right);
	}

	/**
	 * ����
	 * 
	 * @param data
	 * @return
	 */
	public RBTreeNode<T> search(T data) {
		if (this.root == null) {
			return null;
		}

		RBTreeNode<T> node = this.root;
		while (node != nilNode) {
			if (data.compareTo(node.data) > 0) {
				node = node.right;
			} else if (data.compareTo(node.data) < 0) {
				node = node.left;
			} else {
				return node;
			}
		}

		return null;
	}

	/**
	 * ��λ��С�ڵ�
	 * 
	 * @param rootNode
	 * @return
	 */
	public RBTreeNode<T> findMin(RBTreeNode<T> rootNode) {
		if (rootNode == null || rootNode == nilNode) {
			return null;
		}

		if (rootNode.left != nilNode) {
			return findMin(rootNode.left);
		} else {
			return rootNode;
		}
	}

	/**
	 * ��λ���ڵ�
	 * 
	 * @param rootNode
	 * @return
	 */
	public RBTreeNode<T> findMax(RBTreeNode<T> rootNode) {
		if (rootNode == null || rootNode == nilNode) {
			return null;
		}

		if (rootNode.right != nilNode) {
			return findMax(rootNode.right);
		} else {
			return rootNode;
		}
	}

	/**
	 * ����
	 * 
	 * @param data
	 */
	public void insert(T data) {
		RBTreeNode<T> insNode = new RBTreeNode<T>(data, RBTreeNode.RED, null, nilNode, nilNode);
		// ����ڵ�
		if (this.insert(insNode)) {
			// ���ú�ɫ
			insNode.color = RBTreeNode.RED;
			// ��ƽ��
			insertBalance(insNode);
		}
	}

	/**
	 * ����������Ĳ������
	 * 
	 * @param insNode
	 */
	private boolean insert(RBTreeNode<T> insNode) {
		if (this.root == null) {
			this.root = insNode;
			return true;
		}

		RBTreeNode<T> node = this.root;
		while (node != nilNode) {
			if (insNode.data.compareTo(node.data) < 0) {
				if (node.left == nilNode) {
					node.left = insNode;
					insNode.parent = node;
					return true;
				} else {
					node = node.left;
				}
			}
			if (insNode.data.compareTo(node.data) > 0) {
				if (node.right == nilNode) {
					node.right = insNode;
					insNode.parent = node;
					return true;
				} else {
					node = node.right;
				}
			}
		}

		return false;
	}

	/**
	 * ��������µ���ƽ��
	 * 
	 * @param focNode��ǰ��ע�ڵ�
	 */
	private void insertBalance(RBTreeNode<T> focNode) {
		// ���ڵ��ź�ɫ
		if (focNode == this.root) {
			focNode.color = RBTreeNode.BLACK;
			return;
		}
		// ���ڵ�Ϊ��ɫ����Ȼ�����������ʣ����ö�����ɫ����Ҫ����
		if (focNode.parent.color == RBTreeNode.RED) {
			// ���ڵ�
			RBTreeNode<T> parent = focNode.parent;
			// �游�ڵ�
			RBTreeNode<T> grandParent = parent.parent;
			// ����ڵ�
			RBTreeNode<T> uncle = grandParent.left == parent ? grandParent.right : grandParent.left;
			if (uncle.color == RBTreeNode.RED) {
				// ��ע�������ڵ�Ϊ��ɫ
				uncle.color = RBTreeNode.BLACK;
				parent.color = RBTreeNode.BLACK;
				grandParent.color = RBTreeNode.RED;
				focNode = grandParent;
				insertBalance(focNode);
			} else if (focNode == parent.right) {
				// ��ע�������ڵ�Ϊ��ɫ���ҹ�ע�����丸�ڵ���Һ���
				focNode = parent;
				rotateLeft(focNode);
				insertBalance(focNode);
			} else {
				// ��ע�������ڵ�Ϊ��ɫ���ҹ�ע�����丸�ڵ������
				rotateRight(grandParent);
				boolean tmp = grandParent.color;
				grandParent.color = parent.color;
				parent.color = tmp;
			}
		}
	}

	/**
	 * ɾ��
	 * 
	 * @param data
	 */
	public void remove(T data) {
		RBTreeNode<T> delNode = search(data);
		// ɾ���ڵ�
		if (delNode != null && delNode != nilNode) {
			this.remove(delNode);
			// ��ƽ��
		}
	}

	private void remove(RBTreeNode<T> delNode) {
		RBTreeNode<T> delParent = delNode.parent;
		// Ҫɾ�����Ǹ��ڵ�
		if (delParent == null) {
			this.root = null;
			return;
		}
		
		// Ҫɾ���Ľڵ��������ӽڵ�
		if (delNode.right != nilNode && delNode.left != nilNode) {
			// ��ȡ��������С�ڵ�
			RBTreeNode<T> minNode = findMin(delNode.right);
			// ������ǰ�ڵ����������С�ڵ�
			delNode.data = minNode.data;
			// ����Ҫɾ���Ľڵ�Ϊ��������С�ڵ�
			delNode = minNode;
			// Ҫɾ���ڵ�ĸ��ڵ�Ҳ��Ӧ���
			delParent = minNode.parent;
		}
		
		// Ҫɾ���Ľڵ����������һ���ӽڵ�
		RBTreeNode<T> delChild = nilNode;
		// ��ȡҪɾ���ڵ�ĺ��ӽڵ�
		if(delNode.left != nilNode) {
			delChild = delNode.left;
		} else if(delNode.right != nilNode) {
			delChild = delNode.right;
		}
		// ���ø��ڵ��ָ��
		if (delParent.left == delNode) {
			delParent.left = delChild;
		} else {
			delParent.right = delChild;
		}
	}

	/** ���� */
	private void rotateLeft(RBTreeNode<T> p) {
		if (p == null) {
			return;
		}

		RBTreeNode<T> r = p.right;
		p.right = r.left;
		if (r.left != null) {
			r.left.parent = p;
		}
		r.parent = p.parent;
		if (p.parent == null) {
			this.root = r;
		} else if (p.parent.left == p) {
			p.parent.left = r;
		} else {
			p.parent.right = r;
		}

		r.left = p;
		p.parent = r;
	}

	/** ���� */
	private void rotateRight(RBTreeNode<T> p) {
		if (p == null) {
			return;
		}

		RBTreeNode<T> l = p.left;
		p.left = l.right;
		if (l.right != null) {
			l.right.parent = p;
		}
		l.parent = p.parent;
		if (p.parent == null) {
			this.root = l;
		} else if (p.parent.left == p) {
			p.parent.left = l;
		} else {
			p.parent.right = l;
		}

		l.right = p;
		p.parent = l;
	}

	public static void main(String[] args) {
		RBTree<Integer> tree = new RBTree<Integer>();
		tree.insert(4);
		tree.insert(6);
		tree.insert(2);
		tree.insert(3);
		tree.insert(1);
		tree.insert(5);
		tree.insert(8);
		tree.insert(7);
		tree.insert(9);
		tree.midOrder(tree.root);
		
		System.out.println("\r\n===================");
		tree.remove(6);
		tree.midOrder(tree.root);
	}
}
