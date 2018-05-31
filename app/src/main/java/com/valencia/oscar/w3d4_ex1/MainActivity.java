package com.valencia.oscar.w3d4_ex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName()+"_TAG";
    private Button jsonObjectBT,jsonArrayBT;
    private ProgressBar progressBar;
    private TextView textView;
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
                getVolleyJSONObject();
                break;
            case R.id.btnGetArray:
                getVolleyJSONArray();
                break;
        }
    }

    private void getVolleyJSONObject() {
        String url = "https://randomuser.me/api";
        String REQUEST_TAG = "com.valencia.oscar.w3d4_ex1.volleyJsonObjectRequest";
        // show progress
        showProgress(true);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG,"onResponse: "+response.toString());
                        Info info = getInfoFromJSONObject(response);
                        textView.setText(info.getSeed());
                        Log.d(TAG,"onResponse: seed "+info.getSeed());

                        showProgress(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,"onErrorResponse"+error.getMessage());
                        showProgress(false);
                    }
                });
        AppSingleton.getInstance(getApplicationContext())
                .addToRequestQueue(jsonObjectRequest,REQUEST_TAG);

    }
    private void getVolleyJSONArray() {
        Toast.makeText(this,"NOT IMPLEMENTED",Toast.LENGTH_SHORT).show();
        Log.i(TAG,"getVolleyJSONArray: feature not implemented");
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

    private Info getInfoFromJSONObject(JSONObject response){
        Info info = null;
        try {
            JSONObject jsonObject = response.getJSONObject("info");
            String seed = jsonObject.getString("seed");
            int results = jsonObject.getInt("results");
            int page = jsonObject.getInt("page");
            String version = jsonObject.getString("version");
            info = new Info(seed,results,page,version);
        }catch(JSONException e){
            e.printStackTrace();;
        }
        return info;
    }
}
