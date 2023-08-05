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
        ArrayList<Integer> ans = new ArrayList<>();

    }



    /**给你一个整数数组 arr，请你检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。

     更正式地，检查是否存在两个下标 i 和 j 满足：

     i != j
     0 <= i, j < arr.length
     arr[i] == 2 * arr[j]
     **/
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>()
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



}
