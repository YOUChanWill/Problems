package Algorithms;

import java.util.Arrays;

public class SortedSquares {

// 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }


    public int[] sortedSquares01(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {// 设置指针pos，数组头尾比较大小然后填入
            if (nums[i] * nums[i] > nums[j] * nums[j]){
                ans[pos] = nums[i] * nums[i];
                i++;
            }else {
                ans[pos] = nums[j] * nums[j];
                j--;
            }
            pos--;
        }
        return ans;
    }
}
