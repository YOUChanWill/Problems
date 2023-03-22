package ProgramLearn;

public class GetDecimalValue {

    public int getDecimalValue(ListNode head) {
        ListNode curNode = head;
        int ans = 0;
        while (curNode != null){
            ans = ans * 2 + curNode.val;
            curNode = curNode.next;
        }
        return ans;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
