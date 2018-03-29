package com.example.johnny.androidspringboot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Main2Activity extends AppCompatActivity {
    EditText idinput;
    EditText fnameinput;
    EditText lnameinput;
    EditText dobinput;
    EditText genderinput;
    EditText emailinput;
    Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idinput = (EditText) findViewById(R.id.IDinput);
        fnameinput = (EditText) findViewById(R.id.nameInput);
        lnameinput  = (EditText) findViewById(R.id.editText);
        dobinput  = (EditText) findViewById(R.id.editText2);
        genderinput  = (EditText) findViewById(R.id.editText3);
        emailinput  = (EditText) findViewById(R.id.editText4);

        submitbtn = (Button) findViewById(R.id.button);




        submitbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                //add new json object with variable inside
                Map<String, String> jsonObject = new HashMap<String, String>();
                jsonObject.put("id",idinput.getText().toString());
                jsonObject.put("fname", fnameinput.getText().toString());
                jsonObject.put("lname", lnameinput.getText().toString());
                jsonObject.put("dob",dobinput.getText().toString());
                jsonObject.put("gender",genderinput.getText().toString());
                jsonObject.put("email",emailinput.getText().toString());
                Toast.makeText(Main2Activity.this, "Upload Succeed! Input Information: " + jsonObject.toString(), Toast.LENGTH_LONG).show();
                JSONObject parameter = new JSONObject(jsonObject);

                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, parameter.toString());
                Request request = new Request.Builder()
                        .url("https://johnnyuusqlspringboot.cfapps.io/Users")
                        .post(body)
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

                //back to main a
                Intent myIntent = new Intent(Main2Activity.this, MainActivity.class);
                Main2Activity.this.startActivity(myIntent);




            }



        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(Main2Activity.this, MainActivity.class);
            Main2Activity.this.startActivity(myIntent);
        }

        if (id == R.id.action_refresh) {
            finish();
            startActivity(getIntent());
        }

        return super.onOptionsItemSelected(item);
    }

}
