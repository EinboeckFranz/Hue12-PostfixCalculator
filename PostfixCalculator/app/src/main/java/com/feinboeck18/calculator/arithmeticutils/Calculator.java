package com.feinboeck18.calculator.arithmeticutils;

import com.feinboeck18.calculator.exceptions.UnknownOperatorException;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Calculator {
    private List<String> expressions;
    private Deque<Double> stack = new ArrayDeque<>();

    public Calculator(List<String> postfixes) {
        this.expressions = postfixes;
    }

    public BigDecimal getResult() throws UnknownOperatorException {
        Double left;
        Double right;

        for (int i = 0; i < expressions.size(); i++) {
            String expression = expressions.get(i);
            if(expression.equals("+") || expression.equals("-") || expression.equals("*") || expression.equals("/")) {
                right = stack.pop();
                left = stack.pop();

                stack.push(computeErg(left, expressions.get(i).charAt(0), right));
            } else
                stack.push(Double.valueOf(expressions.get(i)));
        }
        return BigDecimal.valueOf(stack.pop());
    }

    private Double computeErg(Double left, char expression, Double right) throws UnknownOperatorException {
        if(expression == '+')
            return left + right;
        else if(expression == '-')
            return left - right;
        else if(expression == '*')
            return left * right;
        else if(expression == '/')
            return left / right;
        throw new UnknownOperatorException();
    }
}