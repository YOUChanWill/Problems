package 字符串;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterString {

    /**剑指 Offer 05. 替换空格

     请实现一个函数，把字符串 s 中的每个空格替换成"%20"。**/
    public String replaceSpace(String s) {
//        return s.replaceAll(" ","%20");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                ans.append("%20");
                continue;
            }
            ans.append(s.charAt(i));
        }
        return ans.toString();
    }

    /**剑指 Offer 58 - II. 左旋转字符串

     字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
     请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。**/
    public String reverseLeftWords(String s, int n) {
        StringBuilder ans = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            ans.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            ans.append(s.charAt(i));
        }
        return ans.toString();
    }

    public String reverseLeftWords01(String s, int n) {
        return s.substring(n,s.length()) + s.substring(0,n);
    }

    public String reverseLeftWords02(String s, int n) {
        StringBuilder ans = new StringBuilder();
        for(int i = n; i < n + s.length(); i++){
            ans.append(s.charAt(i % s.length()));
        }
        return ans.toString();
    }


    /**剑指 Offer 20. 表示数值的字符串

     请实现一个函数用来判断字符串是否表示数值（包括整数和小数）.
     数值（按顺序）可以分成以下几个部分：若干空格、一个 小数 或者 整数、（可选）一个 'e' 或 'E' ，后面跟着一个 整数、若干空格

     小数（按顺序）可以分成以下几个部分：

     （可选）一个符号字符（'+' 或 '-'）
     下述格式之一：
     至少一位数字，后面跟着一个点 '.'
     至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
     一个点 '.' ，后面跟着至少一位数字

     整数（按顺序）可以分成以下几个部分：

     （可选）一个符号字符（'+' 或 '-'）
     至少一位数字

     部分数值列举如下：
     ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]

     部分非数值列举如下：
     ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]**/
    public boolean isNumber(String s) {
        String trim;
        if (s == null || (trim = s.trim()) == null){
            return false;
        }
        String regex ="^([+-]?\\d+\\.?\\d*|[+-]?\\.\\d+)([Ee][+-]?\\d+)?$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(trim);
        return m.matches();
    }

    public boolean isNumber01(String s) {
        if(s == null || s.length() == 0){
            return false;
        }
        //标记是否遇到相应情况
        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        char[] str = s.trim().toCharArray();

        for(int i = 0;i < str.length; i++){
            if(str[i] >= '0' && str[i] <= '9'){
                numSeen = true;
            }else if(str[i] == '.'){
                //.之前不能出现.或者e
                if(dotSeen || eSeen){
                    return false;
                }
                dotSeen = true;
            }else if(str[i] == 'e' || str[i] == 'E'){
                //e之前不能出现e，必须出现数
                if(eSeen || !numSeen){
                    return false;
                }
                eSeen = true;
                numSeen = false;//重置numSeen，排除123e或者123e+的情况,确保e之后也出现数
            }else if(str[i] == '-' || str[i] == '+'){
                //+-出现在0位置或者e/E的后面第一个位置才是合法的
                if(i != 0 && str[i-1] != 'e' && str[i-1] != 'E'){
                    return false;
                }
            }else{//其他不合法字符
                return false;
            }
        }
        return numSeen;
    }


    // 使用有限状态自动机
    static final int[][] status = {
            {0, 1, 2, 9, 3, 9}, // 初始
            {9, 9, 2, 9, 3, 9}, // 符号
            {8, 9, 2, 5, 4, 9}, // 整数
            {9, 9, 4, 9, 9, 9}, // 小数点
            {8, 9, 4, 5, 9, 9}, // 小数
            {9, 6, 7, 9, 9, 9}, // 科学计数
            {9, 9, 7, 9, 9, 9}, // 幂符号
            {8, 9, 7, 9, 9, 9}, // 幂次
            {8, 9, 9, 9, 9, 9}, // 结束
            {9, 9, 9, 9, 9, 9}, // 错误
    };

    static final boolean[] allow = {
            false,  // 初始
            false,  // 符号
            true,   // 整数
            false,  // 小数点
            true,   // 小数
            false,  // 科学计数
            false,  // 幂符号
            true,   // 幂次
            true,   // 结束
            false   // 错误
    };

    public boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        for (char c : chars) {
            i = getNextStatus(i, c);
        }
        return allow[i];
    }

    private int getNextStatus(int i, char c) {
        int j;
        if (c == ' ') {
            j = 0;
        } else if (c == '+' || c == '-') {
            j = 1;
        } else if ('0' <= c && c <= '9') {
            j = 2;
        } else if (c == 'E' || c == 'e') {
            j = 3;
        } else if (c == '.') {
            j = 4;
        } else {
            j = 5;
        }
        return status[i][j];
    }


    /**剑指 Offer 67. 把字符串转换成整数

     写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。


     首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

     当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
     假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

     该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

     注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

     在任何情况下，若函数不能进行有效的转换时，请返回 0。

     说明：
     假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
     如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。**/
    public int strToInt(String str) {

    }
}
