package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    androidx.gridlayout.widget.GridLayout gridLayout;
    TextView textView;
    private String displayString = "0";
    CalculatorController calculatorController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setText(displayString);
        gridLayout = findViewById(R.id.gridLayout);
        calculatorController = new CalculatorController();
        for(int i =0;i < gridLayout.getChildCount();i++){
            Button btn = (Button) gridLayout.getChildAt(i);
               btn.setOnClickListener((view)->{
                      calculatorController.input(btn.getText().toString());
                      displayString = calculatorController.calculate();
                      textView.setText(displayString);
                });
            }
    }
}
