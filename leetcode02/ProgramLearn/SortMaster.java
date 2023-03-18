package ProgramLearn;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class SortMaster {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] sortlist = new int[len];

        Map<Integer, Integer> map = new Map<>();

        for (int j = 0; j < len; j++) {
            int num = scanner.nextInt();
            sortlist[j] = num;
        }


    }
}
