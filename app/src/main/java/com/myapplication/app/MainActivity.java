package com.myapplication.app;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText num1;
    private EditText num2;
    private Spinner operationSpinner;
    private Button calculate;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        num1 = findViewById(R.id.editTextNumber);
        num2 = findViewById(R.id.editTextNumber2);
        operationSpinner = findViewById(R.id.operationSpinner);
        calculate = findViewById(R.id.calculateButton);
        answer = findViewById(R.id.answerTextView);

        // Initialize the Spinner with the operations
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operationSpinner.setAdapter(adapter);

        // Set an OnClickListener for the calculate button
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the selected operation from the Spinner
                String operation = operationSpinner.getSelectedItem().toString();
                performOperation(operation);
            }
        });
    }

    private void performOperation(String operation) {
        // Retrieve the input from the EditText fields and perform validation
        String input1 = num1.getText().toString();
        String input2 = num2.getText().toString();

        if (input1.isEmpty() || input2.isEmpty()) {
            // Display a Toast message if the input fields are empty
            Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse the input to double and perform the selected operation
        double number1 = Double.parseDouble(input1);
        double number2 = Double.parseDouble(input2);
        double result = 0;
        switch (operation) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if (number2 == 0) {
                    Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = number1 / number2;
                break;
        }

        // Set the result in the answer TextView and animate it
        answer.setText("The answer is: " + result);
        animateAnswer();
    }

    private void animateAnswer() {
        // Create an ObjectAnimator to animate the answer TextView
        ObjectAnimator animator = ObjectAnimator.ofFloat(answer, "translationY", -50f, 0f);
        animator.setDuration(500);
        animator.start();
    }
}
