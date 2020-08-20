package com.linked;

public class Linked {
    public static void main(String[] args) {
        Linked linked = new Linked();
        int arr2[] = {9, 9, 9, 9, 9};
        int arr[] = {1,2,3,4,5,6,7,8,9};
        ListNode n1 = new ListNode(arr[0]);
        ListNode n2 = new ListNode(arr2[0]);


        ListNode n1Temp = n1;
        for (int i = 1; i < arr.length; i++) {
            n1Temp.next = new ListNode(arr[i]);
            n1Temp = n1Temp.next;
        }
        ListNode n2Temp = n2;
        for (int i = 1; i < arr2.length; i++) {
            n2Temp.next = new ListNode(arr2[i]);
            n2Temp = n2Temp.next;
        }

        linked.printNode(n1);
        linked.removeNthFromEnd(n1, 5);
        ListNode node = linked.oddEvenList(n1);

        linked.printNode(node);

        linked.printNode(n1);
        linked.printNode(n2);

        ListNode n3 = linked.addTwoNumbers(n1, n2);
        linked.printNode(n3);
    }

    public void printNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode nodeTemp = node;
        node.val = 0;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        while (temp1 != null && temp2 != null) {
            int l1V = temp1.val;
            int l2V = temp2.val;
            int lSum = l1V + l2V + nodeTemp.val;
            if (lSum >= 10) {
                nodeTemp.val = (lSum - 10);
                nodeTemp.next = new ListNode(1);
            } else {
                nodeTemp.val = lSum;
                nodeTemp.next = new ListNode(0);
            }

            temp1 = temp1.next;
            temp2 = temp2.next;
            if (temp2 == null && temp1 == null) {
                if (nodeTemp.next.val == 0)
                    nodeTemp.next = null;
            } else
                nodeTemp = nodeTemp.next;
        }
        if (temp1 != null) {
            setList(temp1, nodeTemp);
        }

        if (temp2 != null) {
            setList(temp2, nodeTemp);
        }

        return node;
    }

    public void setList(ListNode temp2, ListNode nodeTemp1) {

        temp2.val += nodeTemp1.val;
        if (temp2.val >= 10) {
            nodeTemp1.val = (temp2.val - 10);
            if (temp2.next == null)
                nodeTemp1.next = new ListNode(1);
            else {
                temp2.next.val += 1;

                nodeTemp1.next = temp2.next;
                nodeTemp1 = nodeTemp1.next;
                while (nodeTemp1 != null) {
                    if (nodeTemp1.val >= 10) {
                        nodeTemp1.val -= 10;
                        if (nodeTemp1.next != null) {
                            nodeTemp1.next.val += 1;
                        } else {
                            nodeTemp1.next = new ListNode(1);
                        }

                    }
                    nodeTemp1 = nodeTemp1.next;
                }


            }


        } else {
            nodeTemp1.val = temp2.val;
            nodeTemp1.next = temp2.next;
        }

    }

    //被leetcode的解释带歪了  以为奇数数值放奇数位 偶数数值放偶数位
    //看了解答才知知道  只是奇数位置的数在左边 偶数位置树在右边
    //等于给两个链表分别存奇数和偶数的链表
    //然后最后一个奇数位置指向偶数链表就行
    public ListNode oddEvenList(ListNode head) {

        if (head == null)
            return null;
        ListNode odd = head,
                even = head.next,
                evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        printNode(head);


        return head;
    }

    //
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode h = new ListNode(0);
        h.next = head;
        if (head == null)
            return null;
        ListNode temp = h.next;
        ListNode pre = h;
        int i = 1;
        while (i < n && temp != null) {
            if (temp.next == null) {
                pre.next = null;
                break;
            }
            pre = temp;
            temp = temp.next;
            if (temp != null)
                i++;
            else
                break;

        }
        if (temp == null) {

            return h.next;

        } else {

            ListNode tempNext = temp.next;
            pre.next = tempNext;

        }
        printNode(h.next);
        return h.next;

    }

}

class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}