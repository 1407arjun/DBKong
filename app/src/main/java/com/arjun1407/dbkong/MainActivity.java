package com.arjun1407.dbkong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.arjun1407.dbkong.database.mongodb.MongoDBConnect;
import com.arjun1407.dbkong.databinding.ActivityMainBinding;
import com.arjun1407.dbkong.utility.Executors;
import com.arjun1407.dbkong.utility.HelperClass;
import com.arjun1407.dbkong.utility.OnSuccessListener;

import org.json.JSONObject;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'dbkong' library on application startup.
    static {
        System.loadLibrary("dbkong");
        System.loadLibrary("node");
    }

    private ActivityMainBinding binding;
    public static boolean nodeStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textView = binding.sampleText;

        if(!nodeStarted) {
            nodeStarted = true;
            Executors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    //The path where we expect the node project to be at runtime.
                    String nodeDir=getApplicationContext().getFilesDir().getAbsolutePath()+"/nodejs";
                    if (HelperClass.wasAPKUpdated(MainActivity.this)) {
                        //Recursively delete any existing nodejs-project.
                        File nodeDirReference=new File(nodeDir);
                        if (nodeDirReference.exists()) {
                            HelperClass.deleteFolderRecursively(new File(nodeDir));
                        }
                        //Copy the node project from assets into the application's data path.
                        HelperClass.copyAssetFolder(getApplicationContext().getAssets(), "nodejs", nodeDir);

                        HelperClass.saveLastUpdateTime(MainActivity.this);
                    }
                    startNodeWithArguments(new String[]{"node",
                            nodeDir+"/app.js"
                    });
                }
            });
        }

        MongoDBConnect.getInstance(this, "").db("").collection("").find(new JSONObject())
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure() {

                    }
                });
    }

    public native int startNodeWithArguments(String[] arguments);
}