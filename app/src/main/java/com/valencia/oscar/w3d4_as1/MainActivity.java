package com.valencia.oscar.w3d4_as1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.valencia.oscar.w3d4_ex1.R;
import com.valencia.oscar.w3d4_as1.entities.UserResponse;
import com.valencia.oscar.w3d4_as1.entities.Result;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName()+"_TAG";
    private Button jsonObjectBT,jsonArrayBT,saveUserBtn,viewUsersBtn;
    private ProgressBar progressBar;
    private TextView textView;
    private RandomUserAPI userAPI;
    private UserDB userDB;

    private String tmpIDValue,tmpIDName,tmpLoginUserName,tmpLoginUserPassword,tmpLoginSalt,
            tmpLoginMd5,tmpLoginSha1,tmpLoginSha256,tmpNameTitle,tmpNameFirst,tmpNameLast,
            tmpPictureLarge,tmpPictureMedium,tmpPictureThumbnail,tmpLocationStreet,tmpLocationCity,
            tmpLocationState,tmpDbo,tmpRegistered,tmpPhone,tmpCell,tmpEmail,tmpGender,tmpNat;
    private int tmpLocationPostCode;



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
            case R.id.btnSave:
                saveUser();
                break;
            case R.id.btnViewUsers:
                Intent launchToUserList = new Intent(this,UserList.class);
                startActivity(launchToUserList);
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

                    tmpIDValue = userRes.getResults().get(0).getId().getValue();
                    tmpIDName = userRes.getResults().get(0).getId().getName();
                    tmpLoginUserName = userRes.getResults().get(0).getLogin().getUsername();
                    tmpLoginUserPassword = userRes.getResults().get(0).getLogin().getPassword();
                    tmpLoginSalt = userRes.getResults().get(0).getLogin().getSalt();
                    tmpLoginMd5 = userRes.getResults().get(0).getLogin().getMd5();
                    tmpLoginSha1 = userRes.getResults().get(0).getLogin().getSha1();
                    tmpLoginSha256 = userRes.getResults().get(0).getLogin().getSha256();
                    tmpNameTitle = userRes.getResults().get(0).getName().getTitle();
                    tmpNameFirst = userRes.getResults().get(0).getName().getFirst();
                    tmpNameLast = userRes.getResults().get(0).getName().getLast();
                    tmpPictureLarge = userRes.getResults().get(0).getPicture().getLarge();
                    tmpPictureMedium = userRes.getResults().get(0).getPicture().getMedium();
                    tmpPictureThumbnail = userRes.getResults().get(0).getPicture().getThumbnail();
                    tmpLocationStreet = userRes.getResults().get(0).getLocation().getStreet();
                    tmpLocationCity = userRes.getResults().get(0).getLocation().getCity();
                    tmpLocationState = userRes.getResults().get(0).getLocation().getState();
                    tmpLocationPostCode = userRes.getResults().get(0).getLocation().getPostcode();
                    tmpDbo = userRes.getResults().get(0).getDob();
                    tmpRegistered = userRes.getResults().get(0).getRegistered();
                    tmpPhone = userRes.getResults().get(0).getPhone();
                    tmpCell = userRes.getResults().get(0).getCell();
                    tmpEmail = userRes.getResults().get(0).getEmail();
                    tmpGender = userRes.getResults().get(0).getGender();
                    tmpNat = userRes.getResults().get(0).getNat();


                    textView.setText(tmpNameTitle+" "+tmpNameFirst+" "+tmpNameLast);
                    saveUserBtn.setEnabled(true);
                    Log.d(TAG, "onResponse:"+ tmpNameTitle+" "+tmpNameFirst+" "+tmpNameLast);
                }else{
                    Log.e(TAG, "onResponse: server error");
                }
                showProgress(false);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: no network access");
                showProgress(false);
                saveUserBtn.setEnabled(false);
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
        saveUserBtn = findViewById(R.id.btnSave);
        saveUserBtn.setOnClickListener(this);
        saveUserBtn.setEnabled(false);
        viewUsersBtn = findViewById(R.id.btnViewUsers);
        viewUsersBtn.setOnClickListener(this);
    }

    private void saveUser(){
        userDB = new UserDB(this,"USERS",null,1);
        SQLiteDatabase sqLiteDatabase = userDB.getWritableDatabase();

        if(sqLiteDatabase!=null){
            String SQL_INSERT =
                    "INSERT INTO "+userDB.getTableName()+" VALUES "+
                    " ( "+
                            "'"+tmpIDValue+"',"+
                            "'"+tmpIDName+"',"+
                            "'"+tmpLoginUserName+"',"+
                            "'"+tmpLoginUserPassword+"',"+
                            "'"+tmpLoginSalt+"',"+
                            "'"+tmpLoginMd5+"',"+
                            "'"+tmpLoginSha1+"',"+
                            "'"+tmpLoginSha256+"',"+
                            "'"+tmpNameTitle+"',"+
                            "'"+tmpNameFirst+"',"+
                            "'"+tmpNameLast+"',"+
                            "'"+tmpPictureLarge+"',"+
                            "'"+tmpPictureMedium+"',"+
                            "'"+tmpPictureThumbnail+"',"+
                            "'"+tmpLocationStreet+"',"+
                            "'"+tmpLocationCity+"',"+
                            "'"+tmpLocationState+"',"+
                            ""+tmpLocationPostCode+","+
                            "'"+tmpDbo+"',"+
                            "'"+tmpRegistered+"',"+
                            "'"+tmpPhone+"',"+
                            "'"+tmpCell+"',"+
                            "'"+tmpEmail+"',"+
                            "'"+tmpGender+"',"+
                            "'"+tmpNat+"'"+
                    " ) ";
            Log.d(TAG,SQL_INSERT);
            try{
                sqLiteDatabase.execSQL(SQL_INSERT);
                Log.d(TAG,"User was saved.");
                textView.setText("User was saved.");
                saveUserBtn.setEnabled(false);
            }catch(Exception e){
                Log.d(TAG,e.toString());
                Toast.makeText(this,"Error while saving user.",Toast.LENGTH_SHORT).show();
                saveUserBtn.setEnabled(false);
                if(null==tmpIDValue)textView.setText("The user has not ID. Unable to save");
            }finally {
                sqLiteDatabase.close();
            }
        }else{
            Log.d(TAG,"DB was not created");
        }
    }
}
