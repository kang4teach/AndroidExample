package com.example.calculator;

public class CalculatorController {
    private String numberString = "0";
    private String operate = "";
    private String sqrt(String number)
    {
        return String.format(".2f%",Math.sqrt(Double.parseDouble(number)));
     }

    public String calculate()
    {
        if(operate.equals("clear"))
            return "0";
        else if (operate.equals("sqrt"))
             return  sqrt(numberString);
        else
            return  numberString;
    }

    public void input(String content) {
        if (content.equals("√￣")) {
            operate = "sqrt";
        } else if (content.equals("C")) {
            operate = "clear";
        } else {
            operate = "number";
            if (numberString.equals("0")) {
                numberString = content;
            }else {
                numberString += content;
            }
        }
    }
}
