package com.eller.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnAC;
    private Button btnDel;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnMulti;
    private Button btnDivide;
    private Button btnEquals;
    private Button btnDot;
    private TextView txtResult,txtHistory;
    private String number = null;

    double firstNumber = 0;
    double lastNumber = 0;

    String status = null;
    boolean operator = false;
    DecimalFormat myFormatter = new DecimalFormat("######.######");
    String history, currentResult;
    boolean dot = true;
    boolean btnACcontrol = true;
    boolean btnEqualControl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMulti = findViewById(R.id.btnMulti);
        btnDivide = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnDot = findViewById(R.id.btnDot);

        txtResult = findViewById(R.id.txtResult);
        txtHistory = findViewById(R.id.txtHistory);

        btn0.setOnClickListener(view -> {
            numberClick("0");
        });
        btn1.setOnClickListener(view -> {
            numberClick("1");
        });
        btn2.setOnClickListener(view -> {
            numberClick("2");
        });
        btn3.setOnClickListener(view -> {
            numberClick("3");
        });
        btn4.setOnClickListener(view -> {
            numberClick("4");
        });
        btn5.setOnClickListener(view -> {
            numberClick("5");
        });
        btn6.setOnClickListener(view -> {
            numberClick("6");
        });
        btn7.setOnClickListener(view -> {
            numberClick("7");
        });
        btn8.setOnClickListener(view -> {
            numberClick("8");
        });
        btn9.setOnClickListener(view -> {
            numberClick("9");
        });
        btnAC.setOnClickListener(view -> {
            number = null;
            status = null;
            txtResult.setText("0");
            txtHistory.setText("");
            firstNumber = 0;
            lastNumber = 0;
            dot = true;
            btnACcontrol = true;
        });
        btnDel.setOnClickListener(view -> {
            if (btnACcontrol){
                txtResult.setText("0");
            }else{
                number = number.substring(0,number.length()-1);
                if(number.length() == 0){
                   btnDel.setClickable(false);
                }else if (number.contains(".")){
                   dot = false;
                }else{
                    dot = true;
                }
                txtResult.setText(number);
            }
        });
        btnPlus.setOnClickListener(view -> {
            history = txtHistory.getText().toString();
            currentResult = txtResult.getText().toString();
            txtHistory.setText(history + currentResult+"+");
            if (operator){
                if (status == "multiplications"){
                    multiply();
                }
                else if(status == "division"){
                    divide();
                }
                else if (status == "subtraction"){
                    minus();
                }
                else {
                    plus();
                }
            }
            status = "sum";
            operator = false;
            number = null;
        });
        btnMinus.setOnClickListener(view -> {
            history = txtHistory.getText().toString();
            currentResult = txtResult.getText().toString();
            txtHistory.setText(history + currentResult+"-");
            if (operator){
                if (status == "multiplications"){
                    multiply();
                }
                else if(status == "division"){
                    divide();
                }
                else if (status == "sum"){
                    plus();
                }
                else {
                    minus();
                }
            }
            status = "subtraction";
            operator = false;
            number = null;

        });
        btnMulti.setOnClickListener(view -> {
            history = txtHistory.getText().toString();
            currentResult = txtResult.getText().toString();
            txtHistory.setText(history + currentResult+"*");
            if (operator){
                if (status == "sum"){
                    plus();
                }
                else if(status == "division"){
                    divide();
                }
                else if (status == "subtraction"){
                    minus();
                }
                else {
                    multiply();
                }
            }
            status = "multiplications";
            operator = false;
            number = null;
        });
        btnEquals.setOnClickListener(view -> {
            if (operator){
                if (status == "multiplications"){
                    multiply();
                }
                else if(status == "sum"){
                    plus();
                }
                else if (status == "subtraction"){
                    minus();
                }
                else if(status == "division"){
                    divide();
                }
                else{
                    firstNumber= Double.parseDouble(txtResult.getText().toString());
                }
            }
            operator = false;
            btnEqualControl = true;
        });
        btnDivide.setOnClickListener(view -> {
            history = txtHistory.getText().toString();
            currentResult = txtResult.getText().toString();
            txtHistory.setText(history + currentResult+"/ ");
            if (operator){
                if (status == "multiplications"){
                    multiply();
                }
                 else if(status == "sum"){
                    plus();
                }
                else if (status == "subtraction"){
                    minus();
                }
                else {
                    divide();
                }
            }
            status = "division";
            operator = false;
            number = null;
        });
        btnDot.setOnClickListener(view -> {
            if(dot){
                if (number == null){
                    number = "0.";
                }else{
                    number += ".";
                }
            }
            txtResult.setText(number);
            dot = false;
        });
    }
    public void numberClick(String view){
        if (number == null){
            number = view;
        }else if (btnEqualControl){
            firstNumber = 0;
            lastNumber = 0;
            number = view;
        }
        else{
            number += view;
        }
        txtResult.setText(number);
        operator = true;
        btnACcontrol = false;
        btnDel.setClickable(true);
        btnEqualControl = false;
    }
    public void plus(){
        lastNumber = Double.parseDouble(txtResult.getText().toString());
        firstNumber += lastNumber;

        txtResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }
    public void minus(){
        if (firstNumber == 0){
            firstNumber = Double.parseDouble(txtResult.getText().toString());
        }else{
            lastNumber = Double.parseDouble(txtResult.getText().toString());
            firstNumber -= lastNumber;
        }
        txtResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }
    public void multiply(){
         if(firstNumber == 0){
             firstNumber = 1;
             lastNumber = Double.parseDouble(txtResult.getText().toString());
             firstNumber = firstNumber * lastNumber;
         }else{
             lastNumber = Double.parseDouble(txtResult.getText().toString());
             firstNumber = firstNumber * lastNumber;
         }
        txtResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }
    public void divide(){
        if (firstNumber == 0){
            lastNumber = Double.parseDouble(txtResult.getText().toString());
            firstNumber = lastNumber / 1;
        }else{
            lastNumber = Double.parseDouble(txtResult.getText().toString());
            firstNumber = firstNumber / lastNumber;
        }
        txtResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }
}