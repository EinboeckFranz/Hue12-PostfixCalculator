package com.feinboeck18.calculator.arithmeticutils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class PostFixConverter {
    private final String infix;
    private Deque<Character> stack = new ArrayDeque<>();
    private List<String> postfix = new ArrayList<>();
    private String postfixExpr;

    public PostFixConverter(String expression) {
        this.infix = expression;

        convertExpression();
    }

    public static int getPrecedence(char op) {
        if (op == '-' || op == '+')
            return 1;
        else if (op == '*' || op == '/')
            return 2;
        return -1;
    }

    //Combined some Code from Stack Overflow, it works.
    private void convertExpression() {
        StringBuilder temp = new StringBuilder();
        String toAppend = "";

        for (char c : infix.toCharArray()) {
            switch(c) {
                case '(':
                    inputToStack(c);
                break;
                case ')':
                    while(!stack.isEmpty() && stack.peek() != '(') {
                        toAppend += stack.pop();
                        temp.append(" ").append(toAppend);
                    }
                    if(!stack.isEmpty() && stack.peek() == '(')
                        toAppend += stack.pop();
                break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                        toAppend = String.valueOf(stack.pop());
                        temp.append(" ").append(toAppend);
                    }
                    inputToStack(c);
                    temp.append(" ");
                break;
                default:
                    temp.append(c);
                break;
            }
        }
        while(!stack.isEmpty()) {
            toAppend = String.valueOf(stack.pop());
            temp.append(" ").append(toAppend);
        }
        this.postfixExpr = temp.toString();
    }

    private void inputToStack(char input) {
        stack.push(input);
    }

    private void clearStack() {
        stack.clear();
    }

    public String getPostfixExpression() {
        return this.postfixExpr;
    }

    public List<String> getPostfixAsList() {
        return Arrays.asList(getPostfixExpression().split(" "));
    }
}