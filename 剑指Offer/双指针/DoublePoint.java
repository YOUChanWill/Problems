package 双指针;

import org.w3c.dom.ls.LSInput;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DoublePoint {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    /**剑指 Offer 18. 删除链表的节点

     给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

     返回删除后的链表的头节点。*/
    public ListNode deleteNode(ListNode head, int val) {
        ListNode pre = head, cur = head.next;
        if (pre.val == val){
            return cur;
        }
        while (cur != null){
            if (cur.val == val){
                pre.next = cur.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
    }

    public ListNode deleteNode01(ListNode head, int val) {
        ListNode cur = head;
        if (head.val == val) return head.next;
        while (cur.next != null && cur.next.val != val) cur = cur.next;
        if (cur.next != null){
            cur.next = cur.next.next;
        }
        return head;
    }



    /**剑指 Offer 22. 链表中倒数第k个节点

     输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

     例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。*/
    public ListNode getKthFromEnd(ListNode head, int k) {
        int count = 0;
        ListNode cur = head;
        while (cur.next != null){
            count++;
            cur = cur.next;
        }
        cur = head;
        for (int i = 0; i <= count - k; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public ListNode getKthFromEnd01(ListNode head, int k) {
        ListNode start = head, end = head;
        int count = 0;
        while (end != null){
            if (count >= k) start = start.next;
            end = end.next;
            count++;
        }
        return end;
    }


    /**剑指 Offer 25. 合并两个排序的链表

     输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。*/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), cur = dummy;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dummy.next;
    }


    public ListNode mergeTwoLists01(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null){
            return l1 != null ? l1 : l2;
        }
        if(l1.val <= l2.val){
            l1.next = mergeTwoLists01(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists01(l1, l2.next);
            return l2;
        }
    }


    /**剑指 Offer 52. 两个链表的第一个公共节点

     输入两个链表，找出它们的第一个公共节点。*/
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

    ListNode getIntersectionNode01(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        int lA = 0;
        int lB = 0;
        while (pA != null) {
            lA++;
            pA = pA.next;
        }
        while (pB != null) {
            lB++;
            pB = pB.next;
        }

        pA = headA;
        pB = headB;
        if (lA > lB) {
            for (int i = 0; i < (lA-lB); i++) {
                pA = pA.next;
            }
        }
        if (lB > lA) {
            for (int i = 0; i < (lB-lA); i++) {
                pB = pB.next;
            }
        }

        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }

        return pA;
    }


    /**剑指 Offer 21. 调整数组顺序使奇数位于偶数前面

     输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。*/
    public int[] exchange(int[] nums) {
        int start = 0, end = nums.length - 1, temp;
        while (start < end){
            while (start < end && ((nums[start] % 2) == 1)) start++;
            while (start < end && ((nums[end] % 2) == 0)) end--;
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        return nums;
    }


    public int[] exchange01(int[] nums) {
        int slow = 0, fast = 0, temp;
        while (fast < nums.length){
            if (nums[fast] % 2 == 1){
                temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
            fast++;
        }
        return nums;
    }


    /**剑指 Offer 57. 和为s的两个数字

     输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。*/
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ints = new int[2];
        for (int x :
                nums) {
            map.put(x, target - x);
            if (map.containsKey(target - x)) {
                ints[0] = target - x;
                ints[1] = map.get(target - x);
            }
        }
        return ints;
    }


    public int[] twoSum01(int[] nums, int target) {
        int strat = 0, end = nums.length - 1;
        while (strat < end){
            int sum = nums[strat] + nums[end];
            if (sum > target){
                end--;
            }else if (sum < target) {
                strat++;
            }else return new int[]{nums[strat],nums[end]};

        }
        return new int[0];
    }



    /**剑指 Offer 58 - I. 翻转单词顺序

     输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
     为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。*/
    public String reverseWords(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] str = s.trim().split("\\s+");
        for (int i = str.length - 1; i > 0; i--) {
            stringBuilder.append(str[i]).append(" ");
        }
        stringBuilder.append(str[0]);
        return stringBuilder.toString();
    }

    public String reverseWordss(String s) {
        s = s.trim();
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i > -1; i--) {
            sb.append(arr[i]);
            if (!"".equals(arr[i]) && i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public String reverseWords01(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public String reverseWords02(String s) {
        s = s.trim();
        int start = s.length() - 1, end = s.length() - 1;
        StringBuilder stringBuilder = new StringBuilder();
        while(start >= 0) {
            while(start >= 0 && s.charAt(start) != ' '){
                start--;
            }
            stringBuilder.append(s.substring(start + 1, end + 1)).append(" ");
            while(start >= 0 && s.charAt(start) == ' ') {
                start--;
            }
            end = start;
        }
        return stringBuilder.toString().trim();
    }



}
