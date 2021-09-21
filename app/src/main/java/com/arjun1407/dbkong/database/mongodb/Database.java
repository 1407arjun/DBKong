package com.arjun1407.dbkong.database.mongodb;

public class Database extends Collection{

    public Database(String db) {
        super.db = db;
    }

    String b = super.a;

    protected Database() {
    }

    public Database db(String db) {
        super.db = db;
        return new Database(db);
    }
}
