package com.valencia.oscar.w4d2_as1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.valencia.oscar.w3d4_ex1.R;
import com.valencia.oscar.w4d2_as1.entities.Item;
import com.valencia.oscar.w4d2_as1.entities.UserResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName()+"_TAG";
    private static final String BOOK_API_KEY = "";
    private Button jsonObjectBT, resultsBtn;
    private ProgressBar progressBar;
    private TextView textView;
    private EditText editText;
    private RandomUserAPI userAPI;
    static UserResponse userResponse;



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
                getBooks();
                break;
            case R.id.btnResults:
                Intent launchToUserList = new Intent(this,UserList.class);
                startActivity(launchToUserList);
                break;

        }
    }

    private void getBooks(){
        showProgress(true);
        getRandomUserAPI().getBooks(
                BOOK_API_KEY,
                editText.getText().toString())
                .enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){

                    userResponse = response.body();
//                    (userResponse.getItems==null?"0":userResponse.getItems().size())
                    Log.d(TAG,userResponse.getItems().size()+" items were found");
                    textView.setText(userResponse.getItems().size()+" items were found");

                    if(userResponse.getTotalItems()>0)
                        resultsBtn.setEnabled(true);
                    for(Item result :userResponse.getItems()){
                        Log.d(TAG, "onResponse: " + result.getVolumeInfo().getTitle());
                    }
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " );
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
        jsonObjectBT = findViewById(R.id.btnGetObject);
        jsonObjectBT.setOnClickListener(this);
        progressBar = findViewById(R.id.progress);
        textView = findViewById(R.id.tvResponse);
        resultsBtn = findViewById(R.id.btnResults);
        resultsBtn.setEnabled(false);
        resultsBtn.setOnClickListener(this);
        editText=findViewById(R.id.searchET);
    }
}
