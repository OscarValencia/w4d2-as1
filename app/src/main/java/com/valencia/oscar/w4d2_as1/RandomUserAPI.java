package com.valencia.oscar.w4d2_as1;


import com.valencia.oscar.w4d2_as1.entities.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserAPI {

    String BASE_URL = "https://www.googleapis.com/books/v1/";

    // https://randomuser.me/api
    @GET("volumes")
    Call<UserResponse>getBook();

    // https://randomuser.me/api?results=2..N
    @GET("volumes")
    Call<UserResponse>getBooks(
            @Query("key") String key,
            @Query("q")String search);
}
