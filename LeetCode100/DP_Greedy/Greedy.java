package DP_Greedy;

import java.util.ArrayList;
import java.util.List;

public class Greedy {

    /**给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。

     判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。*/
    public boolean canJump(int[] nums) {
        int n = nums.length, rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost){
                rightmost = Math.max(rightmost,i + nums[i]);
                if (rightmost >= n - 1) return true;
            }
        }
        return false;
    }


    /**给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。

     每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:

     0 <= j <= nums[i]
     i + j < n
     返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。*/
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                // 目前能跳到的最远位置变成了下次起跳位置的有边界
                end = maxPosition;
                // 进入下一次跳跃
                steps++;
            }
        }
        return steps;
    }

    /**给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。

     注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。

     返回一个表示每个字符串片段的长度的列表。*/
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

    public List<Integer> partitionLabels01(String s) {
        List<Integer> list = new ArrayList<>();
        int edge[] = new int[26];
        char chars[] = s.toCharArray();
        for(int i = 0; i < chars.length;i++){
            edge[chars[i]-'a'] = i;
        }

        int  idx = 0;
        int last = -1;
        for(int i =0 ;i < chars.length;i++){
            idx = Math.max(idx,edge[chars[i]- 'a'] );
            if(idx == i){
                list.add(i-last);
                last= i;
            }
        }
        return list;
    }

}
