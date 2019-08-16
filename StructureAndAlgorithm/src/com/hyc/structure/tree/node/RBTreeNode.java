package com.hyc.structure.tree.node;

/**
 * ºìºÚÊ÷½Úµã
 */
public class RBTreeNode<T extends Comparable<T>> {
	public static final boolean RED = false;
	public static final boolean BLACK = true;

	public boolean color;
	public T data;
	public RBTreeNode<T> left;
	public RBTreeNode<T> right;
	public RBTreeNode<T> parent;

	public RBTreeNode(T data) {
		this(data, RED, null, null, null);
	}
	
	public RBTreeNode(T data, boolean color) {
		this(data, color, null, null, null);
	}
	
	public RBTreeNode(T data, boolean color, RBTreeNode<T> parent) {
		this(data, color, parent, null, null);
	}

	public RBTreeNode(T data, boolean color, RBTreeNode<T> parent, RBTreeNode<T> left, RBTreeNode<T> right) {
		this.data = data;
		this.color = color;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
}
