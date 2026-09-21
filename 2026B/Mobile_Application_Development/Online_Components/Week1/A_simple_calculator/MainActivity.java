package com.example.tutorial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDiv,
            buttonMul, buttonDot, buttonC, buttonEqual;
    EditText display;
    TextView input;
    float valueOne, valueTwo;

    boolean isAdd, isDiv, isMul, isSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonDot = (Button) findViewById(R.id.buttonDot);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSub = (Button) findViewById(R.id.buttonSub);
        buttonMul = (Button) findViewById(R.id.buttonMul);
        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonEqual = (Button) findViewById(R.id.buttonEqual);
        display = (EditText) findViewById(R.id.display);
        input = (TextView) findViewById(R.id.input);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "0");
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + ".");
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                display.setText("");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!display.getText().toString().isEmpty()){
                    valueOne = Float.parseFloat(display.getText() + "");
                    isAdd = true;
                    input.setText(display.getText().toString() + " + ");
                    display.setText(null);
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!display.getText().toString().isEmpty()){
                    valueOne = Float.parseFloat(display.getText() + "");
                    isSub = true;
                    input.setText(display.getText().toString() + " - ");
                    display.setText(null);
                }
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!display.getText().toString().isEmpty()){
                    valueOne = Float.parseFloat(display.getText() + "");
                    isDiv = true;
                    input.setText(display.getText().toString() + " / ");
                    display.setText(null);
                }
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!display.getText().toString().isEmpty()){
                    valueOne = Float.parseFloat(display.getText() + "");
                    isMul = true;
                    input.setText(display.getText().toString() + " * ");
                    display.setText(null);
                }
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                valueTwo = Float.parseFloat(display.getText() + "");
                if (isAdd) {
                    input.setText(input.getText() + display.getText().toString());
                    display.setText(valueOne + valueTwo + "");
                    isAdd = false;
                }

                if (isSub) {
                    input.setText(input.getText() + display.getText().toString());
                    display.setText(valueOne - valueTwo + "");
                    isSub = false;
                }

                if (isMul) {
                    input.setText(input.getText() + display.getText().toString());
                    display.setText(valueOne * valueTwo + "");
                    isMul = false;
                }

                if (isDiv) {
                    input.setText(input.getText() + display.getText().toString());
                    display.setText(valueOne / valueTwo + "");
                    isDiv = false;
                }
            }
        });
    }
}
