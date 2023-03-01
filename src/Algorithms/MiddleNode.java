package Algorithms;

public class MiddleNode {

    public ListNode middleNode(ListNode head) {
        if (head == null){
            return null;
        }
        if (head.next == null){
            return head;
        }
        int count = 0;
        ListNode cur = head;
        while (cur != null){
            count++;
            cur = cur.next;
        }
        cur = head;
        for (int i = 0; i < count / 2; i++) {
            cur = cur.next;
        }
        return cur;

    }

    public class ListNode {
        int val;
        MiddleNode.ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, MiddleNode.ListNode next) { this.val = val; this.next = next; }
    }
}
