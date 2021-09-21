package com.arjun1407.dbkong;

import android.content.Context;

import com.arjun1407.dbkong.utility.Executors;
import com.arjun1407.dbkong.utility.HelperClass;

import java.io.File;

public class DBKong {

    protected static Context context;
    private static boolean nodeStarted = false;
    // Used to load the 'dbkong' library on application startup.
    static {
        System.loadLibrary("dbkong");
        System.loadLibrary("node");
    }

    public DBKong(Context context) {
        DBKong.context = context;
        init();
    }

    private void init() {
        if(!nodeStarted) {
            nodeStarted = true;
            Executors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    //The path where we expect the node project to be at runtime.
                    String nodeDir = context.getApplicationContext().getFilesDir().getAbsolutePath()+"/nodejs";
                    if (HelperClass.wasAPKUpdated(context)) {
                        //Recursively delete any existing nodejs-project.
                        File nodeDirReference=new File(nodeDir);
                        if (nodeDirReference.exists()) {
                            HelperClass.deleteFolderRecursively(new File(nodeDir));
                        }
                        //Copy the node project from assets into the application's data path.
                        HelperClass.copyAssetFolder(context.getApplicationContext().getAssets(), "nodejs", nodeDir);

                        HelperClass.saveLastUpdateTime(context);
                    }
                    startNodeWithArguments(new String[]{"node",
                            nodeDir+"/app.js"
                    });
                }
            });
        }
    }
    private native int startNodeWithArguments(String[] arguments);
}
