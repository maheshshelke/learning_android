package com.example.demo_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo_1.models.ApiResponse;
import com.example.demo_1.retrofit.utils.BetterConnectServerInterface;
import com.example.demo_1.retrofit.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                makeGetRequest();
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

    private void makeGetRequest() {
        BetterConnectServerInterface apiInterface = RetrofitClient.getRetrofitInstance().create(BetterConnectServerInterface.class);
        Call<ApiResponse> call = apiInterface.getApiResponse();

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        Log.d(TAG, "Status: " + apiResponse.isStatus());
                        Log.d(TAG, "Status Code: " + apiResponse.getStatusCode());
                        Log.d(TAG, "Message: " + apiResponse.getMessage());
                        Log.d(TAG, "Data: " + apiResponse.getData());
                    }
                } else {
                    Log.e(TAG, "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }
}