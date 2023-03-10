package Algorithms;

public class SingleNumber {

// 任何数于0异或为任何数 0 ^ n => n
//相同的数异或为0: n ^ n => 0

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
