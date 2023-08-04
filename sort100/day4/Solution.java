package day4;

import java.util.Arrays;
import java.util.HashSet;

public class Solution {


    /**有 N 位扣友参加了微软与力扣举办了「以扣会友」线下活动。主办方提供了 2*N 道题目，
     * 整型数组 questions 中每个数字对应了每道题目所涉及的知识点类型。
     * 若每位扣友选择不同的一题，请返回被选的 N 道题目至少包含多少种知识点类型。**/
    public int halfQuestions(int[] questions) {
        int person = questions.length / 2;
        int n = questions.length;
        int[] countNum = new int[1001]; // 用于存储类型出现的次数
        for (int i = 0; i < n; i++) {
            countNum[questions[i]]++;
        }
        Arrays.sort(countNum);
        // 依次减去最大的数，再判断选取的数目是否超过总人数
        int ans = 0;
        for(int i=1000; person>0; i--){
            ans++;
            person -= countNum[i];
        }
        return ans;
    }



    /**给你两个整数数组 arr1 ， arr2 和一个整数 d ，请你返回两个数组之间的 距离值 。
     「距离值」 定义为符合此距离要求的元素数目：对于元素 arr1[i] ，不存在任何元素 arr2[j] 满足 |arr1[i]-arr2[j]| <= d 。**/
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

    }


    /**给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
     从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
     返回可能的 最小差值 。**/
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length, min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = n - 1; i >= k - 1 ; i--) {
            int ans = nums[i] - nums[i - k + 1];
            min = Math.min(min,ans);
        }
        return min;
    }


    /**给你一个整数数组 nums ，如果它是数组 base[n] 的一个排列，我们称它是个 好 数组。

     base[n] = [1, 2, ..., n - 1, n, n] （换句话说，它是一个长度为 n + 1 且包含 1 到 n - 1 恰好各一次，包含 n  两次的一个数组）。
     比方说，base[1] = [1, 1] ，base[3] = [1, 2, 3, 3] 。

     如果数组是一个好数组，请你返回 true ，否则返回 false 。

     注意：数组的排列是这些数字按任意顺序排布后重新得到的数组。**/
    public boolean isGood(int[] nums) {
        int lengh = nums.length ,max = 0;
        for (int x :
                nums) {
            max = Math.max(max,x);
        }
        if (lengh != max + 1) return false;
        int[] ans = new int[lengh];
        for (int x :
                nums) {
            ans[x]++;
        }
        for (int i = 1; i < lengh; i++) {
            if (ans[i] == 0) return false;
            else if (ans[lengh - 1] != 2) return false;
        }
        return true;
    }


    /**你有 n 枚花的种子。每枚种子必须先种下，才能开始生长、开花。播种需要时间，种子的生长也是如此。
     * 给你两个下标从 0 开始的整数数组 plantTime 和 growTime ，每个数组的长度都是 n ：

     plantTime[i] 是 播种 第 i 枚种子所需的 完整天数 。每天，你只能为播种某一枚种子而劳作。
     无须 连续几天都在种同一枚种子，但是种子播种必须在你工作的天数达到 plantTime[i] 之后才算完成。

     growTime[i] 是第 i 枚种子完全种下后生长所需的 完整天数 。在它生长的最后一天 之后 ，将会开花并且永远 绽放 。
     从第 0 开始，你可以按 任意 顺序播种种子。

     返回所有种子都开花的 最早 一天是第几天。**/
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int ans = -1, n = plantTime.length;

    }





    /**爱丽丝和鲍勃拥有不同总数量的糖果。给你两个数组 aliceSizes 和 bobSizes ，
     * aliceSizes[i] 是爱丽丝拥有的第 i 盒糖果中的糖果数量，bobSizes[j] 是鲍勃拥有的第 j 盒糖果中的糖果数量。

     两人想要互相交换一盒糖果，这样在交换之后，他们就可以拥有相同总数量的糖果。一个人拥有的糖果总数量是他们每盒糖果数量的总和。

     返回一个整数数组 answer，其中 answer[0] 是爱丽丝必须交换的糖果盒中的糖果的数目，answer[1] 是鲍勃必须交换的糖果盒中的糖果的数目。
     如果存在多个答案，你可以返回其中 任何一个 。题目测试用例保证存在与输入对应的答案。**/
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int bob = Arrays.stream(bobSizes).sum(), alice = 0;
        int[] answer = new int[2];
        HashSet<Integer> set = new HashSet<>();
        for (int x :
                aliceSizes) {
            alice += x;
            set.add(x);
        }
        int ans = (alice - bob) / 2;
        for (int x :
                bobSizes) {
            if (set.contains(x + ans)) {
                answer[0] = x + ans;
                answer[1] = x;
            }
        }
        return answer;
    }



    /**班里有 m 位学生，共计划组织 n 场考试。给你一个下标从 0 开始、大小为 m x n 的整数矩阵 score ，
     * 其中每一行对应一位学生，而 score[i][j] 表示第 i 位学生在第 j 场考试取得的分数。矩阵 score 包含的整数 互不相同 。

     另给你一个整数 k 。请你按第 k 场考试分数从高到低完成对这些学生（矩阵中的行）的排序。

     返回排序后的矩阵。**/
    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score,(a,b)->b[k]-a[k]);
        return score;
    }



}
