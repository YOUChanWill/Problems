package ProgramLearn;

import java.util.HashSet;

public class IsHappy {

    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        int now = 0, old = n;
        while (true){
            now = sum(old);
            if (hashSet.contains(now)) return false;else hashSet.add(now);
            if (hashSet.contains(1)) return true;
            old = now;
        }
    }

    public boolean isHappy01(int n){
        while (n > 4){
            n = sum(n);
        }
        return n == 1;
    }


    public int sum(int n){
        int res = 0;
        while (n != 0){
            res += (n % 10) * (n % 10);
            n = (n - (n % 10)) / 10;
        }
        return res;
    }
}
