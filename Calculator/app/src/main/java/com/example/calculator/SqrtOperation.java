package com.example.calculator;

public class SqrtOperation implements  CalculateOperation{
    @Override
    public String calculate(String[] values) {
        return String.valueOf(Math.sqrt(Double.parseDouble(values[0])));
    }
}
