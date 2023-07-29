package com.example.demo_1.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.demo_1.data.model.AdminLoginRequestModel;
import com.example.demo_1.data.model.TestApiResponseModel;
import com.example.demo_1.data.model.User;
import com.example.demo_1.data.repository.UserDataRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginViewModel extends ViewModel {

    private String TAG = "TAG";
    private UserDataRepository userDataRepository;
    private MutableLiveData<TestApiResponseModel> loginResponseLiveData;

    private MutableLiveData<User> loginUserLiveData;

    public UserLoginViewModel() {
        userDataRepository = new UserDataRepository();
        loginResponseLiveData = new MutableLiveData<>();
        loginUserLiveData = new MutableLiveData<>();
    }

    public void loginAdmin(String mobile, String password) {

        AdminLoginRequestModel loginRequest = new AdminLoginRequestModel(mobile, password);

        userDataRepository.getAdminLoginResponse(loginRequest).enqueue(new Callback<TestApiResponseModel>() {
            @Override
            public void onResponse(Call<TestApiResponseModel> call, Response<TestApiResponseModel> response) {
                Log.d(TAG, "onResponse: got post request response");
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: POST successful");
                    TestApiResponseModel responseBody = response.body();
                    if (responseBody != null) {
                        Log.d(TAG, "onResponse: setting the data of post request");
                        loginResponseLiveData.setValue(responseBody);
                        loginUserLiveData.setValue(userDataRepository.getUserFromJwtToken(responseBody.getData()));
                    }
                } else {
                    // Handle error
                    Log.e(TAG, "onResponse: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<TestApiResponseModel> call, Throwable t) {
                // Handle failure
                Log.e(TAG, "onResponse: "+ t.getMessage());
            }
        });
    }

    public LiveData<TestApiResponseModel> getLoginResponseLiveData() {
        return loginResponseLiveData;
    }

    public MutableLiveData<User> getLoginUserLiveData() {
        return loginUserLiveData;
    }
}