package DataStructures;

public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseList01(ListNode head) {
        if(head==null){
            return null;
        } else{
            ListNode pre = head;
            ListNode last = reverseList(pre.next);
            if(last==null)return pre;else{
                ListNode l=last;
                while(l.next!=null){
                    l=l.next;
                }
                pre.next=null;
                l.next=pre;
                return last;
            }
        }
    }
}
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}