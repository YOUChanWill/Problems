package day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution {

    /**
     * 给你一个 m x n 大小的矩阵 grid ，由若干正整数组成。
     * 执行下述操作，直到 grid 变为空矩阵：
     * 从每一行删除值最大的元素。如果存在多个这样的值，删除其中任何一个。
     * 将删除元素中的最大值与答案相加。
     * 注意 每执行一次操作，矩阵中列的数据就会减 1 。
     **/
    public static int deleteGreatestValue(int[][] grid) {
        int sum = 0,n = grid[0].length;
        for (int[] row :
                grid) {
            Arrays.sort(row);
        }
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int[] row :
                    grid) {
                max = Math.max(max, row[i]);
            }
            sum += max;
        }
        return sum;
    }


    /**
     * 一个房间里有 n 个座位和 n 名学生，房间用一个数轴表示。给你一个长度为 n 的数组 seats ，其中 seats[i] 是第 i 个座位的位置。同时给你一个长度为 n 的数组 students ，其中 students[j] 是第 j 位学生的位置。
     * 你可以执行以下操作任意次：
     * 增加或者减少第 i 位学生的位置，每次变化量为 1 （也就是将第 i 位学生从位置 x 移动到 x + 1 或者 x - 1）
     * 请你返回使所有学生都有座位坐的 最少移动次数 ，并确保没有两位学生的座位相同。
     * 请注意，初始时有可能有多个座位或者多位学生在 同一 位置。
     **/
    public int minMovesToSeat(int[] seats, int[] students) {
        int sum = 0;
        Arrays.sort(seats);
        Arrays.sort(students);
        for (int i = 0; i < seats.length; i++) {
            sum += Math.abs(seats[i] - students[i]);
        }
        return sum;
    }


    /**
     * 给你一个四位 正 整数 num 。请你使用 num 中的 数位 ，
     * 将 num 拆成两个新的整数 new1 和 new2 。new1 和 new2 中可以有 前导 0 ，且 num 中 所有 数位都必须使用。
     * 比方说，给你 num = 2932 ，你拥有的数位包括：两个 2 ，一个 9 和一个 3 。
     * 一些可能的 [new1, new2] 数对为 [22, 93]，[23, 92]，[223, 9] 和 [2, 329] 。
     * 请你返回可以得到的 new1 和 new2 的 最小 和。
     **/
    public static int minimumSum(int num) {
        int [] a = new int[4];
        for(int i=0;i<4;i++){
            a[i] = num % 10;
            num /= 10;
        }
        Arrays.sort(a);
        int n = a[0]*10 + a[2];
        int m = a[1]*10 + a[3];

        return n + m;
    }

    /**给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

     换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

     以数组形式返回答案。
     **/
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] nums1 = nums.clone();
        int[] ans = new int[nums.length];
        Arrays.sort(nums1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums1[j]){
                    ans[i] = j;
                    break;
                }
            }
        }
        return ans;
    }


    /**两个数对 (a, b) 和 (c, d) 之间的 乘积差 定义为 (a * b) - (c * d) 。

     例如，(5, 6) 和 (2, 7) 之间的乘积差是 (5 * 6) - (2 * 7) = 16 。
     给你一个整数数组 nums ，选出四个 不同的 下标 w、x、y 和 z ，使数对 (nums[w], nums[x]) 和 (nums[y], nums[z]) 之间的 乘积差 取到 最大值 。

     返回以这种方式取得的乘积差中的 最大值 。

     **/
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1];
    }


    /**给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。

     返回该 最大总和 。**/
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length/2; i++) {
            ans += nums[2*i];
        }
        return ans;
    }


    /**给你一个整数数组 prices ，它表示一个商店里若干巧克力的价格。同时给你一个整数 money ，表示你一开始拥有的钱数。

     你必须购买 恰好 两块巧克力，而且剩余的钱数必须是 非负数 。同时你想最小化购买两块巧克力的总花费。

     请你返回在购买两块巧克力后，最多能剩下多少钱。如果购买任意两块巧克力都超过了你拥有的钱，请你返回 money 。注意剩余钱数必须是非负数。**/
    public int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        if (money >= prices[0]+prices[1]){
            return money - prices[0] - prices[1];
        }else return money;
    }


    /**给你一个字符串数组 names ，和一个由 互不相同 的正整数组成的数组 heights 。两个数组的长度均为 n 。

     对于每个下标 i，names[i] 和 heights[i] 表示第 i 个人的名字和身高。

     请按身高 降序 顺序返回对应的名字数组 names 。**/
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> heights[j] - heights[i]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = names[idx[i]];
        }
        return ans;
    }

    /**给你一个整数数组 nums ，数组由 不同正整数 组成，请你找出并返回数组中 任一 既不是 最小值 也不是 最大值 的数字，
     * 如果不存在这样的数字，返回 -1 。返回所选整数。
     **/
    public int findNonMinOrMax(int[] nums) {
        if (nums.length < 3) return -1;
        Arrays.sort(nums,0,3);
        return nums[1];
    }


    /**学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 非递减 的高度顺序排成一行。

     排序后的高度情况用整数数组 expected 表示，其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。

     给你一个整数数组 heights ，表示 当前学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）。

     返回满足 heights[i] != expected[i] 的 下标数量 。**/

    public int heightChecker(int[] heights) {
        int count = 0;
        int[] excepted = heights.clone();
        Arrays.sort(excepted);
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != excepted[i]) count++;
        }
        return count;
    }


    /**一个 句子 指的是一个序列的单词用单个空格连接起来，且开头和结尾没有任何空格。每个单词都只包含小写或大写英文字母。

     我们可以给一个句子添加 从 1 开始的单词位置索引 ，并且将句子中所有单词 打乱顺序 。

     比方说，句子 "This is a sentence" 可以被打乱顺序得到 "sentence4 a3 is2 This1" 或者 "is2 sentence4 This1 a3" 。
     给你一个 打乱顺序 的句子 s ，它包含的单词不超过 9 个，请你重新构造并得到原本顺序的句子。**/
    public String sortSentence(String s) {

    }



    /**给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。

     请你计算并返回该式的最大值。**/
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length - 2] - 1) * (nums[nums.length - 1] - 1);
    }


    /**给你一个下标从 0 开始的整数数组 nums 以及一个目标元素 target 。
     目标下标 是一个满足 nums[i] == target 的下标 i 。
     将 nums 按 非递减 顺序排序后，返回由 nums 中目标下标组成的列表。如果不存在目标下标，返回一个 空 列表。返回的列表必须按 递增 顺序排列。**/
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]){
                integers.add(i);
            }
        }
        return integers;
    }



    /**给你两个长度相同的整数数组 target 和 arr 。每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
     如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。**/
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] list1 = new int[1001];
        int[] list2 = new int[1001];
        for (int a : target) {
            list1[a]++;
        }
        for (int b : arr) {
            list2[b]++;
        }
        for (int i = 0; i < 1001; i++) {
            if (list1[i] != list2[i]) return false;
        }
        return true;
    }



    /**给你一个正整数 num ，请你将它分割成两个非负整数 num1 和 num2 ，满足：
     num1 和 num2 直接连起来，得到 num 各数位的一个排列。
     换句话说，num1 和 num2 中所有数字出现的次数之和等于 num 中所有数字出现的次数。
     num1 和 num2 可以包含前导 0 。
     请你返回 num1 和 num2 可以得到的和的 最小 值。**/
    public int splitNum(int num) {

    }




    /**给你一个非负整数数组 nums 。在一步操作中，你必须：
     选出一个正整数 x ，x 需要小于或等于 nums 中 最小 的 非零 元素。
     nums 中的每个正整数都减去 x。
     返回使 nums 中所有元素都等于 0 需要的 最少 操作数。**/
    public int minimumOperations(int[] nums) {
        int count = 0,sum = 0;
        Arrays.sort(nums);
        int target = nums[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum <= target && target > 0){
                count++;
            }
        }
        return count;
    }



    /**给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。**/
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int a :
                nums1) {
            set1.add(a);
        }
        for (int b :
                nums2) {
            set2.add(b);
        }
        HashSet<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Integer[] temp = intersection.toArray(new Integer[] {});//关键语句

        // 再将Integer型数组转为int型数组
        int[] intArray = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            intArray[i] = temp[i].intValue();
        }
        return intArray;
    }


    /**如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
     首先对两个数组进行排序，然后使用两个指针遍历两个数组。可以预见的是加入答案的数组的元素一定是递增的，为了保证加入元素的唯一性，
     我们需要额外记录变量 pre 表示上一次加入答案数组的元素。
     初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，如果两个数字不相等，
     则将指向较小数字的指针右移一位，如果两个数字相等，且该数字不等于 pre ，
     将该数字添加到答案并更新 pre 变量，同时将两个指针都右移一位。当至少有一个指针超出数组范围时，遍历结束。
    **/
    public int[] intersection01(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }










}
