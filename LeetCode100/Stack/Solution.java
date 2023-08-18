package Stack;

import java.util.Deque;
import java.util.LinkedList;

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


    public String decodeString(String s) {

    }




}
