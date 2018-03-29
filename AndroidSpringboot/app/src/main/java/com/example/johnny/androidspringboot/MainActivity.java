package com.example.johnny.androidspringboot;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //following two line enable the use of network on the main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ListView newlistview = (ListView) findViewById(R.id.listView);

        newlistview.setAdapter(new ArrayAdapter(
                this,android.R.layout.simple_list_item_1,
                this.populate()));


    }


    private ArrayList<String> populate() {
        ArrayList<String> items = new ArrayList<String>();

        try {
            URL url = new URL
                    ("https://johnnyuusqlspringboot.cfapps.io/Users");
            HttpURLConnection urlConnection =
                    (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // gets the server json data
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));
            String next;

            while ((next = bufferedReader.readLine()) != null){
                JSONArray ja = new JSONArray(next);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject) ja.get(i);
                    items.add("User: "+jo.getString("id" )+"\n"+
                            "First Name "+jo.getString("fname")+"\n"+
                            "Last Name: "+jo.getString("lname")+"\n"+
                            "Date of Birth "+jo.getString("dob")+"\n"+
                            "Gender "+jo.getString("gender")+"\n"+
                            "Email "+jo.getString("email")+"\n"
                    );

                }
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
            MainActivity.this.startActivity(myIntent);
        }

        if (id == R.id.action_refresh) {
            finish();
            startActivity(getIntent());
        }

        if (id == R.id.action_delete) {
            Intent myIntent = new Intent(MainActivity.this,Delete.class);
            MainActivity.this.startActivity(myIntent);
        }

        if (id == R.id.action_update) {
            Intent myIntent = new Intent(MainActivity.this,Update.class);
            MainActivity.this.startActivity(myIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
