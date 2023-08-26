package 模拟;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {


    /**剑指 Offer 29. 顺时针打印矩阵

     输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。*/
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        // 定义左右上下四个方向，以及最后输出结束的索引
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1, index = 0;
        int[] ans = new int[(right + 1) * (bottom + 1)];
        while (true){
            // 从左往右打印
            for (int i = left; i <= right; i++) {
                ans[index++] = matrix[top][i];
            }
            if (++top > bottom) break; //上边界内缩
            // 从上往下打印
            for (int i = top; i <= bottom; i++) {
                ans[index++] = matrix[i][right];
            }
            if (left > --right) break; //右边界收缩
            // 从右往左打印
            for (int i = right; i >= left; i--) {
                ans[index++] = matrix[bottom][i];
            }
            if (top > -- bottom) break; // 下边界收缩
            // 从下往上打印
            for (int i = bottom; i >= top; i--) {
                ans[index++] = matrix[i][left];
            }
            if (++left > right) break; // 左边界收缩
        }
        return ans;
    }



    /**剑指 Offer 31. 栈的压入、弹出序列

     输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
     但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。*/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int popedIndex = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[popedIndex]){
                stack.pop();
                popedIndex++;
            }
        }
        return stack.isEmpty();
    }

    public boolean validateStackSequences01(int[] pushed, int[] popped) {
        int p1 = -1, p2 = 0;
        for (int i = 0; i < pushed.length; i++) {
            pushed[++p1] = pushed[i];
            while (p1 >= 0 && pushed[p1] == popped[p2]) {
                p1--;
                p2++;
            }
        }
        return p1 == -1;
    }


}
