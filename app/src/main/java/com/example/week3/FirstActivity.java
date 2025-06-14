package com.example.week3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    EditText nameInput;
    Button nextButton;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Access SharedPreferences
        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedName = prefs.getString("ReserveName", null);

        // If name already saved, go directly to SecondActivity
        if (savedName != null) {
            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Link views
        nameInput = findViewById(R.id.inputText);
        nextButton = findViewById(R.id.buttonToSecond);

        // Set click listener for the Next button
        nextButton.setOnClickListener(v -> {
            String enteredName = nameInput.getText().toString().trim();

            if (!enteredName.isEmpty()) {
                // Save name to SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("ReserveName", enteredName);
                editor.apply();

                // Go to SecondActivity
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
                finish(); // Optional: close FirstActivity
            } else {
                nameInput.setError("Please enter your name");
            }
        });
    }
}
