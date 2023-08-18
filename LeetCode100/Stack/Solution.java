package Stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {


    /**设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

     实现 MinStack 类:

     MinStack() 初始化堆栈对象。
     void push(int val) 将元素val推入堆栈。
     void pop() 删除堆栈顶部的元素。
     int top() 获取堆栈顶部的元素。
     int getMin() 获取堆栈中的最小元素。*/
    class MinStack {

        Deque<Integer> xStack;
        Deque<Integer> minStack;

        public MinStack() {
            xStack = new LinkedList<Integer>();
            minStack = new LinkedList<Integer>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            xStack.push(x);
            minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        public int top() {
            return xStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }


    /**给定一个经过编码的字符串，返回它解码后的字符串。

     编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

     你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

     此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。*/
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray())
        {
            if(c != ']')
                stack.push(c); // 把所有的字母push进去，除了]

            else
            {
                //step 1: 取出[] 内的字符串

                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty() && Character.isLetter(stack.peek()))
                    sb.insert(0, stack.pop());

                String sub = sb.toString(); //[ ]内的字符串
                stack.pop(); // 去除[
                //step 2: 获取倍数数字
                sb = new StringBuilder();
                while(!stack.isEmpty() && Character.isDigit(stack.peek()))
                    sb.insert(0, stack.pop());
                int count = Integer.valueOf(sb.toString()); //倍数
                //step 3: 根据倍数把字母再push回去
                while(count > 0)
                {
                    for(char ch : sub.toCharArray())
                        stack.push(ch);
                    count--;
                }
            }
        }
        //把栈里面所有的字母取出来
        StringBuilder retv = new StringBuilder();
        while(!stack.isEmpty()) retv.insert(0, stack.pop());
        return retv.toString();
    }

    int ptr = 0;
    public String decodeString01(String s) {
        return solve(s);
    }
    public String solve(String s) {
        int t = 0;
        StringBuilder sb = new StringBuilder();
        while(ptr < s.length()) {
            if(s.charAt(ptr) >= '0' && s.charAt(ptr) <= '9') {
                t = t * 10 + (s.charAt(ptr) - '0');
                ptr++;
            }else if(s.charAt(ptr) == '['){
                ptr++;
                String str = solve(s);
                //System.out.println(t + "  " + str);
                for(int i = 0; i < t; i++)
                    sb.append(str);
                t = 0;
            }else if(s.charAt(ptr) == ']') {
                ptr++;
                return sb.toString();
            }else {
                sb.append(s.charAt(ptr));
                ptr++;
            }
        }
        return sb.toString();
    }


    public int[] dailyTemperatures(int[] temperatures) {

    }




}
