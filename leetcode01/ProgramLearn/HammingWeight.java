package ProgramLearn;

public class HammingWeight {

//    public int hammingWeight(int n) {
//        int count = 0;
//        while (n != 0){
//            count+=(n&1);
//            n>>>=1;
//        }
//        return count;
//    }

    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    public static int bitCount(int i) {
        // HD, Figure 5-2
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }
}
