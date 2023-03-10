package ProgramLearn;

import java.util.Arrays;

public class CanMakeArithmeticProgression {

    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int sub = arr[1] - arr[0];
        for (int i = 1; i <arr.length - 1; i++) {
            if ((arr[i + 1] - arr[i]) != sub){
                return false;
            }
        }
        return true;
    }
}
