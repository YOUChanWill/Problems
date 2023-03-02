package DataStructures;

import java.util.HashSet;

public class FirstUniqChar {

//  给定一个字符串 s ，找到它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1

    public int firstUniqChar(String s) {
        HashSet hashSet = new HashSet();
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!hashSet.add(s.charAt(i))) {
                index = i;
            }
        }
    }
}
