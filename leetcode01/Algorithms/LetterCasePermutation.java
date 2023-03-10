package Algorithms;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
    char[] cs;
    List<String> ans = new ArrayList<>();

    public List<String> letterCasePermutation(String s) {
        cs = s.toCharArray(); // 将字符串转为字符数组
        dfs(0,s.length(),new char[s.length()]);
        return ans;
    }

    private void dfs(int idx, int n, char[] cur){ // 深度搜索
        if (idx == n){
            ans.add(String.valueOf(cur));
            return;
        }
        cur[idx] = cs[idx];
        dfs(idx + 1, n, cur);
        if (Character.isLetter(cs[idx])){
            cur[idx] = (char) (cs[idx] ^ 32);
            dfs(idx + 1,n , cur);
        }
    }

}
