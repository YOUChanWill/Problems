package BinarySearch;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution {


    /**给你一个满足下述两条属性的 m x n 整数矩阵：

     每行中的整数从左到右按非递减顺序排列。
     每行的第一个整数大于前一行的最后一个整数。
     给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。**/
    public boolean searchMatrix(int[][] mat, int t) {
        int m = mat.length, n = mat[0].length;

        // 第一次二分：定位到所在行（从上往下，找到最后一个满足 mat[x]][0] <= t 的行号）
        int l = 0, r = m - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (mat[mid][0] <= t) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        int row = r;
        if (mat[row][0] == t) return true;
        if (mat[row][0] > t) return false;

        // 第二次二分：从所在行中定位到列（从左到右，找到最后一个满足 mat[row][x] <= t 的列号）
        l = 0; r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (mat[row][mid] <= t) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        int col = r;

        return mat[row][col] == t;
    }


    /**给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

     如果数组中不存在目标值 target，返回 [-1, -1]。

     你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。**/
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**整数数组 nums 按升序排列，数组中的值 互不相同 。

     在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

     给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

     你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。**/
    public int search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0) return -1;
        int left = 0, right = len - 1;
        // 1. 旋转数组后，从中间划分，一定有一边是有序的。
        // 2. 由于一定有一边是有序的，所以根据有序的两个边界值来判断目标值在有序一边还是无序一边
        // 3. 这题找目标值，遇到目标值即返回
        // 4. 注意：由于有序的一边的边界值可能等于目标值，所以判断目标值是否在有序的那边时应该加个等号
        // (在二分查找某个具体值得时候如果把握不好边界值，可以再每次查找前判断下边界值，也就是while循环里面的两个if注释)
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            // 右边有序
            if(nums[mid] < nums[right]){
                // 目标值在右边
                if(target > nums[mid] && target <= nums[right]){
                    left = mid + 1;
                    // 目标值在左边
                }else{
                    right = mid - 1;
                }
                // 左边有序
            }else{
                // 目标值在左边
                if(target >= nums[left] && target < nums[mid]){
                    right = mid - 1;
                    // 目标值在右边
                }else{
                    left = mid + 1;
                }
            }
        }
        return -1;
    }



    /**已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。

     给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。

     你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。**/
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }


    /**给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

     算法的时间复杂度应该为 O(log (m+n)) 。**/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }



    /**中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。

     例如 arr = [2,3,4] 的中位数是 3 。
     例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
     实现 MedianFinder 类:

     MedianFinder() 初始化 MedianFinder 对象。

     void addNum(int num) 将数据流中的整数 num 添加到数据结构中。

     double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。**/
    class MedianFinder {
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        private boolean even = true;
        public void addNum(int num) {
            if (even) {
                minHeap.offer(num);
                maxHeap.offer(minHeap.poll());
            } else {
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
            }
            even = !even;
        }

        public double findMedian() {
            if (even) {
                return (minHeap.peek() + maxHeap.peek())/2.0;
            } else {
                return maxHeap.peek();
            }
        }
    }
}
