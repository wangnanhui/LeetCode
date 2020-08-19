package com.bst;

public class BstBuild {

	static Node root;

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Node node = new Node();
		node.val = arr[0];
		Node temp = node;
		for (int i = 1; i < arr.length; i++) {
			temp.next = new Node();
			temp.next.val = arr[i];
			temp = temp.next;
		}
		printNode(node);
		System.out.println();
		BstNode bNode = getTree(node);
		printTree(bNode);
		System.out.println(isBalance(bNode));
	}

	public static BstNode getTree(Node node) {
		root = node;
		int hight = getHight(node);
		return build(0, hight - 1);
	}

	public static int getHight(Node node) {

		if (node == null)
			return 0;
		int hight = 0;
		while (node != null) {
			++hight;
			node = node.next;
		}

		return hight;
	}

	public static BstNode build(int left, int right) {
		if (left > right)
			return null;
		int mid = (left + right + 1) >> 1;
		BstNode bRoot = new BstNode();
		bRoot.left = build(left, mid - 1);
		bRoot.val = root.val;
		root = root.next;
		bRoot.right = build(mid + 1, right);

		return bRoot;
	}

	public static void printNode(Node node) {
		while (node != null) {
			System.out.print(node.val + "->");
			node = node.next;
		}
	}

	public static void printTree(BstNode bNode) {
		if (bNode == null)
			return;
		System.out.print(bNode.val + "->");
		printTree(bNode.left);
		printTree(bNode.right);
	}

	public static boolean isBalance(BstNode bNode) {
		if (bNode == null)
			return true;
		return getHight(bNode) >= 0;
	}

	public static int getHight(BstNode bNode) {
		if (bNode == null)
			return 0;
		int lHight = getHight(bNode.left);
		int rHight = getHight(bNode.right);
		if (lHight == -1 || rHight == -1 || Math.abs(lHight - rHight) > 1)
			return -1;

		return Math.max(lHight, rHight) + 1;

	}

}

class BstNode {
	public int val;
	public BstNode left;
	public BstNode right;
}

class Node {
	public int val;
	public Node next;
}