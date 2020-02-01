package com.androidaura.t2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    // private String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";
    private String url = "https://jsonplaceholder.typicode.com/users";


    ArrayList<HashMap<String, String>> arraylist;
    CustomList adapter;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listview = (ListView) findViewById(R.id.list);
        arraylist = new ArrayList<HashMap<String, String>>();
        adapter = new CustomList(this, arraylist);
        listview.setAdapter(adapter);


      //  new Task().execute();
        loadHeroList();
    }

   // private class Task extends AsyncTask<Void, Void, Void> {
   private void loadHeroList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                          //  Log.d("vt","yes "+response);

                            //getting the whole json object from the response
                            JSONArray jj = new JSONArray(response);


                            for (int i = 0; i < jj.length(); i++) {
                                JSONObject obj= jj.getJSONObject(i);
                                //Log.d("vt","yes "+obj.getString("name"));
                                HashMap<String, String> map = new HashMap<String, String>();

                                map.put("name", obj.getString("name"));
                                arraylist.add(map);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}