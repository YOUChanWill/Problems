package Array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {


    /**给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。

     题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。

     请不要使用除法，且在 O(n) 时间复杂度内完成此题。**/
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;

        // L 和 R 分别表示左右两侧的乘积列表
        int[] L = new int[length];
        int[] R = new int[length];

        int[] answer = new int[length];

        // L[i] 为索引 i 左侧所有元素的乘积
        // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }

        // R[i] 为索引 i 右侧所有元素的乘积
        // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }

        // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
        for (int i = 0; i < length; i++) {
            answer[i] = L[i] * R[i];
        }

        return answer;
    }

    public int[] productExceptSelf01(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];

        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }
        return answer;
    }



    /**给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

     请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。**/
    public int firstMissingPositive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int x :
                nums) {
            map.put(x, 1);
            max = Math.max(max,x);
        }
        Arrays.sort(nums);
        for (int i = 1; i < max; i++) {
            if (!map.containsKey(i)) return i;
        }
        return max > 0 ? max + 1 : 1;
    }

    public int firstMissingPositive01(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public int firstMissingPositive02(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }



    /**给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。**/
    public List<Integer> spiralOrder(int[][] mat) {
        int INF = 101;
        List<Integer> ans = new ArrayList<>();
        int m = mat.length, n = mat[0].length;
        // 定义四个方向
        int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        for (int x = 0, y = 0, d = 0, i = 0; i < m * n; i++) {
            ans.add(mat[x][y]);
            mat[x][y] = INF;
            // 下一步要到达的位置
            int nx = x + dirs[d][0], ny = y + dirs[d][1];
            // 如果下一步发生「溢出」或者已经访问过（说明四个方向已经走过一次）
            if (nx < 0 || nx >= m || ny < 0 || ny >= n || mat[nx][ny] == INF) {
                d = (d + 1) % 4;
                nx = x + dirs[d][0]; ny = y + dirs[d][1];
            }
            x = nx; y = ny;
        }
        return ans;
    }

    public List<Integer> spiralOrder01(int[][] mat) {
        List<Integer> ans = new ArrayList<>();
        int m = mat.length, n = mat[0].length;
        circle(mat, 0, 0, m - 1, n - 1, ans);
        return ans;
    }
    // 遍历 以 (x1, y1) 作为左上角，(x2, y2) 作为右下角形成的「圈」
    void circle(int[][] mat, int x1, int y1, int x2, int y2, List<Integer> ans) {
        if (x2 < x1 || y2 < y1) return;
        // 只有一行时，按「行」遍历
        if (x1 == x2) {
            for (int i = y1; i <= y2; i++) ans.add(mat[x1][i]);
            return;
        }
        // 只有一列时，按「列」遍历
        if (y1 == y2) {
            for (int i = x1; i <= x2; i++) ans.add(mat[i][y2]);
            return;
        }
        // 遍历当前「圈」
        for (int i = y1; i < y2; i++) ans.add(mat[x1][i]);
        for (int i = x1; i < x2; i++) ans.add(mat[i][y2]);
        for (int i = y2; i > y1; i--) ans.add(mat[x2][i]);
        for (int i = x2; i > x1; i--) ans.add(mat[i][y1]);
        // 往里收一圈，继续遍历
        circle(mat, x1 + 1, y1 + 1, x2 - 1, y2 - 1, ans);
    }

    /**给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。

     你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。**/
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }

    public void rotate01(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    public void rotate02(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

     每行的元素从左到右升序排列。
     每列的元素从上到下升序排列。**/
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int x : row) {
                if (target == x) return true;
            }
        }
        return false;
    }

    /**从矩阵 matrix 的右上角 (0,n−1) 进行搜索。
     * 在每一步的搜索过程中，如果我们位于位置 (x,y)，
     * 那么我们希望在以 matrix 的左下角为左下角、以 (x,y) 为右上角的矩阵中进行搜索，
     * 即行的范围为 [x,m−1]，列的范围为 [0,y]：
     **/
    public boolean searchMatrix01(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int r = m - 1, c = 0;
        while (r >= 0 && c < n) {
            int val = matrix[r][c];
            if (val == target) {
                return true;
            } else if (val > target) {
                r--;
            } else {
                c++;
            }
        }
        return false;
    }


    /**整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。

     例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

     例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     给你一个整数数组 nums ，找出 nums 的下一个排列。

     必须 原地 修改，只允许使用额外常数空间。**/
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}
