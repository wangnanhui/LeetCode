package com.tire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TST {

	TSTNode rootNode = null;// new TSTNode();

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(
				new FileReader(new File("C:\\Users\\Administrator\\eclipse-workspace\\Test111\\resource\\dic.txt")));
		TST tst = new TST();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			tst.putData(line);

		}
		tst.putData("南极之恋");
		tst.getNode("做咗手腳南极之恋".toCharArray(), true);

	}

	public void putData(String str) {
		String[] arr = str.split("\\ ");
		char[] chs = arr[0].toCharArray();

		if (rootNode == null) {
			rootNode = createNewNode(chs, arr[2], Integer.parseInt(arr[1]));
		} else {
			// char[] cTemp = new char[chs.length - 1];
			// System.arraycopy(chs, 1, cTemp, 0, cTemp.length);

			putExist(chs, rootNode, arr[2], Integer.parseInt(arr[1]));
		}

	}

	public TSTNode getNode(char[] chs, boolean profix) {
		TSTNode temp = rootNode;
		TSTNode node = null;
		TSTNode nodeTemp = node;
		for (int i = 0; i < chs.length; i++) {
			if (temp == null)
				break;
			if (temp.c == chs[i]) {
				System.out.print(temp.c);
				if (node == null) {
					node = temp;
					nodeTemp = node;

				} else {
					nodeTemp.directNode = temp;
					if (!profix) {
						nodeTemp.left = null;
						nodeTemp.right = null;
					}
					nodeTemp = nodeTemp.directNode;

				}
				temp = temp.directNode;
			} else if (temp.c > chs[i]) {
				i--;
				temp = temp.left;
			} else {
				i--;
				temp = temp.right;
			}

		}

		if (!profix) {
			node.directNode = null;

		}
		node.left = null;
		node.right = null;
		return node;

	}

	public TSTNode createNewNode(char[] chs, String speech, int df) {
		TSTNode node = new TSTNode();
		char c = chs[0];
		node.c = c;
		TSTNode temp = node;
		for (int i = 1; i < chs.length; i++) {
			TSTNode newNode = new TSTNode();
			newNode.c = chs[i];
			temp.direct = true;
			temp.directNode = newNode;
			temp = temp.directNode;
		}
		temp.df = df;
		temp.partOfSpeech = speech;

		return node;

	}

	public TSTNode putExist(char[] chs, TSTNode root, String speench, int df) {
		TSTNode temp = root;
		for (int i = 0; i < chs.length; i++) {
			char c = chs[i];
			if (temp.c == c) {
				if (temp.directNode == null && i < (chs.length - 1)) {
					char[] cTemp = new char[chs.length - i];
					System.arraycopy(chs, i, cTemp, 0, cTemp.length);
					temp.directNode = createNewNode(cTemp, speench, df);
					break;
				} else {

					temp = temp.directNode;

				}

			} else if (temp.c > c) {
				if (temp.left != null) {
					i--;
					temp = temp.left;
				} else {
					char[] cTemp = new char[chs.length - i];
					System.arraycopy(chs, i, cTemp, 0, cTemp.length);
					temp.left = createNewNode(cTemp, speench, df);
					temp.direct = false;
					break;
				}

			} else {

				if (temp.right != null) {
					i--;
					temp = temp.right;

				} else {
					char[] cTemp = new char[chs.length - i];
					System.arraycopy(chs, i, cTemp, 0, cTemp.length);
					temp.right = createNewNode(cTemp, speench, df);
					temp.direct = false;
					break;
				}

			}

		}
		return root;

	}

}

class TSTNode {
	public char c;
	public boolean direct;
	public TSTNode directNode;
	public TSTNode left;
	public TSTNode right;
	public int df;
	public String partOfSpeech;

	@Override
	public String toString() {
		return "TSTNode [c=" + c + ", direct=" + direct + ", directNode=" + directNode + ", left=" + left + ", right="
				+ right + "]";
	}

}
