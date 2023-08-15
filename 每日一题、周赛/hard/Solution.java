package hard;

public class Solution {


    /**在二维网格 grid 上，有 4 种类型的方格：

     1 表示起始方格。且只有一个起始方格。
     2 表示结束方格，且只有一个结束方格。
     0 表示我们可以走过的空方格。
     -1 表示我们无法跨越的障碍。
     返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。

     每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。**/
    public int uniquePathsIII(int[][] grid) {
        int startX = 0, startY = 0, stepNum = 1;  //当grid[i][j] == 2, stepNum++, 这里直接初始化为1
        //遍历获取起始位置和统计总步数
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    startY = i;
                    startX = j;
                    continue;
                }
                if (grid[i][j] == 0) stepNum++;
            }
        }

        return dfs(startX, startY, stepNum, grid);
    }

    public int dfs(int x, int y, int stepSur, int[][] grid){
        //排除越界的情况和遇到障碍的情况
        if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length || grid[y][x] == -1) return 0;
        if (grid[y][x] == 2) return stepSur == 0 ? 1 : 0;
        grid[y][x] = -1;  //已走过的标记为障碍
        int res = 0;
        res += dfs(x - 1, y, stepSur - 1, grid);
        res += dfs(x + 1, y, stepSur - 1, grid);
        res += dfs(x, y - 1, stepSur - 1, grid);
        res += dfs(x, y + 1, stepSur - 1, grid);
        grid[y][x] = 0;  //dfs遍历完该位置为起始位置的情况后，置零，以不影响后面的dfs
        return res;
    }


     /**给你一个 n x n 整数矩阵 grid ，请你返回 非零偏移下降路径 数字和的最小值。

      非零偏移下降路径 定义为：从 grid 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。**/
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] d = new int[n][n];
        // 令状态 f[i][j] 表示从数组 grid 的前 i 行中的每一行选择一个数字，
        // 并且第 i 行选择的数字为 grid[i][j] 时，可以得到的路径和最小值
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            d[0][i] = grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k) {
                        continue;
                    }
                    d[i][j] = Math.min(d[i][j], d[i - 1][k] + grid[i][j]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res, d[n - 1][j]);
        }
        return res;
    }

    public int minFallingPathSum01(int[][] grid) {
        int n = grid.length;
        int first_min_sum = 0;
        int second_min_sum = 0;
        int first_min_index = -1;

        for (int i = 0; i < n; i++) {
            int cur_first_min_sum = Integer.MAX_VALUE;
            int cur_second_min_sum = Integer.MAX_VALUE;
            int cur_first_min_index = -1;

            for (int j = 0; j < n; j++) {
                int cur_sum = (j != first_min_index ? first_min_sum : second_min_sum) + grid[i][j];
                if (cur_sum < cur_first_min_sum) {
                    cur_second_min_sum = cur_first_min_sum;
                    cur_first_min_sum = cur_sum;
                    cur_first_min_index = j;
                } else if (cur_sum < cur_second_min_sum) {
                    cur_second_min_sum = cur_sum;
                }
            }
            first_min_sum = cur_first_min_sum;
            second_min_sum = cur_second_min_sum;
            first_min_index = cur_first_min_index;
        }
        return first_min_sum;
    }

    public int minFallingPathSum02(int[][] grid) {

        int n = grid.length;
        int minSum1 = 0, minSum2 = 0, lastMinSumCol = -1;
        for(int i = 0; i < n; ++i){

            int curMinSum1 = Integer.MAX_VALUE, curMinSum2 = Integer.MAX_VALUE, curMinSumCol = -1;

            for(int j = 0; j < n; ++j){
                int curMinSum =(j == lastMinSumCol? minSum2:minSum1) + grid[i][j];

                if(curMinSum < curMinSum1){
                    curMinSum2 = curMinSum1;
                    curMinSum1 = curMinSum;
                    curMinSumCol = j;
                }else if(curMinSum < curMinSum2)
                    curMinSum2 = curMinSum;


            }
            minSum1 = curMinSum1;
            minSum2 = curMinSum2;
            lastMinSumCol = curMinSumCol;

        }
        return minSum1;
    }


}
