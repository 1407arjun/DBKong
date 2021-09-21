package com.arjun1407.dbkong.database.mongodb;

import com.android.volley.Request;
import com.arjun1407.dbkong.DBKong;
import com.arjun1407.dbkong.utility.Volley;
import com.arjun1407.dbkong.utility.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MongoDBDao extends DBKong {
    protected String uri;
    protected String db;
    protected String collection;
    protected static OnSuccessListener mListener;

    public MongoDBDao(String uri, String db, String collection) {
        this.uri = uri;
        this.db = db;
        this.collection = collection;
    }

    protected MongoDBDao() {
    }

    public MongoDBDao find(JSONObject filter) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 5);
            object.put("filter", filter);

            Volley.postVolley(context, object, Request.Method.POST, 5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new MongoDBDao(uri, db, collection);
    }

    public void addOnSuccessListener(OnSuccessListener listener) {
        mListener = listener;
    }
}
