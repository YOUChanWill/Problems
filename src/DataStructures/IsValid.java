package DataStructures;

import java.util.Stack;

public class IsValid {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '['){
                stack.push(ch); // 入栈
            }else {
                if (stack.empty()){
                    return false;
                }
                char ch2 = stack.peek(); // 查看栈顶元素
                if (ch2 == '(' && ch == ')' || ch2 == '{' && ch == '}' || ch2 == '[' && ch == ']'){
                    stack.pop();
                }else return false;
            }
        }
        if (!stack.empty()){
            return false;
        }
        return true;
    }
}
