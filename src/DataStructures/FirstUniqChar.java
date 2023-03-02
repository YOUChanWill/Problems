package DataStructures;

import java.util.Arrays;
import java.util.HashSet;

public class FirstUniqChar {

//  给定一个字符串 s ，找到它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1

    public int firstUniqChar(String s) {
        int n = s.length();
        int[] index = new int[26]; // 记录26个字母的出现的次数的数组
        Arrays.fill(index,-1); // 使用-1填充数组
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (index[c] != -2){
                index[c] = index[c] == -1 ? i : -2;
            }
        }
        int res = n;
        for (int i = 0; i < 26; i++) {
            if (index[i] != -1 && index[i] != -2){
                res = Math.min(res,index[i]);
            }
        }
        return res == n ? -1 : res;
    }
}
