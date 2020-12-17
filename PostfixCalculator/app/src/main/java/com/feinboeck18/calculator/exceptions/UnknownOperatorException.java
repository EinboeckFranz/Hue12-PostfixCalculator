package com.feinboeck18.calculator.exceptions;

public class UnknownOperatorException extends Exception {
    public UnknownOperatorException() { }

    public UnknownOperatorException(String message) {
        super(message);
    }
}