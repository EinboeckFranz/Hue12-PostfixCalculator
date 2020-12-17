package com.feinboeck18.calculator.arithmeticutils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostFixConverter {
    private final String infix;
    private Deque<Character> stack = new ArrayDeque<>();
    private List<String> postfix = new ArrayList<>();

    public PostFixConverter(String expression) {
        this.infix = expression;

        convertExpression();
    }

    private void convertExpression() {
        char pooped = ' ';
        for(int i = 0; i < infix.length(); i++) {
            char charAtI = infix.charAt(i);
            if(getPrecedence(charAtI)==0)
                postfix.add(charAtI + "");
            else if(charAtI == ')') {
                while((pooped = stack.pop()) != '(')
                    postfix.add(pooped + "");
            } else {
                while(!stack.isEmpty() && charAtI != '(' && getPrecedence(stack.peek()) >= getPrecedence(pooped))
                    postfix.add(stack.pop() + "");

                inputToStack(pooped);
            }
        }

        while(!stack.isEmpty())
            postfix.add(stack.pop()+"");

        clearStack();
    }

    private void inputToStack(char input) {
        stack.push(input);
    }

    private int getPrecedence(char op) {
        if(op == '(' || op == ')')
            return 1;
        else if (op == '-' || op == '+')
            return 2;
        else if (op == '*' || op == '/')
            return 3;
        return 0;
    }

    private void clearStack() {
        stack.clear();
    }

    public String getPostfixExpression() {
        StringBuilder sB = new StringBuilder();
        postfix.forEach(sB::append);
        return sB.toString();
    }

    public List<String> getPostfixAsList() {
        return postfix;
    }
}