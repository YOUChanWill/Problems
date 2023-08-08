package day8;

import java.util.*;

public class Solution {

    /**矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。
     * 例如，矩阵 mat 有 6 行 3 列，从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。

     给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。**/
    public int[][] diagonalSort(int[][] mat) {
        // 行数
        int m = mat.length;
        // 列数
        int n = mat[0].length;
        // 主对角线的条数
        int dLen = m + n - 1;

        // 每一条对角线都创建一个动态数组
        ArrayList<Integer>[] diagonal = new ArrayList[dLen];
        for (int i = 0; i < dLen; i++) {
            diagonal[i] = new ArrayList<>(m);
        }

        // 遍历原始矩阵，把原始矩阵中的元素放进对应的动态数组中
        // 主对角线上元素的特点是：纵坐标 - 横坐标 = 定值
        // 加上偏移 m - 1 是为了能够放进数组中
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diagonal[j - i + (m - 1)].add(mat[i][j]);
            }
        }

        // 对每一个对角线上的动态数组分别进行升序排序
        for (int i = 0; i < dLen; i++) {
            Collections.sort(diagonal[i]);
        }

        int[][] res = new int[m][n];

        // 对角线数组上还未取出的元素的下标，初始化的时候均为 0
        int[] next = new int[dLen];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 对角线的坐标
                int index = j - i + (m - 1);
                // 记录结果
                res[i][j] = diagonal[index].get(next[index]);
                // 维护 next 数组的值
                next[index]++;
            }
        }
        return res;
    }

    public int[][] diagonalSort01(int[][] matrix) {

        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int[][] sortedMatrix = new int[numRows][numCols];
        int[] diagonal = new int[Math.max(numRows, numCols)];
        for(int i = 1 - numCols; i < numRows; i++) {
            int startRow;
            int startCol;
            if(i < 0) {
                startRow = 0;
                startCol = -i;
            } else {
                startRow = i;
                startCol = 0;
            }
            int len = 0;
            for(int row = startRow, col = startCol; row < numRows && col < numCols; row++, col++) {
                diagonal[len++] = matrix[row][col];
            }
            Arrays.sort(diagonal, 0, len);
            for(int j = 0, row = startRow, col = startCol; j < len; j++, row++, col++) {
                sortedMatrix[row][col] = diagonal[j];
            }
        }

        return sortedMatrix;
    }


    public int[][] diagonalSort02(int[][] mat) {
        int[][] res = new int[mat.length][mat[0].length];
        if (mat.length < 1 || mat[0].length < 1) {
            return res;
        }

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for (int i = 0; i < mat.length; i++ ) {
            for (int j = 0; j < mat[0].length; j++) {
                map.putIfAbsent(i-j, new PriorityQueue<>());
                map.get(i-j).add(mat[i][j]);
            }
        }

        for (int i = 0; i < mat.length; i++ ) {
            for (int j = 0; j < mat[0].length; j++) {
                res[i][j] = map.get(i-j).poll();
            }
        }

        return res;
    }


    /**Alice 管理着一家公司，并租用大楼的部分楼层作为办公空间。Alice 决定将一些楼层作为 特殊楼层 ，仅用于放松。

     给你两个整数 bottom 和 top ，表示 Alice 租用了从 bottom 到 top（含 bottom 和 top 在内）的所有楼层。
     另给你一个整数数组 special ，其中 special[i] 表示  Alice 指定用于放松的特殊楼层。

     返回不含特殊楼层的 最大 连续楼层数。**/
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = 0, n = special.length;
        for (int i = 1; i < special.length; i++) {
            max = Math.max(special[i] - special[i - 1] - 1,max);
        }
        return Math.max(max,Math.max(top - special[special.length - 1], special[0] - bottom));
    }

    public int maxConsecutive01(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = special[0] - bottom;
        for (int i = 1; i < special.length; i++) {
            int floor = special[i] - special[i - 1] - 1;
            if (floor > max) {
                max = floor;
            }
        }
        int last = special[special.length - 1];
        if (top - last > max) {
            max = top - last;
        }
        return max;
    }





}
