package com.linked;

import java.util.List;

public class Linked2 {

    public static void main(String[] args) {

        int[] arr = {1,1,5,4,6,7,8,9,3};
        Linked2 linked2 = new Linked2();
        ListNode2 node2 = linked2.creatNode(arr);

        linked2.partition(node2,6);
        ListNode2  deleteDuplicates = linked2.deleteDuplicates(node2);

        ListNode2 right = linked2.rotateRight(node2,2);


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
        return node.next;

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



    //向右移动K个长度
    //思想 ：假设链表长度N  向右移动就是把从N-K个位置后链表的放到头结点
    //首先 N-k的链表开始位置（假设为B） 以及他的前驱 （C）， 找到B链表的队尾然后让他和head相连，然后将C的后一位置为空即可
    public ListNode2 rotateRight(ListNode2 head, int k) {
        if (head == null)
            return null;
        ListNode2 temp = head;
        int n = 1;
        while (temp.next != null) {
            temp = temp.next;
            n++;
        }

        k = k % n;
        if (k == 0) {
            return head;
        }

        temp = head;
        ListNode2 temp1 = null;
        ListNode2 pre = null;
        k = n - k;

        while (true) {
            if (k > 0) {
                pre = temp;
                temp = temp.next;
                k--;
            } else {
                if (temp1 == null)
                    temp1 = temp;
                while (temp1.next != null) {
                    temp1 = temp1.next;
                }
                break;
            }


        }

        temp1.next = head;
        pre.next = null;


        return temp;

    }

    //去除链表中重复的节点
    //时间复杂度为 o(n) 空间复杂度为o(n)
    //思路 ： 创建一个临时链表用来记录最后需要的结构
    //如果当前节点和下一个节点相同 此时不存 循环找到当前节点和下一节点不同的节点 然后把他add进去
    //最后返回
    public ListNode2 deleteDuplicates(ListNode2 head) {
        if(head == null)
            return head ;
        ListNode2 pre = new ListNode2(0) ;
        ListNode2 temp = head ;
        ListNode2 tempPre = pre ;
        while(temp != null && temp.next != null ){
            if(temp.val == temp.next.val){
                while(temp.val == temp.next.val){
                    temp = temp.next ;
                    if(temp.next == null){
                        temp = null ;
                        break ;
                    }
                }
                if(temp == null|| temp.next == null)
                    break;
                temp = temp.next;
            }else {
                tempPre.next = new ListNode2(temp.val);
                tempPre = tempPre.next;
                temp = temp.next;
            }
        }

        if(temp !=null)
            tempPre.next = new ListNode2(temp.val);

        return pre.next ;
    }


    //给定一个值  大于此值的在右侧 小于此值的在左侧
    //思路 分成两个小链表 最后合并
    public ListNode2 partition(ListNode2 head, int x) {
        if(head == null)
            return head ;
        ListNode2 temp1 = null;
        ListNode2 temp2 = null ;
        ListNode2 temp3  = temp1 ;
        ListNode2 temp4  = temp2 ;
        ListNode2 temp = head ;
        while(temp != null){
            if(temp.val < x){
                temp3.next = temp ;
                temp3 = temp3.next ;

            }else{
                temp4.next = temp ;
                temp4 = temp4.next ;
            }
            temp= temp.next ;
        }
        temp4.next = null ;
        temp3.next = null ;
        temp3.next = temp2.next ;
        return temp1.next;



    }

}

class ListNode2 {
    public int val;
    public ListNode2 next;
    public ListNode2(int val){
        this.val = val;
    }
    public ListNode2(){

    }
}