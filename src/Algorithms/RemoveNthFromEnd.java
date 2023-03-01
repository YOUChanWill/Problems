package Algorithms;

public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null){
            return null;
        }
        if (head.next == null && n == 1){
            return null;
        }
        int count = 0;
        ListNode cur = head; // 定义当前的链表结点为头指针
        while ( cur != null){
            count++;
            cur = cur.next; // 遍历获取链表的长度
        }
        cur = head;
        if (count > n){
            for (int i = 0; i < count - n - 1; i++) {
                cur = cur.next; // 找到要删除的结点
            }
            cur.next = cur.next.next; // 去除要删除的结点
            return head;
        }else {
            head = head.next;
            return head;
        }

    }




//Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }



}
