package com.arjun1407.dbkong.database.mongodb;

import android.content.Context;

import com.arjun1407.dbkong.DBKong;
import com.arjun1407.dbkong.utility.Volley;
import com.arjun1407.dbkong.utility.OnSuccessListener;

import org.json.JSONObject;

public class MongoDBDao extends DBKong {
    protected String uri;
    protected String db;
    protected String collection;
    private OnSuccessListener listener;

    public MongoDBDao(String uri, String db, String collection) {
        this.uri = uri;
        this.db = db;
        this.collection = collection;
    }

    protected MongoDBDao() {
    }

    public MongoDBDao find(JSONObject object) {
        Volley.postVolley(context, object);
        return new MongoDBDao(uri, db, collection);
    }

    public void addOnSuccessListener(OnSuccessListener listener) {
        this.listener = listener;
    }
}
