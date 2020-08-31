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
		int min = getMinNumberTree(bNode);
		System.out.println(min);

		printTree(bNode);
		System.out.println(isBalance(bNode));
	}

	public static BstNode getTree(Node node) {
		root = node;
		int hight = getHight(node);
		return build(0, hight - 1);
	}

	//获取树的总高度
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

	//二叉树构建  使用中序遍历的思想 + 分治
	//先构建左边 然后中间 最后右边
	//前提是 链表是有序的
	//时间复杂度 o(n) + log (n) 搜索的时间复杂度
	public static BstNode build(int left, int right) {
		if (left > right)
			return null;
		int mid = (left + right + 1) >> 1;
		BstNode bRoot = new BstNode();
		bRoot.left = build(left, mid - 1);
		bRoot.val = root.val;
		root = root.next;//第一个应该是最左边的节点 
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

	//判断一棵树是不是平衡的
	//思想是 : 分别找这个树每个的左树和右树的高度差 如果超过1 就返回一个状态  最后判断这个状态即可
	//时间复杂度是 o(n) 遍历一遍树就行
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

	//获取 二叉树，找出其最小深度。
	//最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
	//深度优先遍历
	public static int getMinNumberTree(BstNode root){
		int min = Integer.MAX_VALUE ;
		if(root == null)
			return 0 ;
		if(root.left == null  && root.right == null){
			return 1 ;
		}
		if(root.left != null){
			min = Math.min(getMinNumberTree(root.left),min);
		}
		if(root.right != null){

			min = Math.min(getMinNumberTree(root.right),min);

		}
		return min + 1 ;
	}


	//给定一个二叉树，它的每个结点都存放着一个整数值。
	//
	//找出路径和等于给定数值的路径总数。
	//
	//路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

	//思路 ： 从根节点往下遍历  使用sum-root.val 判断
	//比如 sum为8 当前节点为 5  那么下个节点只要等于3就行 如果想等 count+1 ，然后分辨遍历左边和右边
	public int getPath(BstNode root , int  sum ){
		if(root == null)
			return 0;
		return  paths(root,sum) + getPath(root.left,sum)+getPath(root.right,sum);
	}
	public int paths(BstNode root , int sum ){
		if(root == null)
			return 0 ;
		int res =  0 ;
		if(sum == root.val){
			res += 1 ;
		}
		res += paths(root.left , sum - root.val);
		res += paths(root.right,sum-root.val);

		return res ;
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