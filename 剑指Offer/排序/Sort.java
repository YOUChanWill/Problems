package 排序;

import java.util.Arrays;
import java.util.HashSet;

public class Sort {


    /**剑指 Offer 45. 把数组排成最小的数

     输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。*/
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }


    /**剑指 Offer 61. 扑克牌中的顺子

     从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。*/
    public boolean isStraight(int[] nums) {
        HashSet<Integer> set = new HashSet<>(); // 用于判断是否有重复的
        int max = -1 ,min = 14;
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i]) && nums[i] != 0) return false;
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min && nums[i] != 0) {
                min = nums[i];
            }
        }
        return max - min < 5;
    }


    /**剑指 Offer 40. 最小的k个数

     输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。*/
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }


    /**剑指 Offer 41. 数据流中的中位数

     如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
     如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

     例如，
     [2,3,4] 的中位数是 3
     [2,3] 的中位数是 (2 + 3) / 2 = 2.5

     设计一个支持以下两种操作的数据结构：

     void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     double findMedian() - 返回目前所有元素的中位数。*/
    class MedianFinder {

        /** initialize your data structure here. */
        public MedianFinder() {

        }

        public void addNum(int num) {

        }

        public double findMedian() {

        }
    }




}
