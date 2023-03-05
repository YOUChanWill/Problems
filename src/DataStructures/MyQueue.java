package DataStructures;

import java.util.Stack;

public class MyQueue {
    Stack<Integer> stackin;
    Stack<Integer> stackout;

    public MyQueue() {
        stackin = new Stack<>();
        stackout = new Stack<>();
    }

    public void push(int x) {
        stackin.push(x);
    }

    public int pop() {
        if (stackout.isEmpty()){
            enterOutExiyIn();
        }
        return stackout.pop();
    }

    public int peek() {
        if (stackout.isEmpty()){
            enterOutExiyIn();
        }
        return stackout.peek();
    }

    public boolean empty() {
        return stackin.isEmpty() && stackout.isEmpty();
    }

    public void enterOutExiyIn(){
        while (!stackin.isEmpty()){
            stackout.push(stackin.pop());
        }
    }

}
