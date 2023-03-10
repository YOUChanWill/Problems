package Algorithms;

import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {

    }

//给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列
//请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
//
//以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
//
//你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
//你所设计的解决方案必须只使用常量级的额外空间。

    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();// 创建一个hashmap
        for (int i = 0; i < numbers.length; i++) {// 循环遍历
            if (map.containsKey(target - numbers[i])){// 检查 hashMap 中是否存在指定的 key 对应的映射关系，返回值为bool
                return new int[]{map.get(target - numbers[i]) + 1, i + 1};// 获取指定 key 对应对 value
            }
            map.put(numbers[i], i);// 依次放入键值对，键为数组元素，值为数组下标
        }
        return new int[0];
    }

    public int[] twoSum01(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;// 头尾指针
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;// 如果小于目标，则移动左指针
            } else {
                --high;// 如果大于目标，则移动右指针
            }
        }
        return new int[]{-1, -1};
    }
}
