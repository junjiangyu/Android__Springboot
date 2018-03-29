package com.example.johnny.androidspringboot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Update extends AppCompatActivity {
    EditText idinput;
    EditText fnameinput;
    EditText lnameinput;
    EditText dobinput;
    EditText genderinput;
    EditText emailinput;
    Button updatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idinput = (EditText) findViewById(R.id.IDinputup);
        fnameinput = (EditText) findViewById(R.id.nameInputup);
        lnameinput  = (EditText) findViewById(R.id.editTextup);
        dobinput  = (EditText) findViewById(R.id.editText2up);
        genderinput  = (EditText) findViewById(R.id.editText3up);
        emailinput  = (EditText) findViewById(R.id.editText4up);
        updatebtn = (Button) findViewById(R.id.buttonup);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                //add new json object with variable inside
                Map<String, String> jsonObject = new HashMap<String, String>();
                jsonObject.put("id",idinput.getText().toString());
                jsonObject.put("fname", fnameinput.getText().toString());
                jsonObject.put("lname", lnameinput.getText().toString());
                jsonObject.put("dob",dobinput.getText().toString());
                jsonObject.put("gender",genderinput.getText().toString());
                jsonObject.put("email",emailinput.getText().toString());
                JSONObject parameter = new JSONObject(jsonObject);

                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, parameter.toString());
                Request request = new Request.Builder()
                        .url("https://johnnyuusqlspringboot.cfapps.io/Users/"+ idinput.getText().toString())
                        .put(body)
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("response", call.request().body().toString());

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("response", response.body().string());
                    }
                });


            }
        });





    }}