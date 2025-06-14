package com.example.week3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    SharedPreferences prefs;
    TextView textView;
    Button backButton;
    Button thankYouButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize SharedPreferences
        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Get the saved name (or fallback value)
        String savedName = prefs.getString("ReserveName", "user");

        // Get the welcome string from strings.xml
        String welcomeMessage = getString(R.string.welcome_message, savedName);

        // Link views from XML
        textView = findViewById(R.id.textView); // TextView to show "Welcome"
        backButton = findViewById(R.id.previousPageButton);
        thankYouButton = findViewById(R.id.button);

        // Set the welcome message to the TextView
        textView.setText(welcomeMessage);

        // When "Don't Call Me That" is pressed, go back to FirstActivity
        Button backButton = findViewById(R.id.previousPageButton);
        backButton.setOnClickListener(v -> {
            // Clear the saved name so FirstActivity doesn't skip
            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove("ReserveName");
            editor.apply();

            // Now go to FirstActivity
            Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
            startActivity(intent);
            finish(); // Optional: close SecondActivity
        });


        // When "Thank You" is pressed, exit the app
        thankYouButton.setOnClickListener(v -> {
            finishAffinity(); // Closes all activities
        });
    }
}
