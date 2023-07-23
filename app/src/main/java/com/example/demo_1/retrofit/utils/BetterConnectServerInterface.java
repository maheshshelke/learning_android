package com.example.demo_1.retrofit.utils;

import com.example.demo_1.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BetterConnectServerInterface {
    @GET("api/v1/")
    Call<ApiResponse> getApiResponse();
}
