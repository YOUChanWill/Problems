package LinkedList;

import java.util.HashSet;
import java.util.Set;

public class solution {
    class ListNode {

        int val;
        ListNode next;
        ListNode() {}
        ListNode(int x) {val = x;next = null;}
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    /**给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。**/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 首先遍历链表 headA，并将链表 headA 中的每个节点加入哈希集合中。
        // 然后遍历链表 headB，对于遍历到的每个节点，判断该节点是否在哈希集合中
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            // 如果当前节点在哈希集合中，则后面的节点都在哈希集合中，即从当前节点开始的所有节点都在两个链表的相交部分，
            // 因此在链表 headB 中遍历到的第一个在哈希集合中的节点就是两个链表相交的节点，返回该节点。
            if (visited.contains(temp)) {
                return temp;
            }
            // 如果当前节点不在哈希集合中，则继续遍历下一个节点；
            temp = temp.next;
        }
        return null;
    }

    public ListNode getIntersectionNode01(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


    /**给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。**/
    public boolean isPalindrome(ListNode head) {

    }



    /**给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

     如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。

     如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     不允许修改 链表。**/
    public ListNode detectCycle(ListNode head) {

    }


    /**给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

     请你将两个数相加，并以相同形式返回一个表示和的链表。

     你可以假设除了数字 0 之外，这两个数都不会以 0 开头。**/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    }



}
