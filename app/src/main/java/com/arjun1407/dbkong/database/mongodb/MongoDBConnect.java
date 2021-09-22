package com.arjun1407.dbkong.database.mongodb;

import com.android.volley.Request;
import com.arjun1407.dbkong.DBKong;
import com.arjun1407.dbkong.utility.OnSuccessListener;
import com.arjun1407.dbkong.utility.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MongoDBConnect extends DBKong {
    private static final Object LOCK = new Object();
    private static MongoDBConnect instance;
    private String uri;
    private String db;
    private String collection;
    protected static OnSuccessListener mListener;

    public MongoDBConnect(String uri, String db, String collection) {
        super(context, timeout);
        this.uri = uri;
        this.db = db;
        this.collection = collection;
    }

    public static MongoDBConnect getInstance(String uri, String db, String collection) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new MongoDBConnect(uri, db, collection);
            }
        }
        return instance;
    }

    public MongoDBConnect setUri(String uri) {
        this.uri = uri;
        return instance;
    }

    public MongoDBConnect setDb(String db) {
        this.db = db;
        return instance;
    }

    public MongoDBConnect setCollection(String collection) {
        this.collection = collection;
        return instance;
    }

    public MongoDBConnect find(JSONObject filter) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 5);
            object.put("filter", filter);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public void addOnSuccessListener(OnSuccessListener listener) {
        mListener = listener;
    }
}
