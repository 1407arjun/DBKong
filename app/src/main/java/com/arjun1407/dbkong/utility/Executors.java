package com.arjun1407.dbkong.utility;

import java.util.concurrent.Executor;

public class Executors {
    private static final Object LOCK = new Object();
    private static Executors instance;
    private final Executor diskIO, networkIO;

    private Executors(Executor diskIO, Executor networkIO) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
    }

    public static Executors getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new Executors(java.util.concurrent.Executors.newSingleThreadExecutor(),
                        java.util.concurrent.Executors.newFixedThreadPool(3));
            }
        }
        return instance;
    }

    public Executor diskIO() { return diskIO; }
    public Executor networkIO() { return networkIO; }
}
