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
			RBTreeNode<T> uncle = this.getUncle(focNode);
			if (uncle.color == RBTreeNode.RED) {
				// ��ע�������ڵ�Ϊ��ɫ
				uncle.color = RBTreeNode.BLACK;
				parent.color = RBTreeNode.BLACK;
				grandParent.color = RBTreeNode.RED;
				focNode = grandParent;
				this.insertBalance(focNode);
			} else if (focNode == parent.right) {
				// ��ע�������ڵ�Ϊ��ɫ���ҹ�ע�����丸�ڵ���Һ���
				focNode = parent;
				this.rotateLeft(focNode);
				this.insertBalance(focNode);
			} else {
				// ��ע�������ڵ�Ϊ��ɫ���ҹ�ע�����丸�ڵ������
				this.rotateRight(grandParent);
				this.swapColor(parent, grandParent);
			}
		}
	}

	/**
	 * ɾ������ƽ���㷨����
	 * 
	 * @param data
	 */
	public RBTreeNode<T> remove(T data) {
		// ��ɾ���ڵ�
		RBTreeNode<T> delNode = this.search(data);
		// ȡ���ڵ�
		RBTreeNode<T> repNode = null;
		// ɾ���ڵ�
		if (delNode != null && delNode != nilNode) {
			RBTreeNode<T> focNode = this.copy(delNode);
			repNode = this.remove(delNode);
			// ��ƽ��-��ɫ�ڵ�����
			focNode = removeBalanceBlack(focNode, repNode);
			// ��ƽ��-���ں�ɫ�ڵ�
			if(focNode != null) {
				removeBalanceRed(focNode);
			}
		}

		return delNode;
	}

	/**
	 * ɾ��������һ�׶Σ���ɫ�ڵ���������
	 * 
	 * @param delNode ɾ���Ľڵ�
	 * @param repNode ȡ��ɾ���ڵ�λ�õĽڵ�-ȡ���ڵ�
	 * @return �µĹ�ע�ڵ�
	 */
	private RBTreeNode<T> removeBalanceBlack(RBTreeNode<T> delNode, RBTreeNode<T> repNode) {
		RBTreeNode<T> focNode = null;
		RBTreeNode<T> parent = delNode.parent;
		if (delNode.left == nilNode && delNode.right == nilNode) {
			// ɾ���Ľڵ�û�зǿ��ӽڵ㣬����ɫ�Ǻ�ɫ���ҷǸ��ڵ�
			if (delNode.color == RBTreeNode.BLACK && parent != null) {
				// ���ڵ�Ӻ�ɫ
				this.addBlack(parent);
				focNode = parent;
			}
		} else if (delNode.left == nilNode || delNode.right == nilNode) {
			// ɾ���Ľڵ�ֻ��һ���ǿ��ӽڵ�
			repNode.color = delNode.color;
		} else {
			// ɾ���Ľڵ��������ǿ��ӽڵ�
			if (repNode.color == RBTreeNode.BLACK) {
				this.addBlack(repNode.right);
				focNode = repNode.right;
			}
			repNode.color = delNode.color;
		}

		return focNode;
	}

	/**
	 * ɾ�������ڶ��׶Σ����ں�ɫ�ڵ����
	 * 
	 * @param focNode
	 * @return
	 */
	private void removeBalanceRed(RBTreeNode<T> focNode) {
		RBTreeNode<T> parent = this.getParent(focNode);
		RBTreeNode<T> brother = this.getBrother(focNode);

		if (brother != null && brother.color == RBTreeNode.RED) {
			// �ֵܽڵ�Ϊ��ɫ
			this.rotateLeft(parent);
			this.swapColor(brother, parent);
			this.removeBalanceRed(focNode);
		} else if (brother != null && brother.color == RBTreeNode.BLACK && brother.left != null && brother.right != null) {
			// �ֵܽڵ�Ϊ��ɫ
			if (brother.left.color == RBTreeNode.BLACK && brother.right.color == RBTreeNode.BLACK) {
				// �ֵܵ����Һ��Ӷ��Ǻ�ɫ
				brother.color = RBTreeNode.RED;
				this.minusBlack(focNode);
				this.addBlack(parent);
				this.removeBalanceRed(parent);
			} else if(brother.left.color == RBTreeNode.RED && brother.right.color == RBTreeNode.BLACK) {
				// �ֵܵ������Ǻ�ɫ���Һ����Ǻ�ɫ
				this.swapColor(brother, brother.left);
				this.rotateRight(brother);
				this.removeBalanceRed(focNode);
			} else if(brother.right.color == RBTreeNode.RED) {
				// �ֵܵ��Һ����Ǻ�ɫ
				brother.color = parent.color;
				brother.right.color = RBTreeNode.BLACK;
				this.rotateLeft(parent);
				parent.color = RBTreeNode.BLACK;
				this.minusBlack(focNode);
			}
		}
	}

	/**
	 * ɾ��
	 * 
	 * @param delNode
	 * @return ȡ���ڵ�
	 */
	private RBTreeNode<T> remove(RBTreeNode<T> delNode) {
		// ȡ���ڵ�
		RBTreeNode<T> rep = null;
		RBTreeNode<T> delParent = delNode.parent;
		// Ҫɾ���Ľڵ��������ӽڵ�
		if (delNode.right != nilNode && delNode.left != nilNode) {
			// ȡ���ڵ�Ϊ����
			rep = delNode;
			// ��ȡ��������С�ڵ�
			RBTreeNode<T> minNode = findMin(delNode.right);
			// ������ǰ�ڵ����������С�ڵ�
			delNode.data = minNode.data;
			delNode.color = minNode.color;
			// ����Ҫɾ���Ľڵ�Ϊ��������С�ڵ�
			delNode = minNode;
			// Ҫɾ���ڵ�ĸ��ڵ�Ҳ��Ӧ���
			delParent = minNode.parent;
		}

		// Ҫɾ���Ľڵ����������һ���ӽڵ�
		RBTreeNode<T> delChild = nilNode;
		// ��ȡҪɾ���ڵ�ĺ��ӽڵ�
		if (delNode.left != nilNode) {
			delChild = delNode.left;
		} else if (delNode.right != nilNode) {
			delChild = delNode.right;
		}

		// ���ø��ڵ��ָ��
		if (delParent == null) {
			if (delChild == nilNode) {
				// Ҫɾ�����Ǹ��ڵ㣬��û�к���
				delChild = null;
			} else {
				// Ҫɾ�����Ǹ��ڵ㣬���к���
				this.root = delChild;
			}
		} else if (delParent.left == delNode) {
			delParent.left = delChild;
		} else {
			delParent.right = delChild;
		}
		// ȡ���ڵ��ɾ���ڵ���ͬһ��λ��ʱ����ɾ���ڵ�λ�ã����򷵻�ɾ���ڵ�ĺ��ӽڵ�
		return rep != null ? rep : delChild;
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

	private void addBlack(RBTreeNode<T> node) {
		if (node == null) {
			return;
		}
		if (node.color == RBTreeNode.BLACK) {
			node.color = RBTreeNode.BLACK_BLACK;
		} else if (node.color == RBTreeNode.RED) {
			node.color = RBTreeNode.RED_BLACK;
		}
	}

	private void minusBlack(RBTreeNode<T> node) {
		if (node == null) {
			return;
		}
		if (node.color == RBTreeNode.BLACK_BLACK) {
			node.color = RBTreeNode.BLACK;
		} else if (node.color == RBTreeNode.RED_BLACK) {
			node.color = RBTreeNode.RED;
		}
	}

	private RBTreeNode<T> getParent(RBTreeNode<T> node) {
		return node != null ? node.parent : null;
	}

	private RBTreeNode<T> getUncle(RBTreeNode<T> node) {
		RBTreeNode<T> parent = this.getParent(node);
		RBTreeNode<T> grandParent = this.getParent(parent);
		if (parent == null || grandParent == null) {
			return null;
		}
		return grandParent.left == parent ? grandParent.right : grandParent.left;
	}

	private RBTreeNode<T> getBrother(RBTreeNode<T> node) {
		RBTreeNode<T> parent = this.getParent(node);
		if (parent == null) {
			return null;
		}
		return parent.left == node ? parent.right : parent.left;
	}

	private void swapColor(RBTreeNode<T> node1, RBTreeNode<T> node2) {
		int color = node1.color;
		node1.color = node2.color;
		node2.color = color;
	}
	
	private RBTreeNode<T> copy(RBTreeNode<T> node) {
		if(node == null) {
			return null;
		}
		return new RBTreeNode<T>(node.data, node.color, node.parent, node.left, node.right);
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
		tree.remove(5);
		tree.midOrder(tree.root);
	}
}
