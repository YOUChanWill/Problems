package ProgramLearn;

public class NearestValidPoint {

    public int nearestValidPoint(int x, int y, int[][] points) {
        int n = points.length; // 数组长度

        int best = Integer.MAX_VALUE;
        int bestid = -1;
        for (int i = 0; i < n; i++) {
            int px = points[i][0];
            int py = points[i][1];
            if (x == px){
                int dist = Math.abs(y - py);
                if (dist < best){
                    best = dist;
                    bestid = i;
                }
            }else if (y == py){
                int dist = Math.abs(x - px);
                if (dist < best){
                    best = dist;
                    bestid = i;
                }
            }
        }
        return bestid;
    }

}
