package ProgramLearn;

import java.util.HashMap;
import java.util.HashSet;

public class AreAlmostEqual {

    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) return true;

        HashMap<Character, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            char sc1 = s1.charAt(i);
            char sc2 = s2.charAt(i);
            if (sc1 != sc2){
                count++;
            }
            map.put(sc1, map.getOrDefault(sc1,0) + 1);
            map.put(sc2, map.getOrDefault(sc2,0) - 1);
            if (count > 2) return false;
        }
        for (int val :
                map.values()) {
            if (val != 0) return false;
        }
        return true;
    }
}
