package com.arjun1407.dbkong.database.mongodb;

import com.android.volley.Request;
import com.arjun1407.dbkong.DBKong;
import com.arjun1407.dbkong.utility.OnSuccessListener;
import com.arjun1407.dbkong.utility.Volley;

import org.json.JSONArray;
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

    public MongoDBConnect insertOne(JSONObject query) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 0);
            object.put("query", query);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect insertMany(JSONObject query) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 1);
            object.put("query", query);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect updateOne(JSONObject filter, JSONObject updateDocument) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 2);
            object.put("filter", filter);
            object.put("updateDocument", updateDocument);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect updateOne(JSONObject filter, JSONObject updateDocument, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 2);
            object.put("filter", filter);
            object.put("updateDocument", updateDocument);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect updateMany(JSONObject filter, JSONObject updateDocument) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 3);
            object.put("filter", filter);
            object.put("updateDocument", updateDocument);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect updateMany(JSONObject filter, JSONObject updateDocument, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 3);
            object.put("filter", filter);
            object.put("updateDocument", updateDocument);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect replaceOne(JSONObject filter, JSONObject replacementDocument) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 4);
            object.put("filter", filter);
            object.put("replacementDocument", replacementDocument);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect replaceOne(JSONObject filter, JSONObject replacementDocument, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 4);
            object.put("filter", filter);
            object.put("replacementDocument", replacementDocument);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect findOne(JSONObject query) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 5);
            object.put("query", query);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect findOne(JSONObject query, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 5);
            object.put("query", query);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect find(JSONObject query) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 6);
            object.put("query", query);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect find(JSONObject query, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 6);
            object.put("query", query);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect deleteOne(JSONObject query) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 7);
            object.put("query", query);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect deleteMany(JSONObject query) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 8);
            object.put("query", query);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect countDocuments(JSONObject filter) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 9);
            object.put("filter", filter);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect countDocuments(JSONObject filter, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 9);
            object.put("filter", filter);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect aggregate(JSONArray pipeline) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 10);
            object.put("pipeline", pipeline);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect aggregate(JSONArray pipeline, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 10);
            object.put("pipeline", pipeline);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect findOneAndDelete(JSONObject query) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 11);
            object.put("query", query);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect findOneAndDelete(JSONObject query, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 11);
            object.put("query", query);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect findOneAndUpdate(JSONObject filter, JSONObject updateDocument) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 12);
            object.put("filter", filter);
            object.put("updateDocument", updateDocument);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect findOneAndUpdate(JSONObject filter, JSONObject updateDocument, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 12);
            object.put("filter", filter);
            object.put("updateDocument", updateDocument);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect findOneAndReplace(JSONObject filter, JSONObject replacementDocument) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 13);
            object.put("filter", filter);
            object.put("replacementDocument", replacementDocument);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect findOneAndReplace(JSONObject filter, JSONObject replacementDocument, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 13);
            object.put("filter", filter);
            object.put("replacementDocument", replacementDocument);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect bulkWrite(JSONArray operations) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 14);
            object.put("operations", operations);
            object.put("options", new JSONObject());

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public MongoDBConnect bulkWrite(JSONArray operations, JSONObject options) {
        try {
            JSONObject object = new JSONObject();
            object.put("uri", uri);
            object.put("db", db);
            object.put("collection", collection);
            object.put("cmd", 14);
            object.put("operations", operations);
            object.put("options", options);

            Volley.postVolley(context, object, Request.Method.POST);
        } catch (JSONException e) {
            e.printStackTrace();
            mListener.onFailure(new Error(e));
        }
        return instance;
    }

    public void addOnSuccessListener(OnSuccessListener listener) {
        mListener = listener;
    }
}
