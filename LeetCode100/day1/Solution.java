package day1;

import java.util.Arrays;
import java.util.HashSet;

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

    /**给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。**/
    public int trap(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0;
        while (left < right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]){
                ans += leftMax - height[left];
                ++left;
            }else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }


    /**给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

     请你设计并实现时间复杂度为 O(n) 的算法解决此问题。**/
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x :
                nums) {
            set.add(x);
        }
        int max = 0;
        for (int x :
                set) {
            if (!set.contains(x - 1)) {
                int currenNum = x;
                int currenMax = 1;
                while (set.contains(currenNum + 1)) {
                    currenNum += 1;
                    currenMax += 1;
                }
                max = Math.max(max, currenMax);
            }
        }
        return max;
    }

    public int longestConsecutive01(int[] nums) {
        Arrays.sort(nums);
        int n=1,max=0;
        for(int i=0;i<nums.length;i++){
            if(i+1<nums.length && nums[i]+1==nums[i+1]){
                n++;
            }else if(i+1<nums.length && nums[i]==nums[i+1]){
                continue;
            }else{
                if(n>max)max=n;n=1;
            }
        }
        return max;
    }



}
