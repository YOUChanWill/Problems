package DataStructures;

public class maxSubArray {
    public static void main(String[] args) {
        int[] ints = {-2,1,-3,4,-1,2,1,-5,4};
        maxSubArray maxSubArray = new maxSubArray();
        System.out.println(maxSubArray.maxSubArray(ints));
    }

//    给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//    子数组 是数组中的一个连续部分。

    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int previous = nums[0];
        for (int i = 1; i < nums.length; i++) {
            previous = Math.max(previous+nums[i],nums[i]);
            ans = Math.max(previous,ans);
        }
        return ans;
    }
}
