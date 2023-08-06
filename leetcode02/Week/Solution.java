package Week;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
}
