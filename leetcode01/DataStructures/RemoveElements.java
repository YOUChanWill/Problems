package DataStructures;

import java.util.HashSet;

public class RemoveElements {

    public ListNode removeElements(ListNode head, int val) {
        HashSet hashSet = new HashSet();
        ListNode dummy = new ListNode();
        dummy.next = head;
        hashSet.add(val);
        ListNode now = head ,pre = dummy;
        while (now != null ){
            if (!hashSet.add(now.val)){
                pre.next = now.next;
                now.next = null;
                now = pre.next;
            }else {
                now = now.next;
                pre = pre.next;
                hashSet.clear();
                hashSet.add(val);
            }
        }
        return dummy.next;
    }


    public ListNode removeElements01(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode now = head, pre = dummy;
        while (now != null) {
            if (now.val != val) {
                now = now.next;
                pre = pre.next;
            } else {
                pre.next = now.next;
                now.next = null;
                now = pre.next;
            }
        }
        return dummy.next;
    }

public class ListNode {
int val;
 ListNode next;
ListNode() {}
ListNode(int val) { this.val = val; }
ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

}
