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

    public int getDecimalValue01(ListNode head) {
        int res = 0;
        while (head != null) {
            res <<= 1; // 向左移位后，最低位一定为0，所有可以用 或运算 和 最低位 做 加法，因为不存在进位的情况下，或运算可以用作二进制的加法
            res |= head.val; // res = res | head.val
            head = head.next;
        }
        return res;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
