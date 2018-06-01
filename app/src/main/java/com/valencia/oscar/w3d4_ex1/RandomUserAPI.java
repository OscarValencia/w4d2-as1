package com.valencia.oscar.w3d4_ex1;

import com.valencia.oscar.w3d4_ex1.entities.UserResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserAPI {

    String BASE_URL = "https://randomuser.me/";

    // https://randomuser.me/api
    @GET("api")
    Call<UserResponse>getRandomUser();

    // https://randomuser.me/api?results=2..N
    @GET("api")
    Call<UserResponse>getRandomUsers(@Query("results")int results);
}
