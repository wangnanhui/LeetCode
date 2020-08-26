package com.linked;

import java.time.temporal.Temporal;
import java.util.List;

public class Linked {
    public static void main(String[] args) {
        Linked linked = new Linked();
        int arr2[] = {9, 9, 9, 9, 9};
        int arr[] = {1,2,3,4,5,4,3,2,9,9,9,9,9};

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

        ListNode postFix = linked.getSamePostfix(n1,n2);
        System.out.println(postFix == null);
        linked.printNode(postFix);





        linked.isPalindrome(n1);


        System.out.println("交换-------");
        ListNode swap = linked.swapPairs(n1);
        linked.printNode(swap);
        System.out.println("反转-------");



       ListNode reverseNode =  linked.reverseKNode(swap,3);

       linked.printNode(reverseNode);

        System.out.println("------");


        ListNode reverseBetween = linked.reverseBetween(reverseNode , 2,6) ;
        linked.printNode(reverseBetween);
        System.out.println();

        ListNode  mergeNode = linked.mergeTwoLists(n1,n2);

        linked.printNode(mergeNode);

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
    //更简单的办法就是  设置一个临时变量 i 初始为0   链表每next一次 加一 判断i的奇偶性 然后放到奇偶链表立 最后合并
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

    //移除正数第n个节点
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
    //合并两个有序链表
    //定一个新的链表用来存放最后结果
    //判断l1 和 l2 当前节点大小   哪个小放在前面 然后小的指针向下移动
    //最后把非空的l1 或者 l2 放在tempNode的后面
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null )
            return l2;
        if(l2 == null)
            return l1 ;


        ListNode  ll = new ListNode(0);
        ListNode llTemp = ll ;
        while(true){
            if(l1 == null || l2  == null)
                break;
            if(l1.val >= l2.val){
                llTemp.next = l2 ;
                l2 = l2.next  ;
            }else{
                llTemp.next = l1;
                l1  = l1.next;
            }
            llTemp = llTemp.next ;

        }

        if(l1 !=  null)
            llTemp.next = l1 ;
        if(l2 !=null)
            llTemp.next = l2 ;
        return ll.next;



    }
    //两两相交换
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head ;
        ListNode swapTemp = new ListNode(0);
        ListNode swap = swapTemp;

        ListNode temp = head ;
        ListNode nextTemp = head.next ;
        while(temp !=  null && nextTemp != null){
            swap.next = new ListNode(nextTemp.val) ;
            swap = swap.next;
            swap.next  = new ListNode(temp.val) ;
            swap = swap.next ;

            nextTemp = nextTemp.next ;
            temp = nextTemp ;
            if(nextTemp ==null)
                break ;
            nextTemp = temp.next ;
        }
        if(temp != null){
            swap.next = temp ;
        }
        return swapTemp.next ;
    }

    //k组 链表反转
    //思想是 把链表分成 size / k 组  反转每一组  最后拼接一下
    //
    public ListNode reverseKNode(ListNode list , int k ){
        if(list == null || k == 0 ||  k == 1)
            return list ;


        ListNode hTemp = list ;
        int length = 0 ;

        while(hTemp != null){
            length ++;
            hTemp = hTemp.next ;
        }

        int count = length  / k ;

        ListNode [] lists = new ListNode[count];
        int s = k ;


        ListNode pre = null ;
        ListNode curr = list ;
        while(curr != null ){
            ListNode temp = curr.next ;
            curr.next = pre ;
            pre = curr ;
            curr = temp ;
            k -- ;
            if(k== 0 ){
                lists[count - 1] = pre ;
                count --;
                pre = null ;
                k = s ;
                if(count == 0)
                    break;
            }
        }
        ListNode node = null ;
        ListNode temp = null ;
        for (int i = lists.length - 1; i >= 0 ; i--) {
            if(node == null){
                node = lists[i] ;
                temp = node ;
                while(temp.next != null)
                    temp = temp.next ;
            }else{
                temp.next = lists[i] ;
                while(temp.next != null)
                    temp = temp.next ;
            }


        }
        if(curr != null)
            temp.next = curr  ;
        return  node;


    }



    //单链表 反转
    //思想是 head - > A -> B -> C -> D -> E
    //第一次

    //  current = A -> B -> C -> D -> E
    //  tmp = B -> C -> D  -> E
    //  pre = head

    //第二次
    //  pre = A -> head
    // current = B -> C -> D ->  E
    // temp = C -> D -> E

    //第三次

    //pre  = B -> A -> head
    //current = C -> D ->  E
    //tmp = D -> E

    //第四次

    //pre = C -> B -> A ->head
    //current = D  -> E
    //tmp = E

    //第五次

    //pre = D -> C -> B -> A -> head
    //current = E ;
    //tmp  = null ;


    //第六次
    //pre = E  -> D -> B ->A ->head
    //current = null 跳出

    // 每次让当前节点的下一个节点指向当前的节点的前一个节点（或者说链表 ）
    // 然后 当前节点和前一个节点往后移动
    public ListNode reverseListNode(ListNode head ){
        if(head  == null)
            return  head ;

        ListNode pre  = null ;
        ListNode current = head ;
        while(current != null){
            ListNode tmp = current.next ;
            current.next  = pre ;
            pre  = current ;
            current  = tmp ;
        }
        return pre ;
    }

    //判断是否为回文链表
    //思想 定义 快慢指针 然后反转一半链表  然后和另一半链表比较
   public boolean  isPalindrome(ListNode head ){

        ListNode fast = head ;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next ;
        }

        ListNode pre = reverseListNode(slow) ;

       while(pre != null && head != null ){
           if( pre.val != head.val){
               break;
           }
           pre = pre.next ;
           head = head.next;
       }
       return pre == null  ;

   }



    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null )
            return head ;
        int start = 1 ;
        ListNode pre = null ;
        ListNode current  = head ;
        ListNode rtemp = head ;
        while(current != null){
            if(start >= m && start <= n ){
                ListNode temp = current.next ;
                current.next = pre ;
                pre = current ;
                current = temp ;
                start ++ ;
                if(current == null)
                    break ;
            }else{
                if(start >= m)
                    break ;
                // pre = current ;
                rtemp = rtemp.next ;
                current = current.next ;

                start ++ ;
            }

        }
        ListNode pTemp = pre ;
        if(current != null){
            while(pTemp.next != null)
                pTemp = pTemp.next ;
            pTemp.next = current ;
        }


        rtemp.next = pre ;
        return head ;


    }



    public ListNode getSamePostfix(ListNode n1 , ListNode n2 ){
        ListNode pre1 = reverseListNode(n1);
        ListNode pre2 = reverseListNode(n2) ;
        ListNode node = new ListNode(0);
        ListNode temp = node ;
        while(pre1 != null && pre2 != null){
            if(pre1.val != pre2.val){
                break;
            }else{
                temp.next = new ListNode(pre1.val);
                temp = temp.next;
                pre1 = pre1.next ;
                pre2 = pre2.next ;
            }
        }
        return node.next;

    }








}

class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}