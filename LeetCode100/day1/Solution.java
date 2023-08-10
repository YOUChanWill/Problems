package day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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


    /**给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

     异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。**/
    public List<Integer> findAnagrams(String s, String p) {
        //使用数组存储字母出现频率,通过数组比较判断是否是异位词
        int[] scount = new int[26];
        int[] pcount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            ++pcount[p.charAt(i) - 'a'];
        }

        int len = s.length();
        int l = 0, r = 0;
        List<Integer> ans = new ArrayList<>();
        while (r < len) {
            //增大窗口
            ++scount[s.charAt(r) - 'a'];
            //缩小窗口的条件是窗口长度大于等于p的长度
            while (r - l + 1 >= p.length()) {
                //数组相等则记录结果
                if (Arrays.equals(scount, pcount)) {
                    ans.add(l);
                }
                char d = s.charAt(l);
                --scount[d - 'a'];
                l++;
            }
            r++;
        }
        return ans;
    }

    public List<Integer> findAnagrams01(String s, String p) {
        int n = s.length(), m = p.length();
        List<Integer> res = new ArrayList<>();
        if(n < m) return res;
        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        for(int i = 0; i < m; i++){
            pCnt[p.charAt(i) - 'a']++;
            sCnt[s.charAt(i) - 'a']++;
        }
        if(Arrays.equals(sCnt, pCnt)){
            res.add(0);
        }
        for(int i = m; i < n; i++){
            sCnt[s.charAt(i - m) - 'a']--;
            sCnt[s.charAt(i) - 'a']++;
            if(Arrays.equals(sCnt, pCnt)){
                res.add(i - m + 1);
            }
        }
        return res;
    }

    /**给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
     * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请

     你返回所有和为 0 且不重复的三元组。

     注意：答案中不可以包含重复的三元组。**/
    public List<List<Integer>> threeSum(int[] nums) {
        //定义一个结果集
        List<List<Integer>> res = new ArrayList<>();
        //数组的长度
        int len = nums.length;
        //当前数组的长度为空，或者长度小于3时，直接退出
        if(nums == null || len <3){
            return res;
        }
        //将数组进行排序
        Arrays.sort(nums);
        //遍历数组中的每一个元素
        for(int i = 0; i<len;i++){
            //如果遍历的起始元素大于0，就直接退出
            //原因，此时数组为有序的数组，最小的数都大于0了，三数之和肯定大于0
            if(nums[i]>0){
                break;
            }
            //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int l = i +1;
            int r = len-1;
            //当 l 不等于 r时就继续遍历
            while(l<r){
                //将三数进行相加
                int sum = nums[i] + nums[l] + nums[r];
                //如果等于0，将结果对应的索引位置的值加入结果集中
                if(sum==0){
                    // 将三数的结果集加入到结果集中
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //在将左指针和右指针移动的时候，先对左右指针的值，进行判断
                    //如果重复，直接跳过。
                    //去重，因为 i 不变，当此时 l取的数的值与前一个数相同，所以不用在计算，直接跳
                    while(l < r && nums[l] == nums[l+1]) {
                        l++;
                    }
                    //去重，因为 i不变，当此时 r 取的数的值与前一个相同，所以不用在计算
                    while(l< r && nums[r] == nums[r-1]){
                        r--;
                    }
                    //将 左指针右移，将右指针左移。
                    l++;
                    r--;
                    //如果结果小于0，将左指针右移
                }else if(sum < 0){
                    l++;
                    //如果结果大于0，将右指针左移
                }else if(sum > 0){
                    r--;
                }
            }
        }
        return res;
    }


    /**给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

     字母异位词 是由重新排列源单词的所有字母得到的一个新单词。**/
    public List<List<String>> groupAnagrams(String[] strs) {

    }



}
