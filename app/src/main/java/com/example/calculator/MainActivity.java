package com.example.calculator;
import static com.example.calculator.R.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private EditText input1, input2, trigInput;
    private TextView resultView;
    private Switch modeSwitch;
    private boolean isDegree = true; // Default to degrees mode

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the Toolbar in your layout
        Toolbar toolbar = findViewById(R.id.my_toolbar);

        // Set the Toolbar as the support action bar
        setSupportActionBar(toolbar);

        // Initialize Arithmetic Views
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        resultView = findViewById(R.id.resultView);

        // Initialize Trigonometric Views
        trigInput = findViewById(R.id.trigInput);
        modeSwitch = findViewById(R.id.modeSwitch);

        // Switch for Degrees/Radians Mode
        modeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isDegree = isChecked;
            Toast.makeText(this, isDegree ? "Degrees Mode" : "Radians Mode", Toast.LENGTH_SHORT).show();
        });

        // Set up Arithmetic Buttons
        findViewById(R.id.addButton).setOnClickListener(v -> performArithmetic("+"));
        findViewById(R.id.subtractButton).setOnClickListener(v -> performArithmetic("-"));
        findViewById(R.id.multiplyButton).setOnClickListener(v -> performArithmetic("*"));
        findViewById(R.id.divideButton).setOnClickListener(v -> performArithmetic("/"));
        findViewById(R.id.modulusButton).setOnClickListener(v -> performArithmetic("%"));

        // Set up Trigonometric Buttons
        findViewById(R.id.sinButton).setOnClickListener(v -> performTrigonometric("sin"));
        findViewById(R.id.cosButton).setOnClickListener(v -> performTrigonometric("cos"));
        findViewById(R.id.tanButton).setOnClickListener(v -> performTrigonometric("tan"));
        findViewById(R.id.cosecButton).setOnClickListener(v -> performTrigonometric("cosec"));
        findViewById(R.id.secButton).setOnClickListener(v -> performTrigonometric("sec"));
        findViewById(R.id.cotButton).setOnClickListener(v -> performTrigonometric("cot"));

        //set up inverse Trigonometric Buttons
        findViewById(R.id.asinButton).setOnClickListener(v -> performTrigonometric("asin"));
        findViewById(R.id.acosButton).setOnClickListener(v -> performTrigonometric("acos"));
        findViewById(R.id.atanButton).setOnClickListener(v -> performTrigonometric("atan"));
        findViewById(R.id.arccscButton).setOnClickListener(v -> performTrigonometric("arccsc"));
        findViewById(R.id.arcsecButton).setOnClickListener(v -> performTrigonometric("arcsec"));
        findViewById(R.id.arccotButton).setOnClickListener(v -> performTrigonometric("arccot"));
    }private void performArithmetic(String operation) {
        try {
            double num1 = Double.parseDouble(input1.getText().toString());
            double num2 = Double.parseDouble(input2.getText().toString());
            double result;
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) throw new ArithmeticException("Division by zero");
                    result = num1 / num2;
                    break;
                case "%":
                    result = num1 % num2;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Operation");
            }
            resultView.setText(String.valueOf(result));
        } catch (Exception e) {
            showErrorDialog(e.getMessage());
        }
    }
    private void performTrigonometric(String function) {
        try {
            double angle = Double.parseDouble(trigInput.getText().toString());
            double radians = isDegree ? Math.toRadians(angle) : angle;
            double result;
            switch (function) {
                case "sin":
                    result = Math.sin(radians);
                    break;
                case "cos":
                    result = Math.cos(radians);
                    break;
                case "tan":
                    result = Math.tan(radians);
                    break;
                case "cosec":
                    result = 1 / Math.sin(radians);
                    break;
                case "sec":
                    result = 1 / Math.cos(radians);
                    break;
                case "cot":
                    result = 1 / Math.tan(radians);
                    break;
                case "asin":
                    result = Math.asin(radians);
                    break;
                case "acos":
                    result = Math.acos(radians);
                    break;
                case "atan":
                    result = Math.atan(radians);
                    break;
                case "arccsc":
                    result = Math.asin(1/radians);
                    break;
                case "arcsec":
                    result = Math.acos(1/radians);
                    break;
                case "arccot":
                    result = Math.atan(1/radians);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Function");
            }
            resultView.setText(String.valueOf(result));
        } catch (Exception e) {
            showErrorDialog(e.getMessage());
        }
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}