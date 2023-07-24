package com.example.demo_1.data.repository;



import com.example.demo_1.data.model.AdminLoginRequestModel;
import com.example.demo_1.data.model.TestApiResponseModel;
import com.example.demo_1.network.ApiService;
import com.example.demo_1.network.RetrofitClient;

import retrofit2.Call;

public class AdminDataRepository {

    private String TAG = "TAG";
    private ApiService apiService;

    public AdminDataRepository() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public Call<TestApiResponseModel> getAdminLoginResponse(AdminLoginRequestModel adminLoginRequestModel) {
        return apiService.loginAdmin(adminLoginRequestModel);
    }
}
