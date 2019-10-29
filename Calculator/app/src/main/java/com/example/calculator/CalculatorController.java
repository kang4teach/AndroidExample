package com.example.calculator;

public class CalculatorController {
    private String numberString = "0";
    Calculator calculatorObj ;


    public String input(String content) {
        if (isNumberic(content)){
            if (numberString.equals("0")){
                numberString = content;
            }else{
                numberString += content;
            }
        }else if (content.equals("=")){
            numberString = calculatorObj.calculate(numberString);
        }else{
            calculatorObj = new Calculator(numberString,content);
            numberString = calculatorObj.getShowString();
        }
        return numberString;
    }

    private String sqrt(String number)
    {
        return String.valueOf(Math.sqrt(Double.parseDouble(number)));
    }
    private boolean isNumberic(String content) {
        switch (content){
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                return true;
            default:return false;
        }
    }
}
