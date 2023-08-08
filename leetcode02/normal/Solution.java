package normal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {


    /**在桌子上有 N 张卡片，每张卡片的正面和背面都写着一个正数（正面与背面上的数有可能不一样）。

     我们可以先翻转任意张卡片，然后选择其中一张卡片。

     如果选中的那张卡片背面的数字 X 与任意一张卡片的正面的数字都不同，那么这个数字是我们想要的数字。

     哪个数是这些想要的数字中最小的数（找到这些数中的最小值）呢？如果没有一个数字符合要求的，输出 0。

     其中, fronts[i] 和 backs[i] 分别代表第 i 张卡片的正面和背面的数字。

     如果我们通过翻转卡片来交换正面与背面上的数，那么当初在正面的数就变成背面的数，背面的数就变成正面的数。**/
    public int flipgame(int[] fronts, int[] backs) {
        int lengh = fronts.length,min = 2000;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < lengh; i++) {
            if (fronts[i] == backs[i]){
                set.add(fronts[i]);
            }
        }
        for (int i = 0; i < lengh; i++) {
            if (set.add(fronts[i]) && fronts[i] < min){
                min = fronts[i];
            }
            if (set.add(backs[i]) && backs[i] < min){
                min = backs[i];
            }
        }
        return min % 2000;
    }


    /**给一个 C++ 程序，删除程序中的注释。这个程序source是一个数组，其中source[i]表示第 i 行源码。 这表示每行源码由 '\n' 分隔。
     在 C++ 中有两种注释风格，行内注释和块注释。

     字符串// 表示行注释，表示//和其右侧的其余字符应该被忽略。
     字符串 表示一个块注释，它表示直到下一个（非重叠）出现的之间的所有字符都应该被忽略。（阅读顺序为从左到右）非重叠是指，字符串并没有结束块注释，因为注释的结尾与开头相重叠。
     第一个有效注释优先于其他注释。

     如果字符串//出现在块注释中会被忽略。
     同样，如果字符串/*出现在行或块注释中也会被忽略。
     如果一行在删除注释之后变为空字符串，那么不要输出该行。即，答案列表中的每个字符串都是非空的。
     **/
    public List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean blockComment = false;
        for (String s : source) {
            int m = s.length();
            for (int i = 0; i < m; ++i) {
                if (blockComment) {
                    // 遇到"*/"，则将状态改为不在注释块内，继续遍历后面第三个字符。
                    if (i + 1 < m && s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                        blockComment = false;
                        ++i;
                    }
                } else {
                    // 遇到"/*"，则将状态改为在注释块内，继续遍历后面第三个字符。
                    if (i + 1 < m && s.charAt(i) == '/' && s.charAt(i + 1) == '*') {
                        blockComment = true;
                        ++i;
                        // 遇到"//"，则直接忽略该行后面的部分。
                    } else if (i + 1 < m && s.charAt(i) == '/' && s.charAt(i + 1) == '/') {
                        break;
                        // 遇到其他字符，将该字符记录到 newLine 中。
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }
            if (!blockComment && sb.length() > 0) {
                ans.add(sb.toString());
                sb.setLength(0);
            }
        }
        return ans;
    }


    /**给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的
     * 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。

     请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。**/
    public int maxAbsoluteSum(int[] nums) {
        int ans = 0, Max = 0, Min = 0;
        for (int x :
                nums) {
            Max = Math.max(Max, 0) + x;
            Min = Math.min(Min, 0) + x;
            ans = Math.max(ans,Math.max(Max,-Min));
        }
        return ans;
    }

    public int maxAbsoluteSum01(int[] nums) {
        //思路为找到子数组和的最大值与最小值，取绝对值最大的返回
        //此处采用动态规划的方法。dpMax[i]表示以元素i为结尾的子数组的最大值，dpMin[i]与之相对
        //那么所求ans=max(dpMax[n-1],abs(dpMin[n-1]))
        //状态转移方程为：dpMax[i]=max(dpMax[i-1],dpMax[i-1]+nums[i]),dpMin与之相对
        //观察到dp[i]只与dp[i-1]以及nums[i]有关，故可以进行空间压缩
        //考虑正负,用两个变量分别承接正负数
        /*int dpMax=0,dpMin=0,positiveSum=0,negativeSum=0;
        for(int num:nums){
            positiveSum+=num;
            dpMax=Math.max(dpMax,positiveSum);
            positiveSum=Math.max(0,positiveSum);
            negativeSum+=num;
            dpMin=Math.min(dpMin,negativeSum);
            negativeSum=Math.min(negativeSum,0);
        }
        return Math.max(dpMax,-dpMin);*/
        //下面是前缀和的解法
        //由于子数组和等于两个前缀和的差，那么取前缀和中的最大值与最小值，它俩的差就是答案。
        int preSum=0,max=0,min=0;
        for(int num:nums){
            preSum+=num;
            if(preSum>max) max=preSum;
            else if(preSum<min) min=preSum;
        }
        return max-min;
    }















}
