package ProgramLearn;

import java.util.Scanner;

public class Laoren {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int count = 0;
        for (int j = 0; j < i; j++) {
            int i1 = scanner.nextInt();
            int res = (int) Math.ceil(Math.sqrt(i1) * 10);
            if (res>= 60) count++;
            System.out.println(res);
        }
        System.out.println(count);
    }
}
