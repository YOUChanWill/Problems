package DataStructures;

import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {

    }

// 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();// 创建一个hashmap
        for (int i = 0; i < nums.length; i++) {// 循环遍历
            if (map.containsKey(target - nums[i])){// 检查 hashMap 中是否存在指定的 key 对应的映射关系，返回值为bool
                return new int[]{map.get(target - nums[i]), i};// 获取指定 key 对应对 value
            }
            map.put(nums[i], i);// 依次放入键值对，键为数组元素，值为数组下标
        }
        return new int[0];
    }


}
