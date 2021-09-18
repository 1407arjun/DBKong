package com.arjun1407.dbkong.database.mongodb;

import android.content.Context;

import com.arjun1407.dbkong.utility.Volley;
import com.arjun1407.dbkong.utility.OnSuccessListener;

import org.json.JSONObject;

public class MongoDBDao {
    protected Context context;
    protected String uri;
    protected String db;
    protected String collection;
    private OnSuccessListener listener;

    public MongoDBDao(Context context, String uri, String db, String collection) {
        this.context = context;
        this.uri = uri;
        this.db = db;
        this.collection = collection;
    }

    public MongoDBDao() {
    }

    public MongoDBDao find(JSONObject object) {
        Volley.postVolley(context, object);
        return new MongoDBDao(context, uri, db, collection);
    }

    public void addOnSuccessListener(OnSuccessListener listener) {
        this.listener = listener;
    }
}
