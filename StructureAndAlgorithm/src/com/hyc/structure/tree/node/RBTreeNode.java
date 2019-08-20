package com.hyc.structure.tree.node;

/**
 * ������ڵ�
 */
public class RBTreeNode<T extends Comparable<T>> {
	// ��
	public static final int RED = 0;
	// ��
	public static final int BLACK = 1;
	// ��-��
	public static final int RED_BLACK = 2;
	// ��-��
	public static final int BLACK_BLACK = 3;

	public int color;
	public T data;
	public RBTreeNode<T> left;
	public RBTreeNode<T> right;
	public RBTreeNode<T> parent;

	public RBTreeNode(T data) {
		this(data, RED, null, null, null);
	}
	
	public RBTreeNode(T data, int color) {
		this(data, color, null, null, null);
	}
	
	public RBTreeNode(T data, int color, RBTreeNode<T> parent) {
		this(data, color, parent, null, null);
	}

	public RBTreeNode(T data, int color, RBTreeNode<T> parent, RBTreeNode<T> left, RBTreeNode<T> right) {
		this.data = data;
		this.color = color;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
}