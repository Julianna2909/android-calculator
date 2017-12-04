package com.example.julianna.mysecondapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public TextView calc;
    public String disp = "";
    public String opChar = "";
    public String result = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        calc = (TextView)findViewById(R.id.textView2);
        calc.setText(disp);
    }

    public void reset(){
        calc.setText(disp);
    }

    public void clearstr(){
        disp = "";
        opChar = "";
        result = "";
    }

    public void onClick(View v){
        if(result != ""){
            clearstr();reset();
        }
        Button button = (Button) v;
        disp += button.getText();reset();
    }

    public boolean isOperator(char opChar){
        if ( opChar == '+' || opChar == '-' || opChar == '*'|| opChar== '/' || opChar == '^' || opChar == '√' || opChar == '%') return true;
            else return false;

    }

    public void onClickOperator(View v){
        if(disp == "") return;

        Button button = (Button)v;

        if(result != ""){
            String dis = result;
            clearstr();
            disp = dis;
        }

        if(opChar != ""){
            Log.d("something went wrong", ""+disp.charAt(disp.length()-1));
            if(isOperator(disp.charAt(disp.length()-1))){
                disp = disp.replace(disp.charAt(disp.length()-1), button.getText().charAt(0));
                reset();
                return;
            }
            else{
                getResult();
                disp = result;
                result = "";
            }
            opChar = button.getText().toString();
        }
        disp += button.getText();
        opChar = button.getText().toString();
        reset();
    }


    public void onClickClear(View v){
        clearstr();
        reset();
    }

    public double operate(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "x": return Double.valueOf(a) * Double.valueOf(b);
            case "^": return Math.pow(Double.valueOf(a),2);
            case "√": return Math.sqrt(Double.valueOf(a));
            case "%": return Double.valueOf(a) / 100;
            case "/": try{
                return Double.valueOf(a) / Double.valueOf(b);
            }catch (Exception e){
                Log.d("dil na 0", e.getMessage());
            }
            default: return -1;
        }
    }

    public boolean getResult(){
        if(opChar == "") return false;
        String[] operation = disp.split(Pattern.quote(opChar));
        result = String.valueOf(operate(operation[0], operation[1], opChar));
        return true;
    }

    public void onClickEqual(View v){
        if(disp == "") return;
        if(!getResult()) return;
        calc.setText(disp + "\n" + String.valueOf(result));
    }

}