package com.feinboeck18.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.feinboeck18.calculator.arithmeticutils.Calculator;
import com.feinboeck18.calculator.arithmeticutils.PostFixConverter;
import com.feinboeck18.calculator.exceptions.UnknownOperatorException;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private PostFixConverter converter;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.calculatorDisplay);
    }

    @SuppressLint("SetTextI18n")
    public void calcErg(View view) {
        converter = new PostFixConverter(display.getText().toString());
        calculator = new Calculator(converter.getPostfixAsList());
        try {
            String result = calculator.getResult().toString();
            display.setText(result);
        } catch (UnknownOperatorException uOe) {
            display.setText("ERROR - Could not resolve this action");
            display.setTextColor(Color.RED);
        }
    }

    @SuppressLint("SetTextI18n")
    public void clickedOnNumberOrSymbol(View view) {
        Button button = (Button) view;
        display.setText(display.getText().toString() + button.getText().toString());
    }

    public void resetDisplay(View view) {
        display.setText("");
        display.setTextColor(Color.WHITE);
    }
}