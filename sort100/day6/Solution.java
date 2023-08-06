package day6;

import java.util.*;

public class Solution {

    /**给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。**/
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    public int maximumProduct01(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    /**给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：

     选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
     重复这个过程恰好 k 次。可以多次选择同一个下标 i 。

     以这种方式修改数组后，返回数组 可能的最大和 。**/
    public static int largestSumAfterKNegations(int[] nums, int k) {
        // 排序，把可能有的负数排到前面
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 贪心：如果是负数，而k还有盈余，就把负数反过来
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
            sum += nums[i];
        }
        Arrays.sort(nums);
        // 如果k没剩，那说明能转的负数都转正了，已经是最大和，返回sum
        // 如果k有剩，说明负数已经全部转正，所以如果k还剩偶数个就自己抵消掉，不用删减，如果k还剩奇数个就减掉2倍最小正数。
        return sum - (k % 2 == 0 ? 0 : 2 * nums[0]);
    }


    /**给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。**/
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, third = nums[n -1], count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < third) {
                third = nums[i];
                count++;
                if (count == 2) return nums[i];
            }
        }
        return nums[n - 1];
    }

    public int thirdMax01(int[] nums) {
        TreeSet<Integer> s = new TreeSet<Integer>();
        for (int num : nums) {
            s.add(num);
            if (s.size() > 3) {
                s.remove(s.first());
            }
        }
        return s.size() == 3 ? s.first() : s.last();
    }

    // 遍历数组，并用三个变量 aaa、bbb 和 ccc 来维护数组中的最大值、次大值和第三大值
    public int thirdMax02(int[] nums) {
        long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;
        for (long num : nums) {
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && num > b) {
                c = b;
                b = num;
            } else if (b > num && num > c) {
                c = num;
            }
        }
        return c == Long.MIN_VALUE ? (int) a : (int) c;
    }


    /**给你一个整数数组 nums 和一个整数 k 。你需要找到 nums 中长度为 k 的 子序列 ，且这个子序列的 和最大 。

     请你返回 任意 一个长度为 k 的整数子序列。

     子序列 定义为从一个数组里删除一些元素后，不改变剩下元素的顺序得到的数组。**/
    public int[] maxSubsequence(int[] nums, int k) {
        int len = nums.length;
        // idxMap：辅助数组，用来存储数值和索引
        int idxMap[][] = new int[len][2];
        for(int idx = 0; idx < len; idx++){
            idxMap[idx][1] = nums[idx];
            idxMap[idx][0] = idx;
        }
        // 按照数值nums[idx]从大到小排序
        Arrays.sort(idxMap, (a, b) -> b[1] - a[1]);
        // 按照索引idx从小到大进行排列
        Arrays.sort(idxMap, 0, k, (a, b) -> a[0] - b[0]);

        // 复制结果
        int[] res = new int[k];
        for(int idx = 0; idx < k; idx++){
            res[idx] = idxMap[idx][1];
        }
        return res;
    }

    public int[] maxSubsequence01(int[] nums, int k)
    {
        int n = nums.length;
        int [] nums2 = new int [n];
        System.arraycopy(nums, 0, nums2, 0, n);
        Arrays.sort(nums2);

        Map<Integer, Integer> num_freq = new HashMap<>();
        for (int i = n - 1; i > n - k - 1; i --)
        {
            num_freq.put(nums2[i], num_freq.getOrDefault(nums2[i], 0) + 1);
        }

        int [] res = new int [k];
        int ri = 0;
        for (int x : nums)
        {
            if (num_freq.getOrDefault(x, 0) > 0)
            {
                res[ri ++] = x;
                num_freq.put(x, num_freq.get(x) - 1);
            }
        }
        return res;
    }

    public int[] maxSubsequence02(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            list.add(nums[i]);
            pq.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > pq.peek()) {
                list.remove(pq.poll());
                list.add(nums[i]);
                pq.add(nums[i]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }


    /**给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。

     请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。**/
    public int dominantIndex(int[] nums) {
        int[] sort = nums.clone();
        Arrays.sort(sort);
        int index = -1, n = nums.length, max = 0;
        if (sort[n - 1] >= sort[n - 2] * 2) max = sort[n - 1];
        else return index;
        for (int i = 0; i < n; i++) {
            if (nums[i] == max) index = i;
        }
        return index;
    }

    public int dominantIndex01(int[] nums) {
        int m1 = -1, m2 = -1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > m1) {
                m2 = m1;
                m1 = nums[i];
                index = i;
            } else if (nums[i] > m2) {
                m2 = nums[i];
            }
        }
        return m1 >= m2 * 2 ? index : -1;
    }

    /**集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。

     给定一个数组 nums 代表了集合 S 发生错误后的结果。

     请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。**/
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        HashSet<Integer> set = new HashSet<>();
        for (int x :
                nums) {
            if (!set.add(x)) {
                ans[0] = x;
            }
        }
        int n = nums.length;
        for (int i = 1; i <= n; i++) {
            if (set.add(i)) ans[1] = i;
        }
        return ans;
    }

    public int[] findErrorNums01(int[] nums) {
        int dup = -1, miss = -1;
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] < 0) {
                dup = idx + 1;
            }
            nums[idx] = -nums[idx];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && i + 1 != dup) {
                miss = i + 1;
                break;
            }
        }
        return new int[]{dup, miss};
    }

    // 重复的数字在数组中出现 2次，丢失的数字在数组中出现 0 次，其余的每个数字在数组中出现 1 次。
    // 重复的数字和丢失的数字的出现次数的奇偶性相同
    public int[] findErrorNums02(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        int lowbit = xor & (-xor);
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & lowbit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & lowbit) == 0) {
                num1 ^= i;
            } else {
                num2 ^= i;
            }
        }
        for (int num : nums) {
            if (num == num1) {
                return new int[]{num1, num2};
            }
        }
        return new int[]{num2, num1};
    }

    public int[] findErrorNums03(int[] nums) {
        int[] ans = new int[2];
        int[] hash = new int[nums.length + 1];
        for (int num : nums) {
            if (++hash[num] == 2) {
                ans[0] = num;
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (hash[i] == 0) {
                ans[1] = i;
            }
        }
        return ans;
    }

    /**给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。*/
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        // 统计出现的频率
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        // 使用优先队列来维护前k个元素
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        int i = 0;

        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll().getKey();
        }

        return result;
    }


    public int[] topKFrequent01(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if (min == max) {
            return new int[]{nums[0]};
        }
        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            ++counts[num - min];
        }
        int maxCount = nums.length;
        List<Integer>[] countLists = new List[maxCount + 1];
        for (int i = max - min; i >= 0; i--) {
            int count = counts[i];
            if (count > 0) {
                if (countLists[count] == null) {
                    countLists[count] = new ArrayList();
                }
                countLists[count].add(i + min);
            }
        }
        int[] topK = new int[k];
        for (int i = maxCount; i > 0 && k > 0; i--) {
            List<Integer> list = countLists[i];
            if (list != null) {
                for (int j = list.size() - 1; j >= 0 && k > 0; j--) {
                    topK[--k] = list.remove(j);
                }
            }
        }
        return topK;
    }

}
