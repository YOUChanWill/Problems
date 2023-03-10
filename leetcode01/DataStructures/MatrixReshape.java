package DataStructures;

public class MatrixReshape {


    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if(mat.length != 0 && mat.length * mat[0].length != r * c){
            return mat;
        }// 判断是否可以转换为新的数组
        int n = 0;// 定义行
        int m = 0;// 定义列

        int[][] ans = new int[r][c];
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[i].length; j++){
                if(n == c){ // 超过边界值，换行
                    m++;
                    n = 0;
                }
                ans[m][n] = mat[i][j];
                n++;
            }
        }
        return ans;
    }
}
