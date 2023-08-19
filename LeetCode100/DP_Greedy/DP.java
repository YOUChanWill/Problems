package DP_Greedy;

import java.util.*;

public class DP {

    /**给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

     完全平方数 是一个整数，其值等于另一个整数的平方；
     换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。**/
    public int numSquares(int n) {
        int[] ans = new int[n + 1];
        Arrays.fill(ans,0x3f3f3f3f);
        ans[0] = 0;
        for (int i = 1; i * i <= n; i++) {
            int x = i * i;
            for (int j = x; j <= n ; j++) {
                ans[j] = Math.min(ans[j],ans[j - x] + 1);
            }
        }
        return ans[n];
    }

    static HashMap<Integer,Integer> map = new HashMap<>();
    public int numSquares(int n) {
        if(map.containsKey(n)) return map.get(n);
        if(n == 0) return 0;
        int count = Integer.MAX_VALUE;
        for(int i = 1; i * i <= n;i++){
            count = Math.min(count,numSquares(n-i*i)+1);
        }
        map.put(n,count);
        return count;
    }


    /**给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

     计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

     你可以认为每种硬币的数量是无限的。*/
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp,max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。

     注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。*/
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 0; i < dp.length; i ++) {
            for(String str : wordDict) {
                int len = str.length();
                if(i >= len && dp[i - len] == true && str.equals(s.substring(i - len, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

     子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }



    /**给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

     测试用例的答案是一个 32-位 整数。

     子数组 是数组的连续子序列。*/
    public int maxProduct(int[] nums) {

    }



    /**给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。**/
    public boolean canPartition(int[] nums) {

    }



    /**给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。*/
    public int longestValidParentheses(String s) {

    }


}
