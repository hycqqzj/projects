package com.hyc.structure.tree.node;

/**
 * AVLÊ÷½Úµã
 */
public class AVLTreeNode<T extends Comparable<T>> {
	public T data;
	public int height;
	public AVLTreeNode<T> left;
	public AVLTreeNode<T> right;
	
	public AVLTreeNode(T data) {
		this(data, null, null);
	}
	
	public AVLTreeNode(T data, AVLTreeNode<T> left, AVLTreeNode<T> right) {
		this(data, left, right, 0);
	}
	
	public AVLTreeNode(T data, AVLTreeNode<T> left, AVLTreeNode<T> right, int height) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.height = height;
	}
}
