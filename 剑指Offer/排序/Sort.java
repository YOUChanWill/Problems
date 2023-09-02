package 排序;

import java.util.Arrays;

public class Sort {


    /**剑指 Offer 45. 把数组排成最小的数

     输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。*/
    public String minNumber(int[] nums) {

    }


    /**剑指 Offer 61. 扑克牌中的顺子

     从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。*/
    public boolean isStraight(int[] nums) {

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




}
