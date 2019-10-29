package com.example.calculator;

import java.util.HashMap;

class Calculator {
    CalculateOperation operation;
    private String cachedNumberString;
    private String currentNumberString;

    public Calculator(String numberString,String operator)
    {
        this.cachedNumberString = numberString;
        operation = new OperationBuilder().build(operator);
    }
    String calculate(String displayString){
        currentNumberString = displayString;
        return  operation.calculate(new String[]{cachedNumberString,currentNumberString});
    }

    String getShowString(){
         return operation.calculate(new String[]{cachedNumberString});
    }
}
