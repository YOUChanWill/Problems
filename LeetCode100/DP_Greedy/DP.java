package DP_Greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    }


    /**给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。

     注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。*/
    public boolean wordBreak(String s, List<String> wordDict) {

    }

    /**给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

     子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。*/
    public int lengthOfLIS(int[] nums) {

    }


}
