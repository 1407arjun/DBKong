package com.arjun1407.dbkong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.arjun1407.dbkong.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

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

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        //TextView tv = binding.sampleText;
        //tv.setText(stringFromJNI());

        if( !_startedNodeAlready ) {
            _startedNodeAlready=true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    startNodeWithArguments(new String[]{"node", "-e",
                            "var http = require('http'); " +
                                    "var versions_server = http.createServer( (request, response) => { " +
                                    "  response.end('Versions: ' + JSON.stringify(process.versions)); " +
                                    "}); " +
                                    "versions_server.listen(3000);"});
                }
            }).start();
        }

        new CountDownTimer(10000, 1) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                new AsyncTask<Void,Void,String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        String nodeResponse="";
                        try {
                            URL localNodeServer = new URL("http://localhost:3000/");
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(localNodeServer.openStream()));
                            String inputLine;
                            while ((inputLine = in.readLine()) != null)
                                nodeResponse=nodeResponse+inputLine;
                            in.close();
                        } catch (Exception ex) {
                            nodeResponse=ex.toString();
                        }
                        return nodeResponse;
                    }
                    @Override
                    protected void onPostExecute(String result) {
                        Log.d("HelloRes", result);
                    }
                }.execute();
            }
        }.start();

    }

    /*
     * A native method that is implemented by the 'dbkong' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
    public native int startNodeWithArguments(String[] arguments);
}