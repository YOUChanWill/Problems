package 栈_队列;

import java.util.Stack;

public class Stack_Queue {


    /**剑指 Offer 09. 用两个栈实现队列

     用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
     分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )*/
    class CQueue {

        Stack<Integer> stack1, stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty()){
                if (stack1.isEmpty()) return -1;
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }


    /**剑指 Offer 30. 包含min函数的栈

     定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。*/
    class MinStack {

        Stack<Integer> stack1;
        Stack<Integer> stack2;

        /** initialize your data structure here. */
        public MinStack() {
            stack1 = new Stack<Integer>();
            stack2 = new Stack<Integer>();
        }

        public void push(int x) {
            stack1.push(x);
            if (stack2.isEmpty() || stack2.peek() >= x) stack2.push(x);
        }

        public void pop() {
            if (stack1.pop().equals(stack2.peek())) stack2.pop();
        }

        public int top() {
            return stack1.peek();
        }

        public int min() {
            return stack2.peek();
        }
    }


    /**剑指 Offer 59 - I. 滑动窗口的最大值

     给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。*/
    public int[] maxSlidingWindow(int[] nums, int k) {

    }



    /**剑指 Offer 59 - II. 队列的最大值

     请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

     若队列为空，pop_front 和 max_value 需要返回 -1*/
    class MaxQueue {

        public MaxQueue() {

        }

        public int max_value() {

        }

        public void push_back(int value) {

        }

        public int pop_front() {

        }
    }





}
