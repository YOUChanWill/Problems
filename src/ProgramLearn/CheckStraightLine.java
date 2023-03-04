package ProgramLearn;

public class CheckStraightLine {

    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        int x0 = coordinates[0][0], y0 = coordinates[0][1]; // 第一个点的横坐标和纵坐标

        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= x0;
            coordinates[i][1] -= y0;
        }

        // 使用 a * x + b * y = 0
        int a = coordinates[1][1], b = -coordinates[1][0];

        for (int i = 2; i < n; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (a * x + b * y != 0) return false;
        }
        return true;
    }
}
