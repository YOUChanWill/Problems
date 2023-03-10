package DataStructures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Intersect {


// 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，
// 应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
    public int[] intersect(int[] nums1, int[] nums2) {
        // 挑选出两个数组找个短的那个
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        for (int num : nums1) {
            // map.getOrDefault : 存在这个数就返回，不存在就返回默认值
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        // 开辟一块内存空间用来存放两个数组的交集
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            // num1中不存在这个数就在map中添加num=0
            int count = map.getOrDefault(num, 0);
            // 存在这个数就往后执行
            if (count > 0) {
                // 把这个数填充到数组中
                intersection[index++] = num;
                // 计数减一
                count--;
                // 如果还大于0
                if (count > 0) {
                    // 再次添加进去，覆盖之前那个key
                    map.put(num, count);
                } else {
                    // 不大于0移除这个数
                    map.remove(num);
                }
            }
        }
        // public static int[] copyOfRange(int[] original, int from, int to)
        // 对已有([9, 4, 0])的数组进行截取和赋值,结果为[9,4]
        return Arrays.copyOfRange(intersection, 0, index);
    }
}


