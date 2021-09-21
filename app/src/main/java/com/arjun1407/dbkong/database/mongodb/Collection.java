package com.arjun1407.dbkong.database.mongodb;

public class Collection extends MongoDBDao {

    public Collection(String collection) {
        super.collection = collection;
    }

    protected Collection() {
    }

    public Collection collection(String collection) {
        super.collection = collection;
        return new Collection(collection);
    }
}
