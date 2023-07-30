package com.example.demo_1.network;

import com.example.demo_1.data.model.AdminLoginRequestModel;
import com.example.demo_1.data.model.TestApiResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("api/v1/")
    Call<TestApiResponseModel> getTestApiResponse();

    @POST("api/v1/user/login")
    Call<TestApiResponseModel> loginAdmin(@Body AdminLoginRequestModel loginRequest);

    @GET("api/v1/user/admin/")
    Call<TestApiResponseModel> performAdminOperation();

    @GET("api/v1/user/fielder/")
    Call<TestApiResponseModel> performFielderOperation();
}
