package com.hyc.structure.tree;

import java.util.LinkedList;
import java.util.Stack;

import com.hyc.structure.tree.node.TreeNode;

import lombok.Getter;
import lombok.Setter;

/**
 * 二叉树
 */
public class BinTree {
	@Setter
	@Getter
	private TreeNode root;

	/**
	 * 前序遍历，data-left-right
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
	 * 前序遍历-非递归遍历
	 * 
	 * 1.先将根节点入栈 2.访问根节点 3.如果根节点存在右孩子，则将右孩子入栈
	 * 4.如果根节点存在左孩子，则将左孩子入栈（注意：一定是右孩子先入栈，然后左孩子入栈） 5.重复2-4
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
	 * 中序遍历，left-data-right
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
	 * 中序遍历-非递归遍历
	 * 
	 * 1.先将根节点入栈 2.将当前节点的所有左孩子入栈，直到左孩子为空 3.访问栈顶元素，如果栈顶元素存在右孩子，则继续第2步
	 * 4.重复第2、3步，直到栈为空并且所有的节点都被访问
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
	 * 后序遍历，left-right-data
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
	 * 后序遍历-非递归遍历
	 * 
	 * 1.根节点入栈 2.将根节点的左子树入栈，直到最左，没有左孩子为止
	 * 3.得到栈顶元素的值，先不访问，判断栈顶元素是否存在右孩子，如果存在并且没有被访问，则将右孩子入栈，否则，就访问栈顶元素
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
	 * 层序遍历
	 * 
	 * @param queue
	 */
	public void levelOrder(LinkedList<TreeNode> queue) {
		if (queue == null) {
			queue = new LinkedList<TreeNode>();
			if (root != null) {
				// 根节点入队
				queue.add(root);
			}
		}
		// 取出队首节点
		TreeNode node = queue.poll();
		// 队列里没有数据则结束
		if (node == null) {
			return;
		}
		// 访问该节点
		System.out.print(node.getData() + ",");

		// 左孩子入队
		if (node.getLeft() != null) {
			queue.add(node.getLeft());
		}
		// 右孩子入队
		if (node.getRight() != null) {
			queue.add(node.getRight());
		}

		// 递归遍历
		levelOrder(queue);
	}

	/**
	 * 计算树的高度 递归法：树的高度=max(左子树高度，右子树高度)+1
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
