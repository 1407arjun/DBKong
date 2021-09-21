package com.arjun1407.dbkong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arjun1407.dbkong.database.mongodb.MongoDBConnect;
import com.arjun1407.dbkong.databinding.ActivityMainBinding;
import com.arjun1407.dbkong.utility.Executors;
import com.arjun1407.dbkong.utility.HelperClass;
import com.arjun1407.dbkong.utility.OnSuccessListener;

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
        Button button = binding.button;

        new DBKong(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "";
                try {
                    MongoDBConnect.getInstance(uri, "", "")
                            .find(new JSONObject("{property: property}"))
                            .addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(JSONObject response) {
                                    textView.setText(response.toString());
                                }

                                @Override
                                public void onFailure(Error error) {
                                    textView.setText(error.getMessage());
                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}