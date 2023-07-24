package com.example.demo_1.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo_1.R;
import com.example.demo_1.data.model.TestApiResponseModel;
import com.example.demo_1.presentation.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "TAG";
    private TextView textView;
    private Button button;

    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.fetchResponse();
            }
        });

        mainViewModel.getTestApiResponseModelMutableLiveData().observe(this, new Observer<TestApiResponseModel>() {
            @Override
            public void onChanged(TestApiResponseModel responseModel) {
                // Update UI with the response data
                // You can access the response using responseModel.getStatus(), responseModel.getData(), etc.

                Log.d(TAG, "onChanged: data changed....");

                if(responseModel.isStatus()){
                    textView.setText(responseModel.getData());
                    Log.d(TAG, "Status: " + responseModel.isStatus());
                    Log.d(TAG, "Status Code: " + responseModel.getStatusCode());
                    Log.d(TAG, "Message: " + responseModel.getMessage());
                    Log.d(TAG, "Data: " + responseModel.getData());
                } else { // business error
                    textView.setText(responseModel.getMessage());
                    Log.d(TAG, "Status: " + responseModel.isStatus());
                    Log.d(TAG, "Status Code: " + responseModel.getStatusCode());
                    Log.d(TAG, "Message: " + responseModel.getMessage());
                    Log.d(TAG, "Data: " + responseModel.getData());
                }
            }
        });

        mainViewModel.getErrorResponseModelMutableLiveData().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {

                // show the error message on UI screen or tost
                Log.e(TAG, "onError: " + throwable.getMessage() );
            }
        });
    }
}