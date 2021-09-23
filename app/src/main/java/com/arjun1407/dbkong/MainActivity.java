package com.arjun1407.dbkong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arjun1407.dbkong.database.mongodb.MongoDBConnect;
import com.arjun1407.dbkong.databinding.ActivityMainBinding;
import com.arjun1407.dbkong.utility.Executors;
import com.arjun1407.dbkong.utility.HelperClass;
import com.arjun1407.dbkong.utility.OnInitListener;
import com.arjun1407.dbkong.utility.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textView = binding.sampleText;

        new DBKong(getApplicationContext(), 30000, new OnInitListener() {
            @Override
            public void onInit(boolean init, Error error) {
                Log.d("HelloInit", "init");
                if (init && error == null) {
                    Log.d("HelloInit", "init2");
                    String uri = "";
                    try {
                        MongoDBConnect.getInstance(uri, "", "")
                                .insertOne(new JSONObject("{property:property}"))
                                .find(new JSONObject("{}"))
                                .addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(JSONObject response) {
                                        Log.d("HelloInit", "init3");
                                        textView.setText(response.toString());
                                    }

                                    @Override
                                    public void onFailure(Error error) {
                                        Log.d("HelloInit", "init4");
                                        textView.setText(error.getMessage());
                                    }
                                });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        textView.setText(e.getMessage());
                    }
                } else {
                    if (error != null)
                        textView.setText(error.getMessage());
                }
            }
        });
    }
}