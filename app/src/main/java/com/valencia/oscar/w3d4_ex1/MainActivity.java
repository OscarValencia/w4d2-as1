package com.valencia.oscar.w3d4_ex1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.valencia.oscar.w3d4_ex1.entities.UserResponse;
import com.valencia.oscar.w3d4_ex1.entities.Result;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName()+"_TAG";
    private Button jsonObjectBT,jsonArrayBT;
    private ProgressBar progressBar;
    private TextView textView;
    private RandomUserAPI userAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnGetObject:
                getRandomUser();
                break;
            case R.id.btnGetArray:
                getRandomUsers();
                break;
        }
    }

    private void getRandomUsers(){
        showProgress(true);
        getRandomUserAPI().getRandomUsers(5).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    UserResponse userResponse = response.body();
                    for(Result result :userResponse.getResults()){
                        Log.d(TAG, "onResponse: " + result.getName().getFirst());
                    }
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    private void getRandomUser(){
        showProgress(true);
        getRandomUserAPI().getRandomUser().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    UserResponse userRes = response.body();
                    textView.setText(userRes.getInfo().getSeed());
                    Log.d(TAG, "onResponse: seed" + userRes.getInfo().getSeed());
                }else{
                    Log.e(TAG, "onResponse: server error");
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: no network access");
                showProgress(false);
            }
        });
    }

    private Retrofit prepareRetrofitClient() {
        return new Retrofit.Builder()
                .baseUrl(RandomUserAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private RandomUserAPI getRandomUserAPI(){
        if(userAPI == null){
            userAPI = prepareRetrofitClient().create(RandomUserAPI.class);
        }
        return userAPI;
    }

    private void showProgress(boolean isEnabled){
        if(isEnabled){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    private void initViews(){
        jsonArrayBT = findViewById(R.id.btnGetArray);
        jsonObjectBT = findViewById(R.id.btnGetObject);
        jsonArrayBT.setOnClickListener(this);
        jsonObjectBT.setOnClickListener(this);
        progressBar = findViewById(R.id.progress);
        textView = findViewById(R.id.tvResponse);
    }
}
