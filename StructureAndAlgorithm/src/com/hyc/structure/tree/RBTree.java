package com.hyc.structure.tree;

import com.hyc.structure.tree.node.RBTreeNode;

import lombok.Data;

/**
 * 红黑树（节点值不重复）
 */
@Data
public class RBTree<T extends Comparable<T>> {
	private RBTreeNode<T> root;
	// 全局NIL节点
	private RBTreeNode<T> nilNode;

	public RBTree() {
		this.root = null;
		nilNode = new RBTreeNode<T>(null, RBTreeNode.BLACK);
	}

	/**
	 * 中序遍历
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
	 * 查找
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
	 * 定位最小节点
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
	 * 定位最大节点
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
	 * 插入
	 * 
	 * @param data
	 */
	public void insert(T data) {
		RBTreeNode<T> insNode = new RBTreeNode<T>(data, RBTreeNode.RED, null, nilNode, nilNode);
		// 插入节点
		if (this.insert(insNode)) {
			// 设置红色
			insNode.color = RBTreeNode.RED;
			// 重平衡
			insertBalance(insNode);
		}
	}

	/**
	 * 二叉查找树的插入操作
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
	 * 插入情况下的重平衡
	 * 
	 * @param focNode当前关注节点
	 */
	private void insertBalance(RBTreeNode<T> focNode) {
		// 根节点着黑色
		if (focNode == this.root) {
			focNode.color = RBTreeNode.BLACK;
			return;
		}
		// 父节点为黑色，依然满足红黑树性质，不用动，红色才需要调整
		if (focNode.parent.color == RBTreeNode.RED) {
			// 父节点
			RBTreeNode<T> parent = focNode.parent;
			// 祖父节点
			RBTreeNode<T> grandParent = parent.parent;
			// 叔叔节点
			RBTreeNode<T> uncle = grandParent.left == parent ? grandParent.right : grandParent.left;
			if (uncle.color == RBTreeNode.RED) {
				// 关注点的叔叔节点为红色
				uncle.color = RBTreeNode.BLACK;
				parent.color = RBTreeNode.BLACK;
				grandParent.color = RBTreeNode.RED;
				focNode = grandParent;
				insertBalance(focNode);
			} else if (focNode == parent.right) {
				// 关注点的叔叔节点为黑色，且关注点是其父节点的右孩子
				focNode = parent;
				rotateLeft(focNode);
				insertBalance(focNode);
			} else {
				// 关注点的叔叔节点为黑色，且关注点是其父节点的左孩子
				rotateRight(grandParent);
				boolean tmp = grandParent.color;
				grandParent.color = parent.color;
				parent.color = tmp;
			}
		}
	}

	/**
	 * 删除
	 * 
	 * @param data
	 */
	public void remove(T data) {
		RBTreeNode<T> delNode = search(data);
		// 删除节点
		if (delNode != null && delNode != nilNode) {
			this.remove(delNode);
			// 重平衡
		}
	}

	private void remove(RBTreeNode<T> delNode) {
		RBTreeNode<T> delParent = delNode.parent;
		// 要删除的是根节点
		if (delParent == null) {
			this.root = null;
			return;
		}
		
		// 要删除的节点有两个子节点
		if (delNode.right != nilNode && delNode.left != nilNode) {
			// 获取右子树最小节点
			RBTreeNode<T> minNode = findMin(delNode.right);
			// 交换当前节点和右子树最小节点
			delNode.data = minNode.data;
			// 设置要删除的节点为右子树最小节点
			delNode = minNode;
			// 要删除节点的父节点也相应变更
			delParent = minNode.parent;
		}
		
		// 要删除的节点有零个或者一个子节点
		RBTreeNode<T> delChild = nilNode;
		// 获取要删除节点的孩子节点
		if(delNode.left != nilNode) {
			delChild = delNode.left;
		} else if(delNode.right != nilNode) {
			delChild = delNode.right;
		}
		// 设置父节点的指针
		if (delParent.left == delNode) {
			delParent.left = delChild;
		} else {
			delParent.right = delChild;
		}
	}

	/** 左旋 */
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

	/** 右旋 */
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
