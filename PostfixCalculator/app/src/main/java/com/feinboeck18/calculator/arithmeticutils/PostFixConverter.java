package com.feinboeck18.calculator.arithmeticutils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

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
        return 0;
    }

    private void convertExpression() {
        StringBuilder postfixBuilder = new StringBuilder();
        for(int i = 0; i < infix.length(); i++) {
            char charAtI = infix.charAt(i);
            if(getPrecedence(charAtI)==0)
                postfixBuilder.append(charAtI);
            else if(getPrecedence(charAtI)>0) {
                while(!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(charAtI))
                    postfixBuilder.append(" ").append(stack.pop());
                inputToStack(charAtI);
                postfixBuilder.append(" ");
            } else if(charAtI == '(') {
                postfixBuilder.append(" ");
                inputToStack(charAtI);
            } else if(charAtI == ')') {
                postfixBuilder.append(" ");
                for (char x = stack.pop(); x != '('; x = stack.pop())
                    postfixBuilder.append(" ").append(x != ')' ? x : "");
                stack.pop();
            }
        }

        while(!stack.isEmpty())
            postfixBuilder.append(" ").append(stack.pop());

        this.postfixExpr = postfixBuilder.toString();
        this.postfix = Arrays.stream(postfixExpr.split(" ")).collect(Collectors.toList());
        clearStack();
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
        return this.postfix;
    }
}