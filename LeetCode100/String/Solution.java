package String;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class Solution {


    /**给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。**/
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)){
                count += map.get(pre - k);
            }
            map.put(pre,map.getOrDefault(pre,0) + 1);
        }
        return count;
    }



    /**给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。

     * 滑动窗口每次只向右移动一位。返回 滑动窗口中的最大值 。**/
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    public int[] maxSlidingWindow01(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int maxValue = Integer.MIN_VALUE;
        int left = 0;
        int idx = -1;
        for (int right = k - 1; right < n; right++, left++) {
            if (left <= idx) {
                if (nums[right] >= nums[idx]) {
                    idx = right;
                    maxValue = nums[right];
                }
            } else if (nums[right] >= maxValue - 1) {
                idx = right;
                maxValue = nums[right];
            } else if (nums[left] >= maxValue - 1) {
                idx = left;
                maxValue = nums[left];
            } else {
                maxValue = nums[left];
                for (int i = left + 1; i <= right; i++) {
                    if (nums[i] >= maxValue) {
                        idx = i;
                        maxValue = nums[i];
                    }
                }
            }
            ans[left] = maxValue;
        }
        return ans;
    }


    public int[] maxSlidingWindow02(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) return new int[0];
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        for(int i = 0; i < nums.length; i++){
            if(!dq.isEmpty() && dq.peek() == i - k) {
                dq.poll();
            }

            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                dq.pollLast();
            }

            dq.offer(i);
            if(i - k + 1 >= 0) result[i - k + 1] = nums[dq.peek()];
        }
        return result;
    }


    /**给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
     * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。**/
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0){
            return "";
        }
        int[] need = new int[128];
        //记录需要的字符的个数
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        //l是当前左边界，r是当前右边界，size记录窗口大小，count是需求的字符个数，start是最小覆盖串开始的index
        int l = 0, r = 0, size = Integer.MAX_VALUE, count = t.length(), start = 0;
        //遍历所有字符
        while (r < s.length()) {
            char c = s.charAt(r);
            if (need[c] > 0) {//需要字符c
                count--;
            }
            need[c]--;//把右边的字符加入窗口
            if (count == 0) {//窗口中已经包含所有字符
                while (l < r && need[s.charAt(l)] < 0) {
                    need[s.charAt(l)]++;//释放左边移动出窗口的字符
                    l++;//指针右移
                }
                if (r - l + 1 < size) {//不能右移时候挑战最小窗口大小，更新最小窗口开始的start
                    size = r - l + 1;
                    start = l;//记录下最小值时候的开始位置，最后返回覆盖串时候会用到
                }
                //l向右移动后窗口肯定不能满足了 重新开始循环
                need[s.charAt(l)]++;
                l++;
                count++;
            }
            r++;
        }
        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
    }

}
