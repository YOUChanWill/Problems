package normal;

import java.util.*;
import java.util.stream.IntStream;

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


    /**你会得到一个字符串 s (索引从 0 开始)，你必须对它执行 k 个替换操作。
     * 替换操作以三个长度均为 k 的并行数组给出：indices, sources,  targets。

     要完成第 i 个替换操作:

     检查 子字符串  sources[i] 是否出现在 原字符串 s 的索引 indices[i] 处。
     如果没有出现， 什么也不做 。
     如果出现，则用 targets[i] 替换 该子字符串。
     例如，如果 s = "abcd" ， indices[i] = 0 , sources[i] = "ab"， targets[i] = "eee" ，那么替换的结果将是 "eeecd" 。

     所有替换操作必须 同时 发生，这意味着替换操作不应该影响彼此的索引。测试用例保证元素间不会重叠 。

     例如，一个 s = "abc" ，  indices = [0,1] ， sources = ["ab"，"bc"] 的测试用例将不会生成，因为 "ab" 和 "bc" 替换重叠。
     在对 s 执行所有替换操作后返回 结果字符串 。

     子字符串 是字符串中连续的字符序列。**/
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        // 设 s 长度为 n，创建一个长为 n 的 replace 列表。
        // 遍历每个替换操作。对于第 i 个替换操作，如果从 indices[i] 开始的字符串有前缀 sources[i]，则可以替换成 target[i]。
        // 此时记录 replace[indices[i]]=(target[i],len(sources[i]))，表示替换后的字符串，以及被替换的长度。
        // 初始化 i=0，如果 replace[i] 是空的，那么无需替换，把 s[i] 加入答案，然后 i 加一；
        // 如果 replace[i] 不为空，那么把 replace[i][0] 加入答案，然后 i 增加 replace[i][1]。循环直到 i=n 为止。
        int n = s.length();
        String[] replaceStr = new String[n]; // 替换后的字符串
        int[] replaceLen = new int[n];    // 被替换的长度
        Arrays.fill(replaceLen, 1);     // 无需替换时 i+=1
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            if (s.startsWith(sources[i], idx)) {
                replaceStr[idx] = targets[i];
                replaceLen[idx] = sources[i].length();
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i += replaceLen[i]) { // 无需替换时 i+=1
            if (replaceStr[i] == null) {
                ans.append(s.charAt(i));
            } else {
                ans.append(replaceStr[i]);
            }
        }
        return ans.toString();
    }

    public String findReplaceString01(String s, int[] indices, String[] sources, String[] targets) {
        int[] order = IntStream.range(0, indices.length).boxed().sorted(Comparator.comparingInt(i -> indices[i]))
                .mapToInt(i -> i).toArray();
        StringBuilder sb = new StringBuilder();
        int preIndex = 0;
        for (int i : order) {
            int index = indices[i];
            sb.append(s, preIndex, index);
            if (s.startsWith(sources[i], index)) {
                sb.append(targets[i]);
                preIndex = index + sources[i].length();
            } else {
                preIndex = index;
            }
        }
        sb.append(s.substring(preIndex));
        return sb.toString();
    }



    /**给你一个 rows x cols 大小的矩形披萨和一个整数 k ，矩形包含两种字符： 'A' （表示苹果）和 '.' （表示空白格子）。你需要切披萨 k-1 次，得到 k 块披萨并送给别人。

     切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。

     请你返回确保每一块披萨包含 至少 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。**/
    public int ways(String[] pizza, int k) {
        final int MOD = (int) 1e9 + 7;
        int m = pizza.length, n = pizza[0].length();
        int[][] sum = new int[m + 1][n + 1]; // 二维后缀和
        int[][] f = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                sum[i][j] = sum[i][j + 1] + sum[i + 1][j] - sum[i + 1][j + 1] + (pizza[i].charAt(j) & 1);
                if (sum[i][j] > 0) f[i][j] = 1; // 初始值
            }
        }

        while (--k > 0) {
            int[] colS = new int[n]; // colS[j] 表示 f 第 j 列的后缀和
            for (int i = m - 1; i >= 0; i--) {
                int rowS = 0; // f[i] 的后缀和
                for (int j = n - 1; j >= 0; j--) {
                    int tmp = f[i][j];
                    if (sum[i][j] == sum[i][j + 1]) // 左边界没有苹果
                        f[i][j] = f[i][j + 1];
                    else if (sum[i][j] == sum[i + 1][j]) // 上边界没有苹果
                        f[i][j] = f[i + 1][j];
                    else // 左边界上边界都有苹果，那么无论怎么切都有苹果
                        f[i][j] = (rowS + colS[j]) % MOD;
                    rowS = (rowS + tmp) % MOD;
                    colS[j] = (colS[j] + tmp) % MOD;
                }
            }
        }
        return f[0][0];
    }


    /**给你两个字符串 start 和 target ，长度均为 n 。每个字符串 仅 由字符 'L'、'R' 和 '_' 组成，其中：

     字符 'L' 和 'R' 表示片段，其中片段 'L' 只有在其左侧直接存在一个 空位 时才能向 左 移动，而片段 'R' 只有在其右侧直接存在一个 空位 时才能向 右 移动。
     字符 '_' 表示可以被 任意 'L' 或 'R' 片段占据的空位。
     如果在移动字符串 start 中的片段任意次之后可以得到字符串 target ，返回 true ；否则，返回 false 。**/
    public boolean canChange(String start, String target) {
        //无论怎么移动，由于 L 和 R 无法互相穿过对方，那么去掉 _ 后的剩余字符应该是相同的，否则返回 false。
        if (!start.replaceAll("_","").equals(target.replaceAll("_",""))) return false;
        for (int i = 0, j = 0; i < start.length(); i++) {
            if (start.charAt(i) == '_') continue;
            while (target.charAt(j) == '_') j++;
            // 如果当前字符为 R 且 i>j，由于 R 由于无法向左移动，返回 false；
            if (i != j && (start.charAt(i) == 'R') == (i > j)) return false;
            // 如果当前字符为 L 且 i<j，由于 L 由于无法向右移动，返回 false；
            if (i != j && (start.charAt(i) == 'L') == (i < j)) return false;
            ++j;
        }
        return true;
    }

    public boolean canChange01(String start, String target) {
        int n=start.length();
        int i=0,j=0;
        while(j<n){
            if(target.charAt(j)=='L'){
                while(i<n && start.charAt(i)=='_') i++;
                if(i>=n || i<j || start.charAt(i)=='R') return false;
                j++;
                i++;
            }else if(target.charAt(j)=='R'){
                while(i<n&&start.charAt(i)=='_')    i++;
                if(i>=n || i>j || start.charAt(i)=='L') return false;
                j++;
                i++;
            }else j++;
        }
        while(i<n){
            if(start.charAt(i++)!='_')  return false;
        }
        return true;
    }



    /**给你一个数组 seats 表示一排座位，其中 seats[i] = 1 代表有人坐在第 i 个座位上，
     * seats[i] = 0 代表座位 i 上是空的（下标从 0 开始）。

     至少有一个空座位，且至少有一人已经坐在座位上。

     亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。

     返回他到离他最近的人的最大距离。*/
    public int maxDistToClosest(int[] seats) {
        int count1 = 0;
        int count2 = 0;
        int i = 0, j = seats.length-1;
        // count1记录开头连续0的个数
        while(seats[i] == 0){
            count1++;
            i++;
        }
        // count2记录结尾连续0的个数
        while(seats[j] == 0){
            count2++;
            j--;
        }
        // countmax记录从第一个1到最后一个1之间，连续0的最大值
        int countmid = 0, countmax = 0;
        for(int k = i+1; k <= j; k++){
            if(seats[k] == 0){
                countmid++;
            }else{
                countmax = Math.max(countmax, countmid);
                countmid = 0;
            }
        }
        // 返回count1, count2, (countmax+1)/2三者中最大值
        return Math.max(Math.max(count1, count2), (countmax+1)/2);
    }

    public int maxDistToClosest01(int[] seats) {
        int first = -1, last = -1;
        int d = 0, n = seats.length;
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                if (last != -1) {
                    d = Math.max(d, i - last);
                }
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }
        return Math.max(d / 2, Math.max(first, n - last - 1));
    }













}
