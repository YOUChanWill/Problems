package DataStructures;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class IsAnagram {

// 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

    public boolean isAnagram(String s, String t) {
        int count1 = 0;
        int slen = s.length();
        int tlen = t.length();
        if (slen != tlen){
            return false;
        }
        int[] s1 = new int[26];
        int[] s2 = new int[26];
        for (int i = 0; i < slen; i++) {
            int c1 = s.charAt(i) - 'a';
            s1[c1]++;
            int c2 = t.charAt(i) - 'a';
            s2[c2]++;
        }
        for (int i = 0; i < 26; i++) {
            if (s1[i] == s2[i]){
                count1++;
            }
        }
        if (count1 == 26){
            return true;
        }else return false;
    }
}
