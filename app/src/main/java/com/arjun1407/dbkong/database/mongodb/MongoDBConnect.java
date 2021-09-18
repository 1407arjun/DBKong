package com.arjun1407.dbkong.database.mongodb;

import android.content.Context;

public class MongoDBConnect extends Database{
    private static final Object LOCK = new Object();
    private static MongoDBConnect instance;

    public MongoDBConnect(Context context, String uri) {
        this.context = context;
        this.uri = uri;
    }

    public static MongoDBConnect getInstance(Context context, String uri) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new MongoDBConnect(context, uri);
            }
        }
        return instance;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
