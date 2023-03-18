package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText mainText;
    String oldNumber = "0";
    String operator = "+";
    boolean isNew = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainText);

    }

    public void clickNumber(View view) {
        if(isNew)
            mainText.setText("");
        isNew = false;
        if (mainText.getText().toString().equals("Недопустимо")) {
            mainText.setText("");
        }
        String number = mainText.getText().toString();
        switch (view.getId()) {
            case R.id.butOne:number = number + "1"; break;
            case R.id.butTwo:number = number + "2"; break;
            case R.id.butThree:number = number + "3"; break;
            case R.id.butFour:number = number + "4"; break;
            case R.id.butFive:number = number + "5"; break;
            case R.id.butSix:number = number + "6"; break;
            case R.id.butSeven:number = number + "7"; break;
            case R.id.butEight:number = number + "8"; break;
            case R.id.butNine:number = number + "9"; break;
            case R.id.butZero:number = number + "0"; break;
            case R.id.butDot:
                if (!dotIsPresent(number)) {
                    number = number + ".";
                }
                break;
            case R.id.butPlusMinus:
                if (minusIsPresent(number)){
                    number = number.substring(1);
                }
                else{
                    number = "-" + number;
                }
                break;
        }
        mainText.setText(number);
    }

    public void operation(View view) {
        isNew = true;
        oldNumber = mainText.getText().toString();
        switch (view.getId()) {
            case R.id.butPlus: operator = "+"; break;
            case R.id.butMinus: operator = "-"; break;
            case R.id.butMultiply: operator = "*"; break;
            case R.id.butDivide: operator = "/"; break;
        }
    }

    public void clickResult(View view) {
        String newNumber = mainText.getText().toString();
        Double result = 0.0;
        if (Double.parseDouble(newNumber) == 0 && operator.equals("/")) {
            mainText.setText("Недопустимо");
        }
        else {
            if (oldNumber.equals("Недопустимо")) {
                oldNumber = "0";
               }
            switch (operator) {
                case "+":
                    result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                    break;
                case "-":
                    result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                    break;
                case "*":
                    result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                    break;
                case "/":
                    result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                    break;
            }
            mainText.setText(result + "");
        }
    }

    public void clickClear(View view) {
        isNew = true;
        mainText.setText("0");
    }

    public boolean dotIsPresent(String number) {
        if(number.indexOf(".") == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean minusIsPresent(String number) {
        if(number.charAt(0) == '-'){
            return true;
        }
        else {
            return false ;
        }
    }

    public void delete(View view) {
        String tempValue = mainText.getText().toString();
        if(tempValue.equals("0")) {
        }
        else {
            if(tempValue.length()>1) {
                mainText.setText(tempValue.substring(0,tempValue.length() - 1));
            }
            else {
                mainText.setText("0");
                isNew = true;
            }
            }
        }


    public void squareRoot(View view) {
        Double number = Double.parseDouble(mainText.getText().toString());
        Double temp = Math.sqrt(number);
        mainText.setText(temp.toString());
    }


    public void reverse(View view) {
        Double number = Double.parseDouble(mainText.getText().toString());
        if (number == 0) {
            mainText.setText("Недопустимо");
        }
        else {
            Double temp = 1 / number;
            mainText.setText(temp.toString());
        }
    }
}