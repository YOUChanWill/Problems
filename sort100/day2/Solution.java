package day2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {


    /**给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     你可以假设数组是非空的，并且给定的数组总是存在多数元素。**/
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // Boyer-Moore 投票算法
    public int majorityElement01(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }


    /**给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。**/
    public int missingNumber(int[] nums) {
        int lengh = nums.length;
        int sum = 0, ans = 0;
        for (int i = 0; i < lengh; i++) {
            sum += i;
            ans += nums[i];
        }
        return sum + lengh - ans;
    }


    /**给你一个下标从 0 开始长度为 偶数 的整数数组 nums 。

     只要 nums 不是 空数组，你就重复执行以下步骤：

     找到 nums 中的最小值，并删除它。
     找到 nums 中的最大值，并删除它。
     计算删除两数的平均值。
     两数 a 和 b 的 平均值 为 (a + b) / 2 。

     比方说，2 和 3 的平均值是 (2 + 3) / 2 = 2.5 。
     返回上述过程能得到的 不同 平均值的数目。

     注意 ，如果最小值或者最大值有重复元素，可以删除任意一个。**/
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        HashSet<Integer> set = new HashSet<>();
        int avg = 0, lengh = nums.length;
        for (int i = 0; i < lengh / 2; i++) {
            avg = nums[i] + nums[lengh - i - 1];
            set.add(avg);
            avg = 0;
        }
        return set.size();
    }

    /**给你一个整数数组 arr ，请你删除最小 5% 的数字和最大 5% 的数字后，剩余数字的平均值。

     与 标准答案 误差在 10-5 的结果都被视为正确结果。**/
    public double trimMean(int[] arr) {
        double avg = 0.000000;
        int lengh = arr.length, a = (int) Math.ceil(lengh * 0.05);
        Arrays.sort(arr);
        for (int i = a; i < lengh - a; i++) {
            avg += arr[i];
        }
        return avg / (lengh * 0.9);
    }



    /**给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。

     对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。

     你可以返回 任何满足上述条件的数组作为答案 。**/
    public int[] sortArrayByParityII(int[] nums) {
        int lengh = nums.length;
        int[] ans = new int[lengh];
        int i = 0;
        for (int x :
                nums) {
            if (x % 2 == 0){
                ans[i] = x;
                i += 2;
            }
        }
        i = 1;
        for (int x :
                nums) {
            if (x % 2 == 1){
                ans[i] = x;
                i += 2;
            }
        }
        return ans;
    }


    /**给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。

     返回满足此条件的 任一数组 作为答案。**/
    public static int[] sortArrayByParity(int[] nums) {
        int lengh = nums.length;
        int i = 0, j = lengh - 1;
        int[] ans = new int[lengh];
        for (int k = 0; k < lengh; k++) {
                if (nums[k] % 2 == 0){
                    ans[i] = nums[k];
                    i++;
                }
                if (nums[k] % 2 == 1){
                    ans[j] = nums[k];
                    j--;
                }
            }
        return ans;
    }

    public int[] sortArrayByParity01(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++) {
            if (nums[i] % 2 == 1) {
                int c = nums[j];
                nums[j--] = nums[i];
                nums[i--] = c;
            }
        }
        return nums;
    }

    /**实现一个算法，确定一个字符串 s 的所有字符是否全都不同。**/
    public boolean isUnique(String astr) {
        HashSet<Character> set = new HashSet<>();
        char[] chars = astr.toCharArray();
        for (char x :
                chars) {
            if (!set.add(x)) return false;
        }
        return true;
    }

    public boolean isUnique01(String astr) {
        return astr.chars().distinct().count() == astr.length();
    }


    /**给定两个数组，arr1 和 arr2，
     arr2 中的元素各不相同
     arr2 中的每个元素都出现在 arr1 中
     对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。**/
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int index = 0;
        int[] hash = new int[1001];
        for (int x :
                arr1) {
            hash[x]++;
        }
        for (int x :
                arr2) {
            while (hash[x]-- > 0) arr1[index++] = x;
        }
        for (int i = 0; i < hash.length; i++) {
            while (hash[i]-- > 0) arr1[index++] = i;
        }
        return arr1;
    }

    /**数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。**/
    public int majorityElement02(int[] nums) {
        int ans = nums[0];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (index == 0) ans = nums[i];
            if (nums[i] == ans) index++;
            else index--;
        }
        return ans;
    }


    /**在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。**/
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x :
                nums) {
            if (!set.add(x)) return x;
        }
        return -1;
    }

    public int findRepeatNumber01(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }


    /**一家商店正在打折销售糖果。每购买 两个 糖果，商店会 免费 送一个糖果。

     免费送的糖果唯一的限制是：它的价格需要小于等于购买的两个糖果价格的 较小值 。

     比方说，总共有 4 个糖果，价格分别为 1 ，2 ，3 和 4 ，一位顾客买了价格为 2 和 3 的糖果，那么他可以免费获得价格为 1 的糖果，但不能获得价格为 4 的糖果。
     给你一个下标从 0 开始的整数数组 cost ，其中 cost[i] 表示第 i 个糖果的价格，请你返回获得 所有 糖果的 最小 总开销。**/
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int lengh = cost.length;
        int index = lengh - 1;
        int sum = 0;
        while (index >= 2){
            sum += cost[index] + cost[index - 1];
            index -= 3;
        }
        while (index >= 0){
            sum += cost[index--];
        }
        return sum;
    }


    /**给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。

     返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。

     子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。**/
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int count = 0,sum = 0;
            for (int j = 0; j < nums.length; j++) {
                sum += nums[j];
                if (sum <= queries[i]) count++;
            }
            ans[i] = count;
        }
        return ans;
    }

    //nums 的元素次序对结果无影响，因此我们对 nums 从小到大进行排序。
    // 显然和有限的最长子序列由最小的前几个数组成。使用数组 f 保存 nums 的前缀和，
    // 其中 f[i]表示前 i个元素之和（不包括 nums[i]）。
    // 遍历 queries，假设当前查询值为 q，
    // 使用二分查找获取满足 f[i]>q 的最小的 i，那么和小于等于 q 的最长子序列长度为 i−1。
    public int[] answerQueries01(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            f[i + 1] = f[i] + nums[i];
        }
        int[] answer = new int[m];
        for (int i = 0; i < m; i++) {
            answer[i] = binarySearch(f, queries[i]) - 1;
        }
        return answer;
    }

    public int binarySearch(int[] f, int target) {
        int low = 1, high = f.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (f[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    /**给你一个下标从 0 开始的整数数组 nums 。根据下述规则重排 nums 中的值：

     按 非递增 顺序排列 nums 奇数下标 上的所有值。
     举个例子，如果排序前 nums = [4,1,2,3] ，对奇数下标的值排序后变为 [4,3,2,1] 。奇数下标 1 和 3 的值按照非递增顺序重排。
     按 非递减 顺序排列 nums 偶数下标 上的所有值。
     举个例子，如果排序前 nums = [4,1,2,3] ，对偶数下标的值排序后变为 [2,1,4,3] 。偶数下标 0 和 2 的值按照非递减顺序重排。
     返回重排 nums 的值之后形成的数组。**/
    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length;
        int[] odd = new int[101], even = new int[101];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                even[nums[i]]++;
            } else {
                odd[nums[i]]++;
            }
        }
        for (int i = 0, index = 0; i <= 100; i++) {
            for (int j = 0; j < even[i]; j++) {
                nums[index] = i;
                index += 2;
            }
        }
        for (int i = 100, index = 1; i >= 0; i--) {
            for (int j = 0; j < odd[i]; j++) {
                nums[index] = i;
                index += 2;
            }
        }
        return nums;
    }



    /**给定两个由小写字母组成的字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。**/
    public boolean CheckPermutation(String s1, String s2) {
        int n = s1.length(), m = s2.length(), tot = 0;
        if (n != m) return false;
        int[] cnts = new int[256];
        for (int i = 0; i < n; i++) {
            if (++cnts[s1.charAt(i)] == 1) tot++;
            if (--cnts[s2.charAt(i)] == 0) tot--;
        }
        return tot == 0;
    }

    public boolean CheckPermutation01(String s1, String s2) {
        return s1.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).equals(
                s2.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        );
    }



    /**给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。

     请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。

     如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。

     军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。**/
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] ans = new int[k];
        int[] list = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            list[i] = count(mat[i]) * 100 + i;
        }
        Arrays.sort(list);
        for (int i = 0; i < k; i++) {
            ans[i] = list[i] % 100;
        }
        return ans;
    }

    private int count(int[] ints) {
        int sum = 0;
        for (int n :
                ints) {
            if (n != 1) break;
            sum += n;
        }
        return sum;
    }


    /**给定四个整数 rows ,   cols ,  rCenter 和 cCenter 。有一个 rows x cols 的矩阵，你在单元格上的坐标是 (rCenter, cCenter) 。

     返回矩阵中的所有单元格的坐标，并按与 (rCenter, cCenter) 的 距离 从最小到最大的顺序排。你可以按 任何 满足此条件的顺序返回答案。

     单元格(r1, c1) 和 (r2, c2) 之间的距离为|r1 - r2| + |c1 - c2|。**/
    int[] dr = {1, 1, -1, -1};
    int[] dc = {1, -1, -1, 1};

    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int maxDist = Math.max(rCenter, rows - 1 - rCenter) + Math.max(cCenter, cols - 1 - cCenter);
        int[][] ret = new int[rows * cols][];
        int row = rCenter, col = cCenter;
        int index = 0;
        ret[index++] = new int[]{row, col};
        for (int dist = 1; dist <= maxDist; dist++) {
            row--;
            for (int i = 0; i < 4; i++) {
                while ((i % 2 == 0 && row != rCenter) || (i % 2 != 0 && col != cCenter)) {
                    if (row >= 0 && row < rows && col >= 0 && col < cols) {
                        ret[index++] = new int[]{row, col};
                    }
                    row += dr[i];
                    col += dc[i];
                }
            }
        }
        return ret;
    }


    public int[][] allCellsDistOrder01(int rows, int cols, int rCenter, int cCenter) {
        int maxDist = Math.max(rCenter, rows - 1 - rCenter) + Math.max(cCenter, cols - 1 - cCenter);
        List<List<int[]>> bucket = new ArrayList<List<int[]>>();
        for (int i = 0; i <= maxDist; i++) {
            bucket.add(new ArrayList<int[]>());
        }

        // 首先存储矩阵内所有的点，然后将其按照哈曼顿距离直接排序
        // 在枚举所有点时，可以直接按照哈曼顿距离分桶。这样就可以实现线性的桶排序。
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int d = dist(i, j, rCenter, cCenter);
                bucket.get(d).add(new int[]{i, j});
            }
        }
        int[][] ret = new int[rows * cols][];
        int index = 0;
        for (int i = 0; i <= maxDist; i++) {
            for (int[] it : bucket.get(i)) {
                ret[index++] = it;
            }
        }
        return ret;
    }

    public int dist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }


    /**输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。**/
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }





    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] a = sortArrayByParity(ints);
        for (int x :
                a) {
            System.out.println(x);
        }
    }


}
