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
        for (String expression : expressions) {
            if(expression.matches("[0-9]+"))
                stack.push(Double.parseDouble(expression));
            else {
                Double right = stack.pop();
                Double left = stack.pop();
                Double erg = computeErg(left, expression.charAt(0), right);
                stack.push(erg);
            }
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
