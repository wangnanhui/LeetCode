package com.linked;

import java.util.List;

public class Linked2 {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        Linked2 linked2 = new Linked2();
        ListNode2 node2 = linked2.creatNode(arr);
        linked2.rotateRight(node2,2);




        ListNode2 temp = node2;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node2.next.next;

        ListNode2 detectCycle = linked2.detectCycle(node2);
        System.out.println(detectCycle);


    }


    public ListNode2 creatNode(int[] arr) {

        ListNode2 node = new ListNode2();
        ListNode2 temp = node;
        for (int i = 0; i < arr.length; i++) {
            temp.next = new ListNode2();
            temp.next.val = arr[i];
            temp = temp.next;


        }
        return node;

    }


    //快慢指追及问题
    //解题思路 定义快慢指针
    //fast 指针每次走两步  slow 指针每次走一步
    //如果两者相等  则有环
    //找出环的入口   假设 头结点为  A  环入口在 B 相遇 在 C
    //fast 走的路程为 slow的 2倍
    //假设 A到B为 x  b到c为 y
    //那么 slow的路程 = X + Y
    //fast走的路程为 2*slow = 2（X+Y）= X + Y + NR(R为环的长度)
    //求解后为 NR = X + Y
    //Y  = NR - X ;  也就是说此时从head 开始走会在B点再次相遇
    //因为  NR表示走了N圈的长度 NR-X 表示的是 在B点相遇走的长度
    //


    public ListNode2 detectCycle(ListNode2 head) {
        if (head == null || head.next == null)
            return null;
        ListNode2 fast = head;
        ListNode2 slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                break;
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    //判断两链表是否相交
    //思想找出长的链表 先走到和短链表一样的长度 然后对比剩下的所有节点是否相等
    //不是对比值  是对比内存地址 用temp记录开始相等的节点 然后依次判断下一个
    //如果不想等 把temp赋值为空 最后返回这个node
    public ListNode2 getIntersectionNode(ListNode2 headA, ListNode2 headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode2 fast = headA;
        ListNode2 slow = headB;
        int k = 0;
        while (fast != null && slow != null) {
            fast = fast.next;
            slow = slow.next;
        }
        if (fast != null) {
            while (fast != null) {
                fast = fast.next;
                k++;
            }
            fast = headA;
            slow = headB;
        } else {
            while (slow != null) {
                slow = slow.next;
                k++;
            }
            fast = headB;
            slow = headA;
        }
        ListNode2 node = null;
        while (fast != null && slow != null) {
            if (k > 0) {
                fast = fast.next;
                k--;
            } else {
                if (fast == slow) {
                    if (node == null) {
                        node = fast;
                    }
                    fast = fast.next;
                    slow = slow.next;

                } else {
                    if (node != null) {
                        node = null;
                    }
                    fast = fast.next;
                    slow = slow.next;


                }
            }


        }


        return node;
    }

    //向右移动 k位  未完成 待续
    public ListNode2 rotateRight(ListNode2 node, int k) {
        if(node == null)
            return null ;
        ListNode2 temp = node ;
        ListNode2 pre = null ;
        while(temp.next != null){
            pre = temp ;
            temp= temp.next ;
        }
        ListNode2 tail = temp ;
        ListNode2 head = node ;
        while(k > 0){//未完
            pre.next = null ;
            tail.next = head;
            tail = pre ;




            k -- ;
        }



        return tail;

    }//0 1 2 3 4 5    5  4 0 1  2 3  4



}

class ListNode2 {
    public int val;
    public ListNode2 next;

}