package 链表;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LinkedList {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    /**剑指 Offer 06. 从尾到头打印链表

     输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。**/
    public int[] reversePrint(ListNode head) {
        //先获取链表长度，创建对应长度数组
        ListNode currNode = head;
        int len = 0;
        while(currNode != null){
            len ++;
            currNode = currNode.next;
        }
        int[] result = new int[len];

        //再次遍历链表，将值倒序填充至结果数组
        currNode = head;
        while(currNode != null){
            result[len - 1] = currNode.val;
            len --;
            currNode = currNode.next;
        }
        return result;
    }

    public int[] reversePrint01(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curNode = head;
        while (curNode != null){
            stack.push(curNode);
            curNode = curNode.next;
        }
        int n = stack.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = stack.pop().val;
        }
        return ans;
    }


    /**剑指 Offer 24. 反转链表

     定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。**/
    public ListNode reverseList(ListNode head) {

    }



    /**剑指 Offer 35. 复杂链表的复制

     请实现 copyRandomList 函数，复制一个复杂链表。
     在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。**/
    public Node copyRandomList(Node head) {

    }




}
