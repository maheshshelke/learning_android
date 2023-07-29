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
import com.example.demo_1.presentation.viewmodel.AdminLoginViewModel;
import com.example.demo_1.presentation.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "TAG";
    private TextView textView;
    private Button button;

    private MainViewModel mainViewModel;

    private AdminLoginViewModel adminLoginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        Button button2 = findViewById(R.id.buttonTaskMode);
        adminLoginViewModel = new ViewModelProvider(this).get(AdminLoginViewModel.class);


        Button button = findViewById(R.id.button);
        // register click event on button click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.fetchResponse();
            }
        });

        // subscribe to success response of test api
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

        // subscribe to error response of test api
        mainViewModel.getErrorResponseModelMutableLiveData().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {

                // show the error message on UI screen or tost
                Log.e(TAG, "onError: " + throwable.getMessage() );
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: button2 is clicked");
                adminLoginViewModel.loginAdmin("0987654321", "123456");
            }
        });

        // subscribe to success response of admin login api
        adminLoginViewModel.getAdminLoginResponseLiveData().observe(this, new Observer<TestApiResponseModel>() {
            @Override
            public void onChanged(TestApiResponseModel responseModel) {
                // Update UI with the response data from the second button click (login)
                // You can access the response using responseModel.getStatus(), responseModel.getData(), etc.

                Log.d(TAG, "admin login response received....");

                if(responseModel.isStatus()){ // login successful
                    textView.setText(responseModel.getData());
                    Log.d(TAG, "Status: " + responseModel.isStatus());
                    Log.d(TAG, "Status Code: " + responseModel.getStatusCode());
                    Log.d(TAG, "Message: " + responseModel.getMessage());
                    Log.d(TAG, "Data: " + responseModel.getData());
                } else { // login failed
                    textView.setText(responseModel.getMessage());
                    Log.d(TAG, "Status: " + responseModel.isStatus());
                    Log.d(TAG, "Status Code: " + responseModel.getStatusCode());
                    Log.d(TAG, "Message: " + responseModel.getMessage());
                    Log.d(TAG, "Data: " + responseModel.getData());
                }

            }
        });
    }

}