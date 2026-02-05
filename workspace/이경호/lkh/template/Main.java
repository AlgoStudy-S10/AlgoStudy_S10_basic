package lkh.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Node root = null;
		while (true) {
			int cur;
			try {
				cur = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				break;
			}
			
			Node curNode = new Node(cur);
			if (root == null) {
				root = curNode;
				continue;
			}
			
			if (!addNode(root, curNode))
				return;
		}
		
		printPostOrder(root);
	}
	
	public static void printPostOrder(Node node) {
		if (node == null)
			return;
		
		if (!node.isLeftEmpty())
			printPostOrder(node.left);
		
		if (!node.isRightEmpty())
			printPostOrder(node.right);
		
		System.out.println(node.value);
	}
		
	// start = root 노드로 생각하고 add하기
	public static boolean addNode(Node start, Node node) {
		boolean done = false;
		while (!done) {
			if (start.value > node.value) {
				if (start.isLeftEmpty()) {
					start.setLeft(node);
					done = true;
				}
				else
					start = start.left;
			}
			else if (start.value < node.value) {
				if (start.isRightEmpty()) {
					start.setRight(node);
					done = true;
				}
				else
					start = start.right;
			}
			else
				return false;
		}
		
		return true;
	}
	
	static class Node {
		int value;
		Node left;
		Node right;
		
		Node() { }
		
		Node(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
		
		public boolean isLeftEmpty() {
			return this.left == null;
		}
		
		public boolean isRightEmpty() {
			return this.right == null;
		}
	}
}
