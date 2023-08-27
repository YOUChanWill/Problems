package 查找算法;

import java.util.Arrays;
import java.util.HashMap;

public class Search {


    /**剑指 Offer 03. 数组中重复的数字

     在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字*/
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while (i < nums.length){
            if (nums[i] == i){
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) return nums[i];
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }


    /**剑指 Offer 53 - I. 在排序数组中查找数字 I

     统计一个数字在排序数组中出现的次数。*/
    public int search(int[] nums, int target) {
        int count = 0;
        for (int x :
                nums) {
            if (x == target) count++;
        }
        return count;
    }

    public int search01(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }
    int helper(int[] nums, int tar) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] <= tar) i = m + 1;
            else j = m - 1;
        }
        return i;
    }


    /**剑指 Offer 53 - II. 0～n-1中缺失的数字

     一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
     在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。*/
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return nums.length;
    }

    public int missingNumber1(int[] nums) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] == m) i = m + 1;
            else j = m - 1;
        }
        return i;
    }


    /**剑指 Offer 04. 二维数组中的查找

     在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，每一列都按照从上到下 非递减 的顺序排序。
     请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。*/
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

    }

    private int search(int[] nums, int target){

    }


    /**剑指 Offer 11. 旋转数组的最小数字

     把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。

     给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
     例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。

     注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。*/
    public int minArray(int[] numbers) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) min = numbers[i];
        }
        return min;
    }

    public int minArray01(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2; // 防止相加越界
            if (numbers[mid] > numbers[right]) left = mid + 1;
            else if (numbers[mid] < numbers[right]) right = mid;
            else right--;
        }
        return numbers[left];
    }


    /**剑指 Offer 50. 第一个只出现一次的字符

     在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。*/
    public char firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char x :
                s.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (char x :
                s.toCharArray()) {
            if (map.get(x) == 1)return x;
        }
        return ' ';
    }

    public char firstUniqChar01(String s) {
        int[] cnt = new int[26];
        char[] arr = s.toCharArray();

        for(int i = 0;i < arr.length; i++) {
            cnt[arr[i] - 'a'] ++;
        }
        for(int i = 0;i < arr.length; i++) {
            if(cnt[arr[i] - 'a'] == 1) return arr[i];
        }
        return ' ';
    }

    public char firstUniqChar02(String s) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            int start = s.indexOf('a' + i);//找到字母的第一个位置
            if (start != -1) {
                int end = s.lastIndexOf('a' + i);
                if (start == end) {
                    res = Math.min(res, start);
                }
            }
        }
        return res == Integer.MAX_VALUE ? ' ' : s.charAt(res);
    }





}
