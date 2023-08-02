package ProgramLearn;

public class DiagonalSum {

    public int diagonalSum(int[][] mat) {
        int len = mat.length, sum = 0, mid = len /2;
        for (int i = 0; i < len; i++) {
            sum += mat[i][i] + mat[i][len - 1 - i];
        }
        return sum - mat[mid][mid] * (len & 1); // len&1用于判断奇偶
    }
}
