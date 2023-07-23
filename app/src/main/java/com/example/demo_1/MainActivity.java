package com.example.demo_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "TAG";
    private TextView textView;
    private Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        // Set OnClickListener for the Button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Button 1 clicked");
                Log.d(TAG, "onClick: Button 1 clicked");
                textView.setText("Button 1 clicked");
            }
        });

//        Task mode button click code
        TextView textViewTaskMode = findViewById(R.id.textViewTaskMode);
        Button buttonTaskMode = findViewById(R.id.buttonTaskMode);

        // Set OnClickListener for the Button
        buttonTaskMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button 2 clicked");
                Log.d(TAG, "onClick: Button 2 clicked");
            }
        });

    }
}