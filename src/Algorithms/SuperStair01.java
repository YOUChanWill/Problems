package Algorithms;

public class SuperStair01 {
    public static int superstair(int m){

        if(m==2){
            return 1;

        } else if (m==1){
            return 1;
        }
        return superstair(m-1)+superstair(m-2);

    }

    public static void main(String[] args) {
        System.out.println(superstair(5));
    }
}
