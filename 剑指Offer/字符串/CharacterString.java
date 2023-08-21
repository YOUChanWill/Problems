package 字符串;

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
