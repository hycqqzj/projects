package com.hyc.structure.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ������Ȩͼ
 */
public class Graph {
	// ����ĸ���
	private int size;
	// �ڽӱ�
	private LinkedList<Integer>[] adj;

	private boolean found;

	@SuppressWarnings("unchecked")
	public Graph(int size) {
		this.size = size;
		this.adj = new LinkedList[size];
		for (int i = 0; i < this.adj.length; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	public void addEdge(int s, int t) {
		adj[s].add(t);
		adj[t].add(s);
	}

	/**
	 * ������ȱ���
	 * 
	 * @param s ��ʼ����
	 * @param t ��������
	 */
	public void bfs(int s, int t) {
		if (s == t) {
			return;
		}
		// �Ѿ������ʵĶ��㣬�������ⶥ�㱻�ظ�����
		boolean[] visited = new boolean[size];
		visited[s] = true;
		// �Ѿ������ʡ��������Ķ��㻹û�б����ʵĶ���
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		// ��¼����·��(��·��)
		int[] prev = new int[size];
		for (int i = 0; i < size; i++) {
			prev[i] = -1;
		}

		while (!queue.isEmpty()) {
			int w = queue.poll();
			for (int i = 0; i < adj[w].size(); i++) {
				int q = adj[w].get(i);
				if (!visited[q]) {
					prev[q] = w;
					if (t == q) {
						printPath(prev, s, t);
						return;
					}
					visited[q] = true;
					queue.add(q);
				}
			}
		}
	}

	public void dfs(int s, int t) {
		found = false;
		// �Ѿ������ʵĶ��㣬�������ⶥ�㱻�ظ�����
		boolean[] visited = new boolean[size];
		visited[s] = true;
		// ��¼����·��(��·��)
		int[] prev = new int[size];
		for (int i = 0; i < size; i++) {
			prev[i] = -1;
		}

		recurDfs(s, t, visited, prev);
		printPath(prev, s, t);
	}

	private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
		if (found) {
			return;
		}
		visited[w] = true;
		if (w == t) {
			found = true;
			return;
		}

		for (int i = 0; i < adj[w].size(); i++) {
			int q = adj[w].get(i);
			if (!visited[q]) {
				prev[q] = w;
				recurDfs(q, t, visited, prev);
			}
		}
	}

	private void printPath(int[] prev, int s, int t) {
		if (prev == null || prev.length == 0) {
			return;
		}

		if (prev[t] != -1 && s != t) {
			printPath(prev, s, prev[t]);
		}
		System.out.print(t + ",");
	}

	public static void main(String[] args) {
		Graph graph = new Graph(8);
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(1, 4);
		graph.addEdge(2, 5);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(4, 6);
		graph.addEdge(5, 7);
		graph.addEdge(6, 7);
		graph.bfs(0, 7);
		System.out.println();
		graph.dfs(0, 7);
	}

}
