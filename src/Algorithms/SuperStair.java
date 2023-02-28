package Algorithms;

public class SuperStair {


    public static int superstair(int m){
        if (m == 0 || m == 1) return m;
        return superstair(m - 1) + superstair(m - 2);

    }

    public static void main(String[] args) {
        System.out.println(superstair(4));
    }
}
