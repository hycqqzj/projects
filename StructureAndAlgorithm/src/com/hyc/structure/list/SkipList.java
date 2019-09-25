package com.hyc.structure.list;

import java.util.Random;

/**
 * 跳表的一种实现方法。 跳表中存储的是正整数，并且存储的是不重复的。
 * 
#1. 结构
  5 head->2
  4 head->1->2
  3 head->1->2->4->5->6
  2 head->1->2->3->4->5->6
  1 head->1->2->3->4->5->6
  0 head->1->2->3->4->5->6
Node中的forward[]表示为 第i层 该Node节点的下一个节点 如 head的forward[0] 代表为第0层下一个节点 即1

#2. 插入函数理解
（1）生成随机层数函数 在这层以及下层都要插入这个值
（2）update[i] 函数表示第 i 层要更新的节点 ，每层都初始化指向head节点。
（3）找出每一层最后一个小于value的节点 记录最后一个小于value的节点。
（4）然后将value插入到这个节点后面。更新每层节点
 */
public class SkipList {
	// 索引层和底层相加最大数
	private static final int MAX_LEVEL = 5;
	
	private int levelCount = 1;
	// 带头链表
	private Node head = new Node();
	private Random random = new Random();

	public Node find(int value) {
		Node p = head;
		for (int i = levelCount - 1; i >= 0; --i) {
			while (p.forwards[i] != null && p.forwards[i].data < value) {
				p = p.forwards[i];
			}
		}

		if (p.forwards[0] != null && p.forwards[0].data == value) {
			return p.forwards[0];
		} else {
			return null;
		}
	}

	public void insert(int value) {
		int level = randomLevel();
		
		Node newNode = new Node();
		newNode.data = value;
		newNode.maxLevel = level;
		
		Node update[] = new Node[level];
		for (int i = 0; i < level; ++i) {
			update[i] = head;
		}

		// record every level largest value which smaller than insert value in update[]
		Node p = head;
		for (int i = level - 1; i >= 0; --i) {
			// 找到新节点插入的位置，即找到小于新值的最后一个节点
			while (p.forwards[i] != null && p.forwards[i].data < value) {
				p = p.forwards[i];
			}
			update[i] = p;// use update save node in search path
		}

		// in search path node next node become new node forwords(next)
		for (int i = 0; i < level; ++i) {
			// 通过指针操作加入新的节点，类似于单链表的插入操作
			// 新节点的next指针指向前一个节点的next指针
			newNode.forwards[i] = update[i].forwards[i];
			// 前一个节点的next指针指向新节点
			update[i].forwards[i] = newNode;
		}

		// 更新调表的深度
		if (levelCount < level) {
			levelCount = level;
		}
	}

	public void delete(int value) {
		Node[] update = new Node[levelCount];
		Node p = head;
		for (int i = levelCount - 1; i >= 0; --i) {
			while (p.forwards[i] != null && p.forwards[i].data < value) {
				p = p.forwards[i];
			}
			update[i] = p;
		}

		if (p.forwards[0] != null && p.forwards[0].data == value) {
			for (int i = levelCount - 1; i >= 0; --i) {
				if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
					update[i].forwards[i] = update[i].forwards[i].forwards[i];
				}
			}
		}
	}

	/**
	 * 随机 level 次，如果是奇数层数 +1，防止伪随机
	 * 
	 * @return
	 */
	private int randomLevel() {
		int level = 1;
		for (int i = 1; i < MAX_LEVEL; ++i) {
			if (random.nextInt() % 2 == 1) {
				level++;
			}
		}

		return level;
	}

	public void printAll() {
		Node p = head;
		while (p.forwards[0] != null) {
			System.out.print(p.forwards[0] + " ");
			p = p.forwards[0];
		}
		System.out.println();
	}

	public class Node {
		private int data = -1;
		// forward[]表示为 第i层中该Node节点的下一个节点，如 head的forward[0]代表为第0层下一个节点
		private Node[] forwards = new Node[MAX_LEVEL];
		private int maxLevel = 0;

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("{ data: ");
			builder.append(data);
			builder.append("; maxLevel: ");
			builder.append(maxLevel);
			builder.append(" }");

			return builder.toString();
		}
	}

	public static void main(String[] args) {
		SkipList list = new SkipList();
		for (int i = 1; i <= 10; i++) {
			list.insert(i);
		}
		
		list.printAll();
		
		System.out.println(list.find(6));
	}
}