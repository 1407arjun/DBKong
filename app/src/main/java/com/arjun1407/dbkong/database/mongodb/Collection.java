package com.arjun1407.dbkong.database.mongodb;

public class Collection extends MongoDBDao {

    public Collection(String collection) {
        this.collection = collection;
    }

    public Collection() {
    }

    public Collection collection(String collection) {
        this.collection = collection;
        return new Collection(collection);
    }
}
