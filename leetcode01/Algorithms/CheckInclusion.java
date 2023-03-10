package Algorithms;

import java.util.Arrays;
import java.util.HashSet;

public class CheckInclusion {

//    public boolean checkInclusion(String s1, String s2) {
//        HashSet hashSet = new HashSet();
//        hashSet.add(s1);
//        for (int i = 0 , j = s1.length(); j < s2.length(); i++,j++) {
//                String str = s2.substring(i,j);
//                if (!hashSet.add(str)){
//                    return true;
//            }
//        }
//        return false;
//    }


    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int m = s2.length(), n = s1.length();
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            cnt2[s2.charAt(i) - 'a']++;
            cnt2[s2.charAt(i - n) - 'a']--;
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }
}
