package com.example.calculator;

import java.util.HashMap;

class OperationFactory {
    HashMap<String,CalculateOperation> hashMap = new HashMap<>();
    public OperationFactory() {
        hashMap.put("√￣",new SqrtOperation());
        hashMap.put("x²", (values -> { return String.valueOf(Double.parseDouble(values[0])*Double.parseDouble(values[0]));}));
        hashMap.put("+",(values -> { return values.length < 2 ?  "0" : String.valueOf(Double.parseDouble(values[0])+Double.parseDouble(values[1]));}));
        hashMap.put("-",(values -> { return values.length < 2 ?  "0" : String.valueOf(Double.parseDouble(values[0])- Double.parseDouble(values[1]));}));
    }

    public CalculateOperation create(String operator) {
        return hashMap.get(operator);
    }
}
