package com.hyc.structure.list;

import java.util.Random;

/**
 * �����һ��ʵ�ַ����� �����д洢���������������Ҵ洢���ǲ��ظ��ġ�
 * 
#1. �ṹ
  5 head->2
  4 head->1->2
  3 head->1->2->4->5->6
  2 head->1->2->3->4->5->6
  1 head->1->2->3->4->5->6
  0 head->1->2->3->4->5->6
Node�е�forward[]��ʾΪ ��i�� ��Node�ڵ����һ���ڵ� �� head��forward[0] ����Ϊ��0����һ���ڵ� ��1

#2. ���뺯�����
��1����������������� ������Լ��²㶼Ҫ�������ֵ
��2��update[i] ������ʾ�� i ��Ҫ���µĽڵ� ��ÿ�㶼��ʼ��ָ��head�ڵ㡣
��3���ҳ�ÿһ�����һ��С��value�Ľڵ� ��¼���һ��С��value�Ľڵ㡣
��4��Ȼ��value���뵽����ڵ���档����ÿ��ڵ�
 */
public class SkipList {
	// ������͵ײ���������
	private static final int MAX_LEVEL = 5;
	
	private int levelCount = 1;
	// ��ͷ����
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
			// �ҵ��½ڵ�����λ�ã����ҵ�С����ֵ�����һ���ڵ�
			while (p.forwards[i] != null && p.forwards[i].data < value) {
				p = p.forwards[i];
			}
			update[i] = p;// use update save node in search path
		}

		// in search path node next node become new node forwords(next)
		for (int i = 0; i < level; ++i) {
			// ͨ��ָ����������µĽڵ㣬�����ڵ�����Ĳ������
			// �½ڵ��nextָ��ָ��ǰһ���ڵ��nextָ��
			newNode.forwards[i] = update[i].forwards[i];
			// ǰһ���ڵ��nextָ��ָ���½ڵ�
			update[i].forwards[i] = newNode;
		}

		// ���µ�������
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
	 * ��� level �Σ�������������� +1����ֹα���
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
		// forward[]��ʾΪ ��i���и�Node�ڵ����һ���ڵ㣬�� head��forward[0]����Ϊ��0����һ���ڵ�
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