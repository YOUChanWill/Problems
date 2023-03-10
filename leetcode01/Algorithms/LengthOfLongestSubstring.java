package Algorithms;

import java.util.HashSet;

public class LengthOfLongestSubstring {

//    public int lengthOfLongestSubstring(String s) {
//        HashSet<Object> hashSet = new HashSet<>();
//        int count = 0;
//        int max = 0;
//        for (int i = 0; i < s.length();) {
//            int cur = s.charAt(i);
//            if (hashSet.add(cur)){
//                count++;
//                i++;
//            }else if (!hashSet.add(cur)){
//                hashSet.clear();
//                count = 0;
//                if (s.charAt(i - 1) == s.charAt(i)){
//                    hashSet.add(cur);
//                }
//                i++;
//            }
//            max = Math.max(count,max);
//        }
//        return max;
//    }

    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> hashSet = new HashSet<>();
        int left = 0, right = 0;
        int max = 0;
        for (right = 0; right < s.length();right++) {
            while (left <= right && !hashSet.add(s.charAt(right))){
                hashSet.remove(s.charAt(left));
                left++;
            }
            max = Math.max(right - left + 1,max);
        }
        return max;
    }


}
