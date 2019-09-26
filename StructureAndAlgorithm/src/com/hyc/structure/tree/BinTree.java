package com.hyc.structure.tree;

import java.util.LinkedList;
import java.util.Stack;

import com.hyc.structure.tree.node.TreeNode;

import lombok.Getter;
import lombok.Setter;

/**
 * ������
 */
public class BinTree {
	@Setter
	@Getter
	private TreeNode root;

	/**
	 * ǰ�������data-left-right
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
	 * ǰ�����-�ǵݹ����
	 * 
	 * 1.�Ƚ����ڵ���ջ 2.���ʸ��ڵ� 3.������ڵ�����Һ��ӣ����Һ�����ջ
	 * 4.������ڵ�������ӣ���������ջ��ע�⣺һ�����Һ�������ջ��Ȼ��������ջ�� 5.�ظ�2-4
	 */
	public void preOrder2(TreeNode root) {
		if (root == null) {
			return;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			System.out.print(node.getData() + ",");
			if (node.getRight() != null) {
				stack.add(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.add(node.getLeft());
			}
		}

		System.out.println();

	}

	/**
	 * ���������left-data-right
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
	 * �������-�ǵݹ����
	 * 
	 * 1.�Ƚ����ڵ���ջ 2.����ǰ�ڵ������������ջ��ֱ������Ϊ�� 3.����ջ��Ԫ�أ����ջ��Ԫ�ش����Һ��ӣ��������2��
	 * 4.�ظ���2��3����ֱ��ջΪ�ղ������еĽڵ㶼������
	 * 
	 * @param root
	 */
	public void midOrder2(TreeNode root) {
		if (root == null) {
			return;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode tmp = root;
		while (!stack.isEmpty() || tmp != null) {
			while (tmp != null) {
				stack.push(tmp);
				tmp = tmp.getLeft();
			}
			tmp = stack.pop();
			System.out.print(tmp.getData() + ",");
			tmp = tmp.getRight();
		}
		System.out.println();
	}

	/**
	 * ���������left-right-data
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
	 * �������-�ǵݹ����
	 * 
	 * 1.���ڵ���ջ 2.�����ڵ����������ջ��ֱ������û������Ϊֹ
	 * 3.�õ�ջ��Ԫ�ص�ֵ���Ȳ����ʣ��ж�ջ��Ԫ���Ƿ�����Һ��ӣ�������ڲ���û�б����ʣ����Һ�����ջ�����򣬾ͷ���ջ��Ԫ��
	 * 
	 * @param root
	 */
	public void postOrder2(TreeNode root) {
		if (root == null) {
			return;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);
		TreeNode tmp = root;
		TreeNode pre = null;

		while (tmp != null || !stack.isEmpty()) {
			while (tmp != null) {
				stack.add(tmp);
				tmp = tmp.getLeft();
			}

			tmp = stack.peek();
			if (tmp.getRight() == null || pre == tmp.getRight()) {
				tmp = stack.pop();
				System.out.println(tmp.getData() + ",");
				pre = tmp;
				tmp = null;
			} else {
				tmp = tmp.getRight();
			}
		}
	}

	/**
	 * �������
	 * 
	 * @param queue
	 */
	public void levelOrder(LinkedList<TreeNode> queue) {
		if (queue == null) {
			queue = new LinkedList<TreeNode>();
			if (root != null) {
				// ���ڵ����
				queue.add(root);
			}
		}
		// ȡ�����׽ڵ�
		TreeNode node = queue.poll();
		// ������û�����������
		if (node == null) {
			return;
		}
		// ���ʸýڵ�
		System.out.print(node.getData() + ",");

		// �������
		if (node.getLeft() != null) {
			queue.add(node.getLeft());
		}
		// �Һ������
		if (node.getRight() != null) {
			queue.add(node.getRight());
		}

		// �ݹ����
		levelOrder(queue);
	}

	/**
	 * �������ĸ߶� �ݹ鷨�����ĸ߶�=max(�������߶ȣ��������߶�)+1
	 * 
	 * @param rootNode
	 * @return
	 */
	public int getHeight(TreeNode rootNode) {
		if (rootNode == null || (rootNode.getLeft() == null && rootNode.getRight() == null)) {
			return 0;
		}

		int leftHeight = getHeight(rootNode.getLeft());
		int rightHeight = getHeight(rootNode.getRight());
		return Math.max(leftHeight, rightHeight) + 1;
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
		TreeNode node14 = new TreeNode(14);

		root.insertLeftAndRight(node2, node3);
		node2.insertLeftAndRight(node4, node5);
		node3.insertLeftAndRight(node6, node7);
		node4.insertLeftAndRight(node8, node9);
		node5.insertLeftAndRight(null, node10);
		node7.insertLeftAndRight(node11, null);
		node8.insertLeftAndRight(node12, null);
		node11.insertLeftAndRight(null, node13);
		node12.insertLeftAndRight(node14, null);

		BinTree tree = new BinTree();
		tree.setRoot(root);

		tree.preOrder(root);
		System.out.println("\r\n=================");
		tree.preOrder2(root);

		// tree.levelOrder(null);

		System.out.println("\r\n=================");
		System.out.println(tree.getHeight(tree.root));
	}

}
