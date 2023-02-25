package Day;

public class minimumSwap {
    public static void main(String[] args) {
        String s1 = "xxxxyxyxyxyxyxxx";
        String s2 = "yyyyxyxyxyxyxyyy";
        minimumSwap swap = new minimumSwap();
        System.out.println(swap.minimumSwap(s1, s2));
    }

    public int minimumSwap(String s1, String s2){
        int xy = 0,yx = 0;
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            char a = s1.charAt(i),b = s2.charAt(i);
            if (a == 'x' && b == 'y'){
                xy++;
            }
            if (a == 'y' && b == 'x'){
                yx++;
            }
        }
        if ((xy + yx) % 2 == 1 ){
            return -1;
        }
        return xy / 2 + yx / 2 + xy % 2 + yx % 2;
    }
}
