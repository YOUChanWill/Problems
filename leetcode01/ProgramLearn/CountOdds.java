package ProgramLearn;

import java.util.ArrayList;
import java.util.List;

public class CountOdds {

        public int countOdds(int low, int high) {
        if (Odds(low)){
            low--;
        }
        if (Odds(high)){
            high++;
        }

        return (high - low) / 2;
        }

    public boolean Odds(int num){
        if (num%2 == 1){
            return true;
        }else return false;
    }
}


//    public int countOdds(int low, int high) {
//        int count = 0;
//        for (int i = 0; i <= high - low; i++) {
//            if (Odds(low + i)){
//                count++;
//            }
//        }
//        return count;
//    }
//

