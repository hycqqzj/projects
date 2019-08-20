package com.hyc.structure.tree;

import com.hyc.structure.tree.node.TreeNode;

import lombok.Getter;
import lombok.Setter;

/**
 * ����ѭ��ʵ�ֵĶ��������
 */
public class BinSearchTree {
	@Setter
	@Getter
	private TreeNode root;

	/**
	 * �������
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
	 * ��λ��С�ڵ�
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
	 * ��λ���ڵ�
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
	 * ����
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
		// Ҫɾ���Ľڵ�
		TreeNode del = root;
		// Ҫɾ���ڵ�ĸ��ڵ�
		TreeNode delParent = null;
		while (del != null && data != del.getData()) {
			delParent = del;
			if (data > del.getData()) {
				del = del.getRight();
			} else {
				del = del.getLeft();
			}
		}
		// δ�ҵ�ֱ�ӷ���
		if(del == null) {
			return;
		}
		
		// Ҫɾ���Ľڵ��������ӽڵ�
		if(del.getLeft() != null && del.getRight() != null) {
			// ��������С�ڵ�
			TreeNode rightMin = del.getRight();
			// ��������С�ڵ�ĸ��ڵ�
			TreeNode rightMinParent = del;
			while (rightMin.getLeft() != null) {
				rightMinParent = rightMin;
				rightMin = rightMin.getLeft();
			}
			// ������������С�ڵ��ֵ��Ҫɾ���ڵ���
			del.setData(rightMin.getData());
			// ����Ҫɾ���Ľڵ�Ϊ��������С�ڵ�
			del = rightMin;
			// ���ø��ڵ�
			delParent = rightMinParent;
		}
		
		// Ҫɾ���Ľڵ���һ��������ӽڵ�
		// Ҫɾ���ڵ���ӽڵ�
		TreeNode child = null;
		if(del.getLeft() != null) {
			child = del.getLeft();
		} else if(del.getRight() != null) {
			child = del.getRight();
		}
		
		// Ҫɾ�����Ǹ��ڵ�
		if(delParent == null) {
			root = child;
		} else if(delParent.getLeft() == del) {
			// Ҫɾ���Ľڵ��Ǹ��ڵ����ڵ�
			delParent.setLeft(child);
		} else {
			// Ҫɾ���Ľڵ㲻�Ǹ��ڵ����ڵ㣨�ҽڵ㣩
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

		System.out.println("\r\n=======����========");
		TreeNode node = tree.search(19);
		System.out.println(node != null ? node.getData() : null);

		System.out.println("=======����========");
		tree.insert(55);
		tree.midOrder(tree.getRoot());
		
		System.out.println("\r\n=======ɾ��========");
		tree.delete(50);
		tree.midOrder(tree.getRoot());
	}

}
