package com.example.demo_1.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demo_1.R;
import com.example.demo_1.data.model.TestApiResponseModel;
import com.example.demo_1.data.model.User;
import com.example.demo_1.presentation.viewmodel.UserLoginViewModel;
import com.example.demo_1.presentation.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "TAG";

    // view models
    private MainViewModel mainViewModel;
    private UserLoginViewModel userLoginViewModel;

    //controls on screen
    private TextView textViewConnectionTest;
    private Button buttonTest;

    private EditText editTextMobile;
    private EditText editTextOtp;
    private Button buttonLogin;
    private TextView textViewLoginMessage;

    private Button buttonAdminOperation;
    private Button buttonUserOperation;
    private TextView textViewUserOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewConnectionTest = findViewById(R.id.textViewConnectionTest);
        buttonTest = findViewById(R.id.buttonTest);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        userLoginViewModel = new ViewModelProvider(this).get(UserLoginViewModel.class);

        buttonAdminOperation = findViewById(R.id.buttonAdminOperation);
        buttonUserOperation = findViewById(R.id.buttonUserOperation);
        textViewUserOperation = findViewById(R.id.textViewUserOperation);

        // hide the user operations button and text view
        buttonAdminOperation.setVisibility(View.INVISIBLE);
        buttonUserOperation.setVisibility(View.INVISIBLE);
        textViewUserOperation.setVisibility(View.INVISIBLE);


        // register click event on button click
        buttonTest.setOnClickListener(new View.OnClickListener() {
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
                    textViewConnectionTest.setText(responseModel.getData());
                    Log.d(TAG, "Status: " + responseModel.isStatus());
                    Log.d(TAG, "Status Code: " + responseModel.getStatusCode());
                    Log.d(TAG, "Message: " + responseModel.getMessage());
                    Log.d(TAG, "Data: " + responseModel.getData());
                } else { // business error
                    textViewConnectionTest.setText(responseModel.getMessage());
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

        buttonLogin = findViewById(R.id.buttonLogin);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextOtp = findViewById(R.id.editTextOTP);
        textViewLoginMessage = findViewById(R.id.textViewLoginMessage);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: login button  is clicked");
                userLoginViewModel.loginAdmin(editTextMobile.getText().toString(),
                        editTextOtp.getText().toString());
            }
        });


        // subscribe to success response of admin login api
        userLoginViewModel.getLoginResponseLiveData().observe(this, new Observer<TestApiResponseModel>() {
            @Override
            public void onChanged(TestApiResponseModel responseModel) {
                // Update UI with the response data from the second button click (login)
                // You can access the response using responseModel.getStatus(), responseModel.getData(), etc.

                Log.d(TAG, "admin login response received....");

                if(responseModel.isStatus()){ // login successful
//                    textViewLoginMessage.setText(responseModel.getData());
                    Log.d(TAG, "Status: " + responseModel.isStatus());
                    Log.d(TAG, "Status Code: " + responseModel.getStatusCode());
                    Log.d(TAG, "Message: " + responseModel.getMessage());
                    Log.d(TAG, "Data: " + responseModel.getData());
                } else { // login failed
                    textViewLoginMessage.setText(responseModel.getMessage());
                    Log.d(TAG, "Status: " + responseModel.isStatus());
                    Log.d(TAG, "Status Code: " + responseModel.getStatusCode());
                    Log.d(TAG, "Message: " + responseModel.getMessage());
                    Log.d(TAG, "Data: " + responseModel.getData());
                }

            }
        });

        userLoginViewModel.getLoginUserLiveData().observe(this, new Observer<User>(){

            @Override
            public void onChanged(User user) {
                Log.d(TAG, "admin login user received....");
                if(user != null) {
                    textViewLoginMessage.setText(user.getFirstName() + " " + user.getLastName() + " : Role: " + user.getRole());
                    if (user.getRole().equals("admin")) {
                        buttonAdminOperation.setVisibility(View.VISIBLE);
                        textViewUserOperation.setVisibility(View.VISIBLE);
                    } else {
                        buttonUserOperation.setVisibility(View.VISIBLE);
                        textViewUserOperation.setVisibility(View.VISIBLE);
                    }
                }
            }

        });

        buttonAdminOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: admin operation button  is clicked");
                userLoginViewModel.performAdminOperation();
            }
        });

        userLoginViewModel.getAdminOperationResponseLiveData().observe(this, new Observer<TestApiResponseModel>() {
            @Override
            public void onChanged(TestApiResponseModel testApiResponseModel) {

                Log.d(TAG, "admin operation data received....");
                Log.d(TAG, "onChanged: " + testApiResponseModel);
                textViewUserOperation.setText(testApiResponseModel.getMessage());
            }
        });


        buttonUserOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: fielder operation button is clicked");
                userLoginViewModel.performFielderOperation();
            }
        });

        userLoginViewModel.getFielderOperationResponseLiveData().observe(this, new Observer<TestApiResponseModel>() {
            @Override
            public void onChanged(TestApiResponseModel testApiResponseModel) {

                Log.d(TAG, "fielder operation data received....");
                Log.d(TAG, "onChanged: " + testApiResponseModel);
                textViewUserOperation.setText(testApiResponseModel.getMessage());
            }
        });


    } //activity onCreate closing bracket

}