package ProgramLearn;

public class MaximumWealth {

    public int maximumWealth(int[][] accounts) {
        int lenY = accounts.length;
        int lenX = accounts[0].length;
        int ans = 0, max = 0;
        for (int i = 0; i < lenY; i++) {
            for (int j = 0; j < lenX; j++) {
                max += accounts[i][j];
            }
            if (max > ans){
                ans = max;
            }
            max = 0;
        }
        return ans;
    }
}
