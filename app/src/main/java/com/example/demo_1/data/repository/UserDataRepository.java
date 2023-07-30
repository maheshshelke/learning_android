package com.example.demo_1.data.repository;


import android.util.Log;

import com.example.demo_1.data.model.AdminLoginRequestModel;
import com.example.demo_1.data.model.TestApiResponseModel;
import com.example.demo_1.data.model.User;
import com.example.demo_1.network.ApiService;
import com.example.demo_1.network.RetrofitAuthClient;
import com.example.demo_1.network.RetrofitClient;
import com.example.demo_1.utils.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import retrofit2.Call;

public class UserDataRepository {

    private String TAG = "TAG";
    private ApiService apiService;

    public UserDataRepository() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public Call<TestApiResponseModel> getAdminLoginResponse(AdminLoginRequestModel adminLoginRequestModel) {
        return apiService.loginAdmin(adminLoginRequestModel);
    }

    public Call<TestApiResponseModel> performAdminOperation(){
        return apiService.performAdminOperation();
    }

    public Call<TestApiResponseModel> performFielderOperation(){
        return apiService.performFielderOperation();
    }



    public User getUserFromJwtToken(String jwtToken){
        User user = null;
        try {
            // set token in retrofit auth client for all the subsequent requests
            apiService = RetrofitAuthClient.getRetrofitAuthClient(jwtToken).create(ApiService.class);


            // Parse the JWT token
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(Constants.JWT_SECRET.getBytes()) // Use your SECRET_KEY here
//                    .build()
                    .parseClaimsJws(jwtToken);

            // Get the claims from the JWT token
            Claims claims = claimsJws.getBody();

            // Extract user information from the claims
            Integer userId = claims.get("id", Integer.class);
            String firstName = claims.get("firstname", String.class);
            String lastName = claims.get("lastname", String.class);
            String mobile = claims.get("mobile", String.class);
            String role = claims.get("role", String.class);

            user = new User(userId, firstName, lastName, mobile, role);
            Log.e(TAG, "getUserFromJwtToken: " + user );
            Log.d(TAG, "getUserFromJwtToken: " + user );

        } catch (Exception e) {
            e.printStackTrace();
            // Handle parsing errors or invalid JWT tokens here
        }

        return user;
    }
}
