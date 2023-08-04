package day1;

public class Solution {


    /**给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
     假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
     你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。**/
    // Floyd 判圈算法
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        // 慢指针每次走一步，快指针每次走两步
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // 两个指针在有环的情况下一定会相遇，此时再将 slow 放置起点 0
        slow = 0;
        // 两个指针每次同时移动一步，相遇的点就是答案
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


}
