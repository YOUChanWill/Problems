package DataStructures;

import java.util.HashSet;

public class DeleteDuplicates {
        public ListNode deleteDuplicates(ListNode head) {
            HashSet hashSet = new HashSet();
            ListNode cur = head;
            while (cur != null){
                if (!hashSet.add(cur.val)) {
                    cur = cur.next;
                }
                cur = cur.next;
            }
            return cur;
        }

    public ListNode deleteDuplicates01(ListNode head) {
        if(head == null ||head.next == null){
            return head;
        }
        head.next  = deleteDuplicates(head.next);//递归
        if(head.val == head.next.val){
            head=head.next;
        }
        return head;
    }


}
