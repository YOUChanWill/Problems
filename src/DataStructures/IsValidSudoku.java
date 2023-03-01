package DataStructures;

import java.util.HashSet;

public class IsValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9]; // 新建行
        int[][] columns = new int[9][9]; // 新建列
        int[][][] subboxes = new int[3][3][9]; // 新建3*3的格子
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.'){
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
