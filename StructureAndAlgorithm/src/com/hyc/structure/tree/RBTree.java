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
			RBTreeNode<T> uncle = this.getUncle(focNode);
			if (uncle.color == RBTreeNode.RED) {
				// 关注点的叔叔节点为红色
				uncle.color = RBTreeNode.BLACK;
				parent.color = RBTreeNode.BLACK;
				grandParent.color = RBTreeNode.RED;
				focNode = grandParent;
				this.insertBalance(focNode);
			} else if (focNode == parent.right) {
				// 关注点的叔叔节点为黑色，且关注点是其父节点的右孩子
				focNode = parent;
				this.rotateLeft(focNode);
				this.insertBalance(focNode);
			} else {
				// 关注点的叔叔节点为黑色，且关注点是其父节点的左孩子
				this.rotateRight(grandParent);
				this.swapColor(parent, grandParent);
			}
		}
	}

	/**
	 * 删除，重平衡算法有误
	 * 
	 * @param data
	 */
	public RBTreeNode<T> remove(T data) {
		// 待删除节点
		RBTreeNode<T> delNode = this.search(data);
		// 取代节点
		RBTreeNode<T> repNode = null;
		// 删除节点
		if (delNode != null && delNode != nilNode) {
			RBTreeNode<T> focNode = this.copy(delNode);
			repNode = this.remove(delNode);
			// 重平衡-黑色节点数量
			focNode = removeBalanceBlack(focNode, repNode);
			// 重平衡-相邻红色节点
			if(focNode != null) {
				removeBalanceRed(focNode);
			}
		}

		return delNode;
	}

	/**
	 * 删除调整第一阶段：黑色节点数量调整
	 * 
	 * @param delNode 删除的节点
	 * @param repNode 取代删除节点位置的节点-取代节点
	 * @return 新的关注节点
	 */
	private RBTreeNode<T> removeBalanceBlack(RBTreeNode<T> delNode, RBTreeNode<T> repNode) {
		RBTreeNode<T> focNode = null;
		RBTreeNode<T> parent = delNode.parent;
		if (delNode.left == nilNode && delNode.right == nilNode) {
			// 删除的节点没有非空子节点，且颜色是黑色，且非根节点
			if (delNode.color == RBTreeNode.BLACK && parent != null) {
				// 父节点加黑色
				this.addBlack(parent);
				focNode = parent;
			}
		} else if (delNode.left == nilNode || delNode.right == nilNode) {
			// 删除的节点只有一个非空子节点
			repNode.color = delNode.color;
		} else {
			// 删除的节点有两个非空子节点
			if (repNode.color == RBTreeNode.BLACK) {
				this.addBlack(repNode.right);
				focNode = repNode.right;
			}
			repNode.color = delNode.color;
		}

		return focNode;
	}

	/**
	 * 删除调整第二阶段：相邻红色节点调整
	 * 
	 * @param focNode
	 * @return
	 */
	private void removeBalanceRed(RBTreeNode<T> focNode) {
		RBTreeNode<T> parent = this.getParent(focNode);
		RBTreeNode<T> brother = this.getBrother(focNode);

		if (brother != null && brother.color == RBTreeNode.RED) {
			// 兄弟节点为红色
			this.rotateLeft(parent);
			this.swapColor(brother, parent);
			this.removeBalanceRed(focNode);
		} else if (brother != null && brother.color == RBTreeNode.BLACK && brother.left != null && brother.right != null) {
			// 兄弟节点为黑色
			if (brother.left.color == RBTreeNode.BLACK && brother.right.color == RBTreeNode.BLACK) {
				// 兄弟的左右孩子都是黑色
				brother.color = RBTreeNode.RED;
				this.minusBlack(focNode);
				this.addBlack(parent);
				this.removeBalanceRed(parent);
			} else if(brother.left.color == RBTreeNode.RED && brother.right.color == RBTreeNode.BLACK) {
				// 兄弟的左孩子是红色，右孩子是黑色
				this.swapColor(brother, brother.left);
				this.rotateRight(brother);
				this.removeBalanceRed(focNode);
			} else if(brother.right.color == RBTreeNode.RED) {
				// 兄弟的右孩子是红色
				brother.color = parent.color;
				brother.right.color = RBTreeNode.BLACK;
				this.rotateLeft(parent);
				parent.color = RBTreeNode.BLACK;
				this.minusBlack(focNode);
			}
		}
	}

	/**
	 * 删除
	 * 
	 * @param delNode
	 * @return 取代节点
	 */
	private RBTreeNode<T> remove(RBTreeNode<T> delNode) {
		// 取代节点
		RBTreeNode<T> rep = null;
		RBTreeNode<T> delParent = delNode.parent;
		// 要删除的节点有两个子节点
		if (delNode.right != nilNode && delNode.left != nilNode) {
			// 取代节点为本身
			rep = delNode;
			// 获取右子树最小节点
			RBTreeNode<T> minNode = findMin(delNode.right);
			// 交换当前节点和右子树最小节点
			delNode.data = minNode.data;
			delNode.color = minNode.color;
			// 设置要删除的节点为右子树最小节点
			delNode = minNode;
			// 要删除节点的父节点也相应变更
			delParent = minNode.parent;
		}

		// 要删除的节点有零个或者一个子节点
		RBTreeNode<T> delChild = nilNode;
		// 获取要删除节点的孩子节点
		if (delNode.left != nilNode) {
			delChild = delNode.left;
		} else if (delNode.right != nilNode) {
			delChild = delNode.right;
		}

		// 设置父节点的指针
		if (delParent == null) {
			if (delChild == nilNode) {
				// 要删除的是根节点，且没有孩子
				delChild = null;
			} else {
				// 要删除的是根节点，且有孩子
				this.root = delChild;
			}
		} else if (delParent.left == delNode) {
			delParent.left = delChild;
		} else {
			delParent.right = delChild;
		}
		// 取代节点和删除节点在同一个位置时返回删除节点位置，否则返回删除节点的孩子节点
		return rep != null ? rep : delChild;
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
