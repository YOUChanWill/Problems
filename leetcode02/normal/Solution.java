package normal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {


    /**在桌子上有 N 张卡片，每张卡片的正面和背面都写着一个正数（正面与背面上的数有可能不一样）。

     我们可以先翻转任意张卡片，然后选择其中一张卡片。

     如果选中的那张卡片背面的数字 X 与任意一张卡片的正面的数字都不同，那么这个数字是我们想要的数字。

     哪个数是这些想要的数字中最小的数（找到这些数中的最小值）呢？如果没有一个数字符合要求的，输出 0。

     其中, fronts[i] 和 backs[i] 分别代表第 i 张卡片的正面和背面的数字。

     如果我们通过翻转卡片来交换正面与背面上的数，那么当初在正面的数就变成背面的数，背面的数就变成正面的数。**/
    public int flipgame(int[] fronts, int[] backs) {
        int lengh = fronts.length,min = 2000;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < lengh; i++) {
            if (fronts[i] == backs[i]){
                set.add(fronts[i]);
            }
        }
        for (int i = 0; i < lengh; i++) {
            if (set.add(fronts[i]) && fronts[i] < min){
                min = fronts[i];
            }
            if (set.add(backs[i]) && backs[i] < min){
                min = backs[i];
            }
        }
        return min % 2000;
    }


    /**给一个 C++ 程序，删除程序中的注释。这个程序source是一个数组，其中source[i]表示第 i 行源码。 这表示每行源码由 '\n' 分隔。
     在 C++ 中有两种注释风格，行内注释和块注释。

     字符串// 表示行注释，表示//和其右侧的其余字符应该被忽略。
     字符串 表示一个块注释，它表示直到下一个（非重叠）出现的之间的所有字符都应该被忽略。（阅读顺序为从左到右）非重叠是指，字符串并没有结束块注释，因为注释的结尾与开头相重叠。
     第一个有效注释优先于其他注释。

     如果字符串//出现在块注释中会被忽略。
     同样，如果字符串/*出现在行或块注释中也会被忽略。
     如果一行在删除注释之后变为空字符串，那么不要输出该行。即，答案列表中的每个字符串都是非空的。
     **/
    public List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean blockComment = false;
        for (String s : source) {
            int m = s.length();
            for (int i = 0; i < m; ++i) {
                if (blockComment) {
                    // 遇到"*/"，则将状态改为不在注释块内，继续遍历后面第三个字符。
                    if (i + 1 < m && s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                        blockComment = false;
                        ++i;
                    }
                } else {
                    // 遇到"/*"，则将状态改为在注释块内，继续遍历后面第三个字符。
                    if (i + 1 < m && s.charAt(i) == '/' && s.charAt(i + 1) == '*') {
                        blockComment = true;
                        ++i;
                        // 遇到"//"，则直接忽略该行后面的部分。
                    } else if (i + 1 < m && s.charAt(i) == '/' && s.charAt(i + 1) == '/') {
                        break;
                        // 遇到其他字符，将该字符记录到 newLine 中。
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }
            if (!blockComment && sb.length() > 0) {
                ans.add(sb.toString());
                sb.setLength(0);
            }
        }
        return ans;
    }















}
