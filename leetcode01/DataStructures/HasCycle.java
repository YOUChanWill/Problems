package DataStructures;

import java.util.HashSet;

public class HasCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow  = head;
        ListNode fast  = head;
        while (fast != null){
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle01(ListNode head) {
        HashSet hashSet = new HashSet();
        while (head != null){
            if (!hashSet.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

