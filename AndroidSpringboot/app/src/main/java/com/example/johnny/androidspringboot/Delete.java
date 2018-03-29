package com.example.johnny.androidspringboot;

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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Delete extends AppCompatActivity {

    Button deletebtn;
    EditText Didinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Didinput =(EditText) findViewById(R.id.editText5);
        deletebtn = (Button) findViewById(R.id.deletebtn);

      deletebtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              OkHttpClient client = new OkHttpClient();
              Request request = new Request.Builder()
                      .url("https://johnnyuusqlspringboot.cfapps.io/Users/"+Didinput.getText().toString())
                      .delete()
                      .build();
              Toast.makeText(Delete.this, "Delete Succeed!", Toast.LENGTH_LONG).show();

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




    }

}
