package com.example.demo_1.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.demo_1.data.model.TestApiResponseModel;
import com.example.demo_1.data.repository.TestApiDataRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// presentation/viewmodel/MainViewModel.java
public class MainViewModel extends ViewModel {

    final String TAG = "TAG";
    private TestApiDataRepository testApiDataRepository;
    private MutableLiveData<TestApiResponseModel> testApiResponseModelMutableLiveData;
    private MutableLiveData<Throwable> errorResponseModelMutableLiveData;

    public MainViewModel() {
        testApiDataRepository = new TestApiDataRepository();
        testApiResponseModelMutableLiveData = new MutableLiveData<>();
        errorResponseModelMutableLiveData = new MutableLiveData<Throwable>();
    }

    public void fetchResponse() {
        testApiDataRepository.getTestApiResponse().enqueue(new Callback<TestApiResponseModel>() {
            @Override
            public void onResponse(Call<TestApiResponseModel> call, Response<TestApiResponseModel> response) {
                if (response.isSuccessful()) {
                    TestApiResponseModel responseBody = response.body();
                    if (responseBody != null) {
                        testApiResponseModelMutableLiveData.setValue(responseBody);
                        // Save the response in SQLite
                        // You can use AsyncTask or RxJava for background database operations.
                        // For simplicity, we'll just show the logic here without background processing.

// not needed now
//                        AppDatabase database = AppDatabase.getInstance();
//                        database.responseDao().insertResponse(responseBody);
                    }
                } else { // this will not update the testApiResponseModelMutableLiveData
                    // need to handle is separately
                    // Handle error in case of 404 or 500
                    Log.e(TAG, "in response else part : " + response.code());
                    Log.e(TAG, ": " + response.message());
                    Log.e(TAG, ": " + response.body()); // body is null


                }
            }

            @Override
            public void onFailure(Call<TestApiResponseModel> call, Throwable t) {
                // Handle failure in case server down
                Log.e(TAG, "onFailure: " + t.getMessage());
                errorResponseModelMutableLiveData.setValue(t);
            }
        });
    }

    public LiveData<TestApiResponseModel> getTestApiResponseModelMutableLiveData() {
        return testApiResponseModelMutableLiveData;
    }

    public LiveData<Throwable> getErrorResponseModelMutableLiveData() {
        return errorResponseModelMutableLiveData;
    }

}
