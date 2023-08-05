package day5;

import org.omg.CORBA.OMGVMCID;

import java.util.*;

public class Solution {


    /**给定一个字符串 s 和一个整数 k 。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。

     返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串 。**/
    public String orderlyQueue(String s, int k) {
        if (k > 1) {
            // 如果 k 大于 1，则可以将字符串排序得到最小字符串
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            return new String(charArray);
        } else {
            // 如果 k 等于 1，则从 s 中选取一个字符加到末尾得到最小字符串
            String minString = s;
            for (int i = 1; i < s.length(); i++) {
                // 从字符串s的第i个字符开始提取子字符串,从字符串s的开头提取到第i个字符（不包括第i个字符）的子字符串。
                String rotated = s.substring(i) + s.substring(0, i);
                if (rotated.compareTo(minString) < 0) {
                    minString = rotated;
                }
            }
            return minString;
        }
    }


    /**给你一个整数数组 digits ，其中每个元素是一个数字（0 - 9）。数组中可能存在重复元素。

     你需要找出 所有 满足下述条件且 互不相同 的整数：

     该整数由 digits 中的三个元素按 任意 顺序 依次连接 组成。
     该整数不含 前导零
     该整数是一个 偶数
     例如，给定的 digits 是 [1, 2, 3] ，整数 132 和 312 满足上面列出的全部条件。

     将找出的所有互不相同的整数按 递增顺序 排列，并以数组形式返回。**/
    public int[] findEvenNumbers(int[] digits) {
        // 统计0 ~ 9各个数字的出现次数
        int[] count = new int[10];
        for(int digit: digits){
            count[digit] ++;
        }

        // 百位、十位、个位按各自规则不重不漏的进行数字选取
        // 各个位置选取之后统计结果减一，完成当前遍历后恢复（统计结果再加一）
        List<Integer> list = new ArrayList<>();

        // 百位按1 ~ 9取存在的数字（保证了顺序且不重复）
        for(int i = 1; i < 10; i ++){
            if(count[i] > 0){
                count[i] --;
                // 十位取剩余0 ~ 9中存在的数字（保证了顺序且不重复）
                for(int j = 0; j < 10; j ++){
                    if(count[j] > 0){
                        count[j] --;
                        // 个位取剩余存在的偶数（保证了顺序且不重复）
                        for(int k = 0; k < 10; k += 2){
                            if(count[k] > 0){
                                list.add(i * 100 + j * 10 + k);
                            }
                        }
                        count[j] ++;
                    }
                }
                count[i] ++;
            }
        }

        // 将有序的list按顺序转为int[]后返回
        int size = list.size();
        int[] ans = new int[size];
        for(int i = 0; i < size; i ++){
            ans[i] = list.get(i);
        }
        return ans;
    }


