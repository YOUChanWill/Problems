package Algorithms;

public class SuperStair {


    public static int superstair(int m){
        if (m == 0 || m == 1) return m;
        return superstair(m - 1) + superstair(m - 2);

    }

    public static int climbStairs(int n){
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;

    }

    public static void main(String[] args) {
        System.out.println(superstair(4));
    }
}
