package com.example.calculator;

class Calculator {
    CalculateOperation operation;
    private String cachedNumberString;
    private String currentNumberString;

    public Calculator(String numberString,String operator)
    {
        this.cachedNumberString = numberString;
        operation = new OperationFactory().create(operator);
    }
    String calculate(String displayString){
        currentNumberString = displayString;
        return  operation.calculate(new String[]{cachedNumberString,currentNumberString});
    }

    String getShowString(){
         return operation.calculate(new String[]{cachedNumberString});
    }
}
