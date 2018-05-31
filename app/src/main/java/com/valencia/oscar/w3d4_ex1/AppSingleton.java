package com.valencia.oscar.w3d4_ex1;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppSingleton {

    private static AppSingleton appInstance;
    private RequestQueue requestQueue;
    private Context context;

    public static synchronized AppSingleton getInstance(Context context) {

            if(appInstance==null){appInstance = new AppSingleton(context);}
        return appInstance;
    }

    private AppSingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequest(Object tag){
        if(requestQueue!=null){
            requestQueue.cancelAll(tag);
        }
    }
}
