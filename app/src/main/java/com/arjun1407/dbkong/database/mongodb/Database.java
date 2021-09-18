package com.arjun1407.dbkong.database.mongodb;

public class Database extends Collection{

    public Database(String db) {
        this.db = db;
    }

    public Database() {
    }

    public Database db(String db) {
        this.db = db;
        return new Database(db);
    }
}
