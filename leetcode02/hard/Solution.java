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
}
