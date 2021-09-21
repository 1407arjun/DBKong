package com.arjun1407.dbkong.utility;

import android.content.Context;
import android.util.Log;

import com.arjun1407.dbkong.database.mongodb.MongoDBConnect;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class Volley extends MongoDBConnect {

    private Volley(String uri, String db, String collection) {
        super(uri, db, collection);
    }

    public static void postVolley(Context context, JSONObject object, int method) {

        RequestQueue queue = SingletonRequestQueue.getInstance(context.getApplicationContext()).getRequestQueue();
        VolleyLog.DEBUG = true;
        String BASE_URL = "http://localhost:3000/";

        JsonObjectRequest request = new JsonObjectRequest(BASE_URL, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    Log.d("Hellores", response.toString());
                    mListener.onSuccess(response);
                } else {
                    Log.d("Hellores", "null");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Log.d("Helloerr", error.getMessage());
                    mListener.onFailure(new Error(error));
                }

            }
        }) {
            @Override
            public int getMethod() {
                return method;
            }

            @Override
            public Priority getPriority() {
                return Priority.NORMAL;
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        queue.add(request);
    }
}