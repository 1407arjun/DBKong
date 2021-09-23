package com.arjun1407.dbkong;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.arjun1407.dbkong.utility.Executors;
import com.arjun1407.dbkong.utility.HelperClass;
import com.arjun1407.dbkong.utility.OnInitListener;
import com.arjun1407.dbkong.utility.OnSuccessListener;
import com.arjun1407.dbkong.utility.SingletonRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class DBKong {

    protected static Context context;
    private static boolean nodeStarted = false;
    private OnInitListener listener;
    protected static int timeout;
    Error mError;

    // Used to load the 'dbkong' library on application startup.
    static {
        System.loadLibrary("dbkong");
        System.loadLibrary("node");
    }

    public DBKong(Context context, int timeout) {
        DBKong.context = context;
        DBKong.timeout = timeout;
        init();
    }

    public DBKong(Context context, int timeout, OnInitListener listener) {
        DBKong.context = context;
        DBKong.timeout = timeout;
        this.listener = listener;

        RequestQueue queue = SingletonRequestQueue.getInstance(context).getRequestQueue();
        VolleyLog.DEBUG = true;
        String BASE_URL = "http://localhost:3000/";

        Log.d("Hellostart", Boolean.toString(nodeStarted));
        JsonObjectRequest request = new JsonObjectRequest(BASE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response != null && response.getBoolean("init")) {
                        Log.d("Hellores", response.toString());
                        nodeStarted = true;
                        listener.onInit(true, null);
                    } else {
                        Log.d("Hellores", "null");
                        init();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    init();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Log.d("Helloerr", error.getMessage());
                    init();
                } else {
                    init();
                }
            }
        }) {
            @Override
            public int getMethod() {
                return Method.GET;
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

    private void init() {
        if(!nodeStarted) {
            Executors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    //The path where we expect the node project to be at runtime.
                    String nodeDir = context.getFilesDir().getAbsolutePath()+"/nodejs";
                    if (HelperClass.wasAPKUpdated(context)) {
                        //Recursively delete any existing nodejs-project.
                        File nodeDirReference=new File(nodeDir);
                        if (nodeDirReference.exists()) {
                            HelperClass.deleteFolderRecursively(new File(nodeDir));
                        }
                        //Copy the node project from assets into the application's data path.
                        HelperClass.copyAssetFolder(context.getAssets(), "nodejs", nodeDir);

                        HelperClass.saveLastUpdateTime(context);
                    }
                    startNodeWithArguments(new String[]{"node",
                            nodeDir+"/app.js"});
                }
            });

            RequestQueue queue = SingletonRequestQueue.getInstance(context).getRequestQueue();
            VolleyLog.DEBUG = true;
            String BASE_URL = "http://localhost:3000/";

            new CountDownTimer(timeout, 500) {
                @Override
                public void onTick(long l) {
                    Log.d("Hellostart", Boolean.toString(nodeStarted));
                    JsonObjectRequest request = new JsonObjectRequest(BASE_URL, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response != null && response.getBoolean("init")) {
                                    Log.d("Hellores", response.toString());
                                    nodeStarted = true;
                                    listener.onInit(true, null);
                                    cancel();
                                } else {
                                    Log.d("Hellores", "null");
                                    mError = new Error("Unknown error occurred");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                mError = new Error(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error.getMessage() != null) {
                                Log.d("Helloerr", error.getMessage());
                                mError = new Error(error);
                            } else {
                                mError = new Error("Unknown error occurred");
                            }
                        }
                    }) {
                        @Override
                        public int getMethod() {
                            return Method.GET;
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

                @Override
                public void onFinish() {
                    if (nodeStarted)
                        listener.onInit(true, null);
                    else {
                        if (mError != null)
                            listener.onInit(false, mError);
                        else
                            listener.onInit(false, new Error("Unknown error occurred"));
                    }
                }
            }.start();
        }
    }
    private native int startNodeWithArguments(String[] arguments);
}
