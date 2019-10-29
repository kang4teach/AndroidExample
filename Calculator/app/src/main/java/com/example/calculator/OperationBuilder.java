package com.example.calculator;

import java.util.HashMap;

class OperationBuilder {
    HashMap<String,CalculateOperation> hashMap = new HashMap<>();
    public OperationBuilder() {
        hashMap.put("√￣",new SqrtOperation());
    }

    public CalculateOperation build(String operator) {
        return hashMap.get(operator);
    }
}
