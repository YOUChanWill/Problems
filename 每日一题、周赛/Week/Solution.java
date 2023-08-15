package Week;

import java.util.*;

public class Solution {


    /**你的笔记本键盘存在故障，每当你在上面输入字符 'i' 时，它会反转你所写的字符串。而输入其他字符则可以正常工作。

     给你一个下标从 0 开始的字符串 s ，请你用故障键盘依次输入每个字符。

     返回最终笔记本屏幕上输出的字符串。**/
    public String finalString(String s) {
        StringBuilder ans = new StringBuilder();
        for (char x :
                s.toCharArray()) {
            if (x == 'i') ans.reverse();
            else ans.append(x);
        }
        return ans.toString();
    }


    /**给你一个长度为 n 的数组 nums 和一个整数 m 。请你判断能否执行一系列操作，将数组拆分成 n 个 非空 数组。

     在每一步操作中，你可以选择一个 长度至少为 2 的现有数组（之前步骤的结果） 并将其拆分成 2 个子数组，而得到的 每个 子数组，至少 需要满足以下条件之一：

     子数组的长度为 1 ，或者
     子数组元素之和 大于或等于 m 。
     如果你可以将给定数组拆分成 n 个满足要求的数组，返回 true ；否则，返回 false 。

     注意：子数组是数组中的一个连续非空元素序列。**/
    public boolean canSplitArray(List<Integer> nums, int m) {
        // 子数组的长度为 1。所以nums.size <= 2的时候也一定可以分裂
        if (nums.size() <= 2) {
            return true;
        }
        // 如果数组中有连续2个数字之和大于m，就一定可以完成分裂
        int pre = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n + pre >= m) {
                return true;
            }
            pre = n;
        }
        return false;
    }


    /**给你一个下标从 0 开始的整数数组 nums 。请你从 nums 中找出和 最大 的一对数，且这两个数数位上最大的数字相等。

     返回最大和，如果不存在满足题意的数字对，返回 -1 。**/
    public int maxSum(int[] nums) {
        Map<Integer, Integer> maxDigitSums = new HashMap<>();
        int maxSum = -1;

        for (int num : nums) {
            int maxDigit = getMaxDigit(num);
            if (maxDigitSums.containsKey(maxDigit)) {
                int currentSum = maxDigitSums.get(maxDigit);
                maxSum = Math.max(maxSum, currentSum + num);
                maxDigitSums.put(maxDigit, Math.max(currentSum, num));
            } else {
                maxDigitSums.put(maxDigit, num);
            }
        }

        return maxSum;
    }

    public static int getMaxDigit(int num) {
        int maxDigit = 0;
        while (num > 0) {
            maxDigit = Math.max(maxDigit, num % 10);
            num /= 10;
        }
        return maxDigit;
    }




    /**给你一个下标从 0 开始的整数数组 nums 和一个整数 x 。

     请你找到数组中下标距离至少为 x 的两个元素的 差值绝对值 的 最小值 。

     换言之，请你找到两个下标 i 和 j ，满足 abs(i - j) >= x 且 abs(nums[i] - nums[j]) 的值最小。

     请你返回一个整数，表示下标距离至少为 x 的两个元素之间的差值绝对值的 最小值 。**/
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int n = nums.size();
        int minAbsDiff = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i + x; j < n; j++) {
                minAbsDiff = Math.min(minAbsDiff, Math.abs(nums.get(i) - nums.get(j)));
            }
        }
        return minAbsDiff;
    }

    public int minAbsoluteDifference01(List<Integer> nums, int x) {
        int n = nums.size();
        int minAns = Integer.MAX_VALUE;

        // 存入当前 nums[0, i-x] 的元素
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = x; i < n; i++) {
            int pre = nums.get(i - x);
            treeSet.add(pre);

            int k = nums.get(i);
            Integer ceiling = treeSet.ceiling(k);
            if (null != ceiling) {
                minAns = Math.min(minAns, Math.abs(ceiling - k));
            }

            Integer floor = treeSet.floor(k);
            if (null != floor) {
                minAns = Math.min(minAns, Math.abs(floor - k));
            }
        }
        return minAns;
    }



}
