package com.example.demo_1.network;

import com.example.demo_1.data.model.TestApiResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/v1/")
    Call<TestApiResponseModel> getTestApiResponse();
}
