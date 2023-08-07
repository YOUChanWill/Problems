package day7;

import java.util.*;

public class Solution {

    /**给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

     请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。**/
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest01(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public int findKthLargest02(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    // 快速排序
    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        Random random = new Random();
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }


    // 建立大根堆
    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    // 交换函数
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。**/
    public int[] smallestK(int[] arr, int k) {
        int[] ans = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 使用快速排序
    public int[] smallestK01(int[] arr, int k) {
        quickSelect(arr, 0, arr.length - 1, k);
        int[] vec = new int[k];
        for (int i = 0; i < k; ++i) {
            vec[i] = arr[i];
        }
        return vec;
    }

    /**给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最小操作数。

     在一次操作中，你可以使数组中的一个元素加 1 或者减 1 。**/
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int lengh = nums.length, sum = 0, mid = nums[(lengh - 1) / 2];
        for (int x :
                nums) {
            sum += Math.abs(mid - x);
        }
        return sum;
    }

    public int minMoves201(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        int minMoves = 0;
        while (i < j) {
            minMoves += nums[j] - nums[i];
            i++;
            j--;
        }
        return minMoves;
    }

    /**给你一个整数 num 。重排 num 中的各位数字，使其值 最小化 且不含 任何 前导零。

     返回不含前导零且值最小的重排数字。

     注意，重排各位数字后，num 的符号不会改变。**/
    public long smallestNumber(long num) {
        
    }


    /**Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。

     给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌上的数值。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。**/
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int lengh = hand.length;
        if (lengh % groupSize != 0) return false;
        if (lengh == 1) return true;

        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.sort(hand);// 对数组进行排序
        for (int x :
                hand) {
            // 统计每个元素的个数
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        // 从排好序的数组中取出元素与哈希表进行判断
        for (int x :
                hand) {
            if (!map.containsKey(x)) continue;

            for (int i = 0; i < groupSize; i++) {
                // 因为排好序，所以当前一定是可以构成顺子中最小的一张
                int num = x + i;
                if (!map.containsKey(num)) return false;
                map.put(num,map.get(num) - 1);
                // 如果该元素用完了，则从哈希表中删去
                if (map.get(num) == 0) map.remove(num);
            }
        }
        return true;
    }

    public boolean isNStraightHand01(int[] hand, int groupSize) {
        if (groupSize == 1) return true;
        int len = hand.length, left = 0, right = 0;
        if (len % groupSize != 0) return false;
        Arrays.sort(hand);
        while (left < len) {
            int group = groupSize, temp = hand[left];
            right = left + 1;
            while (group > 1) {
                while (right < len && (hand[right] == temp || right == 0 || hand[right] == 0)) right++;
                if (right >= len || hand[right] - temp != 1) return false;
                else {
                    temp = hand[right];
                    hand[right] = 0;
                    right++;
                    group--;
                }
            }
            left++;
            while (left < right && hand[left] == 0) left++;
        }
        return true;
    }

    public boolean isNStraightHand02(int[] hand, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->a-b);
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            q.add(i);
        }
        while (!q.isEmpty()) {
            int t = q.poll();
            if (map.get(t) == 0) continue;
            for (int i = 0; i < m; i++) {
                int cnt = map.getOrDefault(t + i, 0);
                if (cnt == 0) return false;
                map.put(t + i, cnt - 1);
            }
        }
        return true;
    }



}
