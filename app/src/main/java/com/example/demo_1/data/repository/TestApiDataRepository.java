package com.example.demo_1.data.repository;

import com.example.demo_1.data.model.TestApiResponseModel;
import com.example.demo_1.network.ApiService;
import com.example.demo_1.network.RetrofitClient;

import retrofit2.Call;

public class TestApiDataRepository {
    private ApiService apiService;

    public TestApiDataRepository() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public Call<TestApiResponseModel> getTestApiResponse() {
        return apiService.getTestApiResponse();
    }
}