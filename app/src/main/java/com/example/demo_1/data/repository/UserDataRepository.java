package com.example.demo_1.data.repository;


import android.util.Log;

import com.example.demo_1.data.model.AdminLoginRequestModel;
import com.example.demo_1.data.model.TestApiResponseModel;
import com.example.demo_1.data.model.User;
import com.example.demo_1.network.ApiService;
import com.example.demo_1.network.RetrofitClient;

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

    public User getUserFromJwtToken(String jwtToken){
        User user = null;
        try {
            // Parse the JWT token
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey("unVvaRg2q7XGZPzjKvKeNem9SgPd7".getBytes()) // Use your SECRET_KEY here
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
