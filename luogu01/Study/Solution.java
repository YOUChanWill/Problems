package Study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        List<Integer> arrayList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int sum = 0;

        for (int i = 0; i < n; ++i) {
            int o = scanner.nextInt();
            arrayList.add(o);
        }

        for (int i = 0; i < m; ++i) {
            int x = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (x == 1){
                arrayList.set(a - 1,(arrayList.get(a - 1) + b));
            }
            if (x == 2){
                for (int j = a - 1; j < b; j++) {
                    sum += arrayList.get(j);
                }
                System.out.println(sum);
                sum = 0;
            }
        }

    }


}
