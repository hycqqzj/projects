package com.hyc.list;

import lombok.Data;
import lombok.NoArgsConstructor;

public class IntLinkedList {
	@Data
	@NoArgsConstructor
	class Node {
		// 数据
		private int data;
		// 后继节点
		private Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	int size = 0;
	private Node head;

	public IntLinkedList() {
		head = new Node();
	}

	public int get(int index) {
		rangeCheck(index);
		return locateNode(index).data;
	}

	public int indexOf(int val) {
		int pos = -1;

		Node node = head;
		while (node == head || node.data != val) {
			node = node.next;
			pos++;
		}

		return pos;
	}

	public Boolean add(int val) {
		Node node = new Node(val);
		Node oldLast = findLast(this.head);
		oldLast.next = node;
		size++;

		return true;
	}

	public Boolean add(int index, int val) {
		Node pre = locateNode(index - 1);

		Node node = new Node(val);
		node.next = pre.next;
		pre.next = node;
		size++;

		return true;
	}

	public Boolean reverseAdd(int val) {
		Node node = new Node(val);
		node.next = head.next;
		head.next = node;
		size++;
		return true;
	}

	public int remove(int index) {
		Node pre = locateNode(index - 1);
		Node cur = pre.next;
		pre.next = cur.next;
		size--;

		return cur.data;
	}

	public Boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return this.size;
	}

	public void reverse() {
		IntLinkedList rList = new IntLinkedList();

		Node node = head;
		while (node.next != null) {
			node = node.next;
			rList.reverseAdd(node.data);
		}

		head = rList.head;
	}

	/**
	 * 排序
	 */
	public void sort() {
		Node node = head.next;
		while (node.next != null) {
			Node minNode = node;
			Node curNode = node.next;
			while (curNode != null) {
				if (minNode.data > curNode.data) {
					int temp = curNode.data;
					curNode.data = minNode.data;
					minNode.data = temp;
				}
				curNode = curNode.next;
			}

			node = node.next;
		}
	}

	public void sortAndMerge(IntLinkedList list) {
		IntLinkedList listNew = new IntLinkedList();

		list.sort();
		this.sort();

		Node node1 = head.next;
		Node node2 = list.head.next;

		while (node1 != null && node2 != null) {
			if (node1.data < node2.data) {
				listNew.add(node1.data);
				node1 = node1.next;
			} else {
				listNew.add(node2.data);
				node2 = node2.next;
			}
		}

		while (node1 != null) {
			listNew.add(node1.data);
			node1 = node1.next;
		}
		while (node2 != null) {
			listNew.add(node2.data);
			node2 = node2.next;
		}

		this.size = this.size + list.size;
		this.head = listNew.head;
	}

	/**
	 * 找出中间节点
	 * 
	 * @return
	 */
	public Node findMiddle() {
		// 快慢指针法，快指针每次走两步，慢指针每次走一步
		// 当快指针走到尾部，此时慢指针处于中间节点处
		Node slow = head.next;
		Node fast = head.next;

		while (fast != null) {
			if (fast.next != null && fast.next.next != null) {
				slow = slow.next;
				fast = fast.next.next;
			} else {
				fast = fast.next;
			}
		}

		return slow;
	}

	/**
	 * 检测链表是否成环
	 * 
	 * @return
	 */
	public Boolean isCycle() {
		boolean cycle = false;

		// 快慢指针法，快指针每次走两步，慢指针每次走一步
		// 当快指针和慢指针重合则成环
		Node slow = head.next;
		Node fast = head.next;
		
		while (fast != null && fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(fast == slow) {
				cycle = true;
				break;
			}
		}

		return cycle;
	}

	private void rangeCheck(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("链表越界");
		}
	}

	/**
	 * 获取第index个节点，从0计数
	 * 
	 * @param index
	 * @return
	 */
	private Node locateNode(int index) {
		Node node = head;

		for (int i = 0; i <= index; i++) {
			node = node.next;
		}

		return node;
	}

	/**
	 * 获取链表尾节点
	 * 
	 * @return
	 */
	private Node findLast(Node head) {
		Node node = head;

		while (node.next != null) {
			node = node.next;
		}

		return node;
	}

	public String show() {
		StringBuilder strBd = new StringBuilder();
		Node node = this.head;
		for (int i = 0; i < this.getSize(); i++) {
			node = node.next;
			strBd.append(node.data + "-");
		}
		strBd.deleteCharAt(strBd.length() - 1);
		return strBd.toString();
	}

	public static void main(String[] args) {
		IntLinkedList list1 = new IntLinkedList();
		list1.add(1);
		list1.add(4);
		list1.add(8);
		list1.add(10);
		list1.add(12);

//		System.out.println(list.get(2));
//		System.out.println(list.indexOf(3));
//		int val = list.remove(2);
//		System.out.println(val);
//

		System.out.println("========reverse========");
		list1.reverse();
		System.out.println(list1.show());

		System.out.println("========sort========");
		list1.sort();
		System.out.println(list1.show());

		System.out.println("========sortAndMerge========");
		IntLinkedList list2 = new IntLinkedList();
		list2.add(2);
		list2.add(3);
		list2.add(5);
		list2.add(11);
		list2.add(15);
		list2.add(19);
		list2.add(20);
		list2.add(29);

		list1.sortAndMerge(list2);
		System.out.println(list1.show());

		System.out.println("========findMiddle========");
		IntLinkedList list3 = new IntLinkedList();
		list3.add(1);
		list3.add(2);
		list3.add(3);
		list3.add(4);
		Node mid = list3.findMiddle();
		System.out.println("mid:" + mid.data);
		
		System.out.println("========isCycle========");
		IntLinkedList list4 = new IntLinkedList();
		list4.add(1);
		list4.add(2);
		list4.add(3);
		list4.add(4);
		list4.add(5);
		list4.add(6);
		System.out.println(list4.isCycle());
		
		// 修改指针使其成环
		Node tail = list4.locateNode(5);
		Node pre = list4.locateNode(2);
		//System.out.println(tail.data + "," + pre.data);
		tail.next = pre;
		System.out.println(list4.isCycle());
	}

}
