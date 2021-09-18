package com.arjun1407.dbkong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.arjun1407.dbkong.databinding.ActivityMainBinding;
import com.arjun1407.dbkong.utility.HelperClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'dbkong' library on application startup.
    static {
        System.loadLibrary("dbkong");
        System.loadLibrary("node");
    }

    private ActivityMainBinding binding;
    public static boolean _startedNodeAlready=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HelperClass helper = new HelperClass(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textView = binding.sampleText;

        // Example of a call to a native method
        //TextView tv = binding.sampleText;
        //tv.setText(stringFromJNI());

        if( !_startedNodeAlready ) {
            _startedNodeAlready=true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //The path where we expect the node project to be at runtime.
                    String nodeDir=getApplicationContext().getFilesDir().getAbsolutePath()+"/nodejs";
                    if (helper.wasAPKUpdated()) {
                        //Recursively delete any existing nodejs-project.
                        File nodeDirReference=new File(nodeDir);
                        if (nodeDirReference.exists()) {
                            helper.deleteFolderRecursively(new File(nodeDir));
                        }
                        //Copy the node project from assets into the application's data path.
                        helper.copyAssetFolder(getApplicationContext().getAssets(), "nodejs", nodeDir);

                        helper.saveLastUpdateTime();
                    }
                    startNodeWithArguments(new String[]{"node",
                            nodeDir+"/app.js"
                    });
                }
            }).start();
        }


    }

    /*
     * A native method that is implemented by the 'dbkong' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
    public native int startNodeWithArguments(String[] arguments);


}