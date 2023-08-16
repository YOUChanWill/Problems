package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class generateParenthesis {

    /**数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。**/
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }
        dfs("", 0, 0, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经用了几个
     * @param right  右括号已经用了几个
     * @param n      左括号、右括号一共得用几个
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs(curStr + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs(curStr + ")", left, right + 1, n, res);
        }
    }

    List<String> ret=new ArrayList<>();
    int l=0;
    int r=0;
    StringBuilder sb = new StringBuilder();
    public List<String> generateParenthesis01(int n) {
        dfs(n);
        return ret;
    }
    void dfs(int n){
        if(l==n&&r==n){
            ret.add(sb.toString());
            return;
        }
        if(l<n){
            //添加左括号
            l+=1;
            sb.append("(");
            dfs(n);
            l-=1;
            sb.deleteCharAt(sb.length()-1);
        }
        if(r<n&&r<l){
            //添加有括号
            r+=1;
            sb.append(")");
            dfs(n);
            r-=1;
            sb.deleteCharAt(sb.length()-1);
        }
    }

}
