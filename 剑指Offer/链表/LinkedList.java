package 链表;

import java.util.*;

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
        ListNode currNode = head;
        int count = 0;
        while(currNode != null){
            count++;
            currNode = currNode.next;
        }
        int[] ans = new int[count];
        currNode = head;
        while(currNode != null){
            ans[count - 1] = currNode.val;
            count--;
            currNode = currNode.next;
        }
        return ans;
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
        ListNode curNode = head; // 头节点
        ListNode preNode = null; // 尾
        while (curNode != null){
            ListNode tempNode = curNode.next; // 暂存头节点的下一个节点
            curNode.next = preNode; // 头节点指向尾
            preNode = curNode; // 尾部为原先的头节点
            curNode = tempNode; // 新的头节点
        }
        return preNode;
    }

    // 使用递归
    public ListNode reverseList01(ListNode head) {
        return recur(head, null);    // 调用递归并返回
    }
    private ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null) return pre; // 终止条件
        ListNode res = recur(cur.next, cur);  // 递归后继节点
        cur.next = pre;              // 修改节点引用指向
        return res;                  // 返回反转链表的头节点
    }


    public ListNode reverseList02(ListNode head) {
        ListNode curNode = head;
        Stack<ListNode> stack = new Stack<>();
        while (curNode != null){
            stack.push(curNode);
            curNode = curNode.next;
        }
        if (stack.isEmpty()) return null;
        ListNode ansNode = stack.pop();
        ListNode dummy = ansNode;
        while (!stack.isEmpty()){
            ansNode.next = stack.pop();
            ansNode = ansNode.next;
        }
        ansNode.next = null;
        return dummy;
    }

    /**剑指 Offer 35. 复杂链表的复制

     请实现 copyRandomList 函数，复制一个复杂链表。
     在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。**/
    public Node copyRandomList(Node head) {
        HashMap<Node,Node> map = new HashMap<>(); //创建HashMap集合
        Node cur = head;
        //复制结点值
        while(cur != null){
            map.put(cur,new Node(cur.val)); //顺序遍历，存储老结点和新结点(先存储新创建的结点值)
            cur = cur.next;
        }
        //复制结点指向
        cur = head;
        while(cur != null){
            map.get(cur).next = map.get(cur.next); //新结点next指向同旧结点的next指向
            map.get(cur).random = map.get(cur.random); //新结点random指向同旧结点的random指向
            cur = cur.next;
        }
        //返回复制的链表
        return map.get(head);
    }

    public Node copyRandomList01(Node head) {
        if(head == null) return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表
        while(cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while(cur != null) {
            if(cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while(cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点,还原原链表
        return res;      // 返回新链表头节点
    }

}