    /**给你一个整数数组 arr，请你检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。

     更正式地，检查是否存在两个下标 i 和 j 满足：

     i != j
     0 <= i, j < arr.length
     arr[i] == 2 * arr[j]
     **/
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int x :
                arr) {
            if (set.contains(x) || set.contains(x * 4)) return true;
            set.add(x * 2);
        }
        return false;
    }


    /**假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。

     对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
     并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。

     你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。**/
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int n = g.length, m = s.length, ans = 0;
        for (int i = 0 ,j = 0; i < n && j < m; i++ ,j++) {
            while (j < m && g[i] > s[j]) j++;
            if (j < m){
                ans++;
            }
        }
        return ans;
    }


    /**和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。

     现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。

     数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。**/
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x :
                nums) {
            map.put(x, map.getOrDefault(x, 0) + 1); // 遇到相同的则value值加1
        }
        int ans = 0;
        for (int x :
                nums) {
            if (map.containsKey(x - 1)) {
                ans = Math.max(ans, map.get(x) + map.get(x - 1)); // 如果存在跟当前值差1的，则统计map中的value值
            }
        }
        return ans; // 返回取到的最大值
    }

    // 使用双指针进行滑动窗口
    public int findLHS01(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        for(int begin =0 ,end = 0; end < nums.length; end++){
            while(end > begin && nums[end] - nums[begin] > 1) begin++;
            if(nums[end] - nums[begin] == 1){
                res = Math.max(res, end - begin + 1);
            }
        }
        return res;
    }

    /**给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。

     序号代表了一个元素有多大。序号编号的规则如下：

     序号从 1 开始编号。
     一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
     每个数字的序号都应该尽可能地小。**/
    public int[] arrayRankTransform(int[] arr) {
        int[] sort = arr.clone();
        Arrays.sort(sort);
        HashMap<Integer, Integer> map = new HashMap<>();
        int currIndex = 1;
        for (int x :
                sort) {
            if (!map.containsKey(x)) map.put(x, currIndex++);
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = map.get(arr[i]);
        }
        return ans;
    }

    /**给你一个整数数组 nums ，统计并返回在 nums 中同时至少具有一个严格较小元素和一个严格较大元素的元素数目。**/
    public int countElements(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int x :
                nums) {
            if (x > nums[0] && x < nums[nums.length - 1]) ans++;
        }
        return ans;
    }

    public int countElements01(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int x :
                nums) {
            min = Math.min(x,min);
            max = Math.max(x,max);
        }
        int ans = 0;
        for (int x :
                nums) {
            if (x != min && x != max) ans++;
        }
        return ans;
    }


    /**给你一个非负整数数组 nums 。如果存在一个数 x ，使得 nums 中恰好有 x 个元素 大于或者等于 x ，那么就称 nums 是一个 特殊数组 ，而 x 是该数组的 特征值 。

     注意： x 不必 是 nums 的中的元素。

     如果数组 nums 是一个 特殊数组 ，请返回它的特征值 x 。否则，返回 -1 。可以证明的是，如果 nums 是特殊数组，那么其特征值 x 是 唯一的 。**/
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // 实现原数组的降序
        for (int i = 0 ,j = n - 1; i < j; i++ ,j--) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] >= i && (i == n || nums[i] < i)) return i;
        }
        return -1;
    }


    /**给你一个房屋数组houses 和一个整数 k ，其中 houses[i] 是第 i 栋房子在一条街上的位置，现需要在这条街上安排 k 个邮筒。

     请你返回每栋房子与离它最近的邮筒之间的距离的 最小 总和。

     答案保证在 32 位有符号整数范围以内。**/
    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        Arrays.sort(houses);

        int[][] medsum = new int[n][n];
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                medsum[i][j] = medsum[i + 1][j - 1] + houses[j] - houses[i];
            }
        }

        int[][] f = new int[n][k + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < n; ++i) {
            f[i][1] = medsum[0][i];
            for (int j = 2; j <= k && j <= i + 1; ++j) {
                for (int i0 = 0; i0 < i; ++i0) {
                    f[i][j] = Math.min(f[i][j], f[i0][j - 1] + medsum[i0 + 1][i]);
                }
            }
        }

        return f[n - 1][k];
    }


    public int minDistance01(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i < k + 1; i++) {
            Arrays.fill(dp[i], 0x3f3f3f3f);
            dp[i][0] = 0;
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                // 第i个邮筒不分配房子时 此时j个房子到i-1个邮局的距离为
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                // 第i个邮局最多可以分配j个房子
                int cost = 0;
                for (int l = 1; l <= j; l++) {
                    // 给第i邮局分配l个房子 求该邮局将这些房子距离它的距离加起来
                    // 负责房子的范围是 [j - l, j - 1] 放一个邮局
                    // 因为此问题中，邮局位置不是固定的，而房屋位置是固定的 因此不可以累加 转换为求局部房屋之间
                    // 任选一个位置作为邮局 使得在这局部房屋之间距离最短
                    // [j - l ,  j - 1] 为什么是[j - l, j - 1] 而不是[j - l - 1, j - 1]需要思考一下
                    cost = getRes(houses, j - l, j - 1);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - l] + cost);
                }
            }
        }
        return dp[k][n];
    }
    public int getRes(int[] houses, int i, int j){
        int res = 0;
        while (i < j){
            res += houses[j] - houses[i];
            j--;
            i++;
        }
        return res;
    }

}
