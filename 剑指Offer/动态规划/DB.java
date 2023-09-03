package 动态规划;

public class DB {

    /**剑指 Offer 10- I. 斐波那契数列

     写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

     F(0) = 0,   F(1) = 1
     F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

     答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。*/
    public int fib(int n) {
        int a = 0,b = 1,sum;
        for (int i = 0; i < n; i++) {
            sum = (int) ((int) (a + b) % (1e9 + 7));
            a = b;
            b = sum;
        }
        return a;
    }


    /**剑指 Offer 10- II. 青蛙跳台阶问题

     一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

     答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。*/
    public int numWays(int n) {
        int a = 1, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (int) ((int) (a + b) % (1e9 + 7));
            a = b;
            b = sum;
        }
        return a;
    }


    /**剑指 Offer 63. 股票的最大利润

     假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？*/
    public int maxProfit(int[] prices) {
        int max = Integer.MIN_VALUE , cur;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j <prices.length; j++) {
                if (prices[i] < prices[j]) {
                    cur = prices[j] - prices[i];
                    max = Math.max(max,cur);
                }
            }
        }
        return max;
    }

    public int maxProfit1(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int x :
                prices) {
            cost = Math.min(x, cost);
            profit = Math.max(profit,x - cost);
        }
        return profit;
    }


    /**剑指 Offer 42. 连续子数组的最大和

     输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

     要求时间复杂度为O(n)。*/
    public int maxSubArray(int[] nums) {

    }


    /**剑指 Offer 47. 礼物的最大价值

     在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？*/
    public int maxValue(int[][] grid) {

    }

    /**剑指 Offer 46. 把数字翻译成字符串

     给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
     一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。*/
    public int translateNum(int num) {

    }


    /**剑指 Offer 48. 最长不含重复字符的子字符串

     请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。*/
    public int lengthOfLongestSubstring(String s) {

    }


    /**剑指 Offer 19. 正则表达式匹配

     请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
     在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。*/
    public boolean isMatch(String s, String p) {

    }


    /**剑指 Offer 49. 丑数

     我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。*/
    public int nthUglyNumber(int n) {

    }


    /**剑指 Offer 60. n个骰子的点数

     把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

     你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。*/
    public double[] dicesProbability(int n) {

    }

}
