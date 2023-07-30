package com.example.demo_1.network;

import com.example.demo_1.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAuthClient {
    private ApiService apiService;

    private static String currentAuthToken = "";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitAuthClient(String authToken) {
        if (retrofit == null || !currentAuthToken.equals(authToken)) {
            currentAuthToken = authToken;
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new AuthInterceptor(authToken));

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
