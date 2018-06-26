package com.example.odoo.minimalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scalified.fab.ActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;


/**
 * Created by odoo on 4/21/18.
 */

public class HomeActivity extends AppCompatActivity{
    boolean isThemed;
    ActionButton addFood;
    public static final String URL_GETRECENTORDERLIST = "http://laniary-accountabil.000webhostapp.com/android/getRecentOrderList.php";
    RecyclerView recentOrderList;
    List<RecentOrder> roList = new ArrayList<>();
    private RecentOrderAdapter roAdapter;
    LoadingDialog ld;
    LinearLayoutManager llm;
    TextView shortName;
    SharedPreferences pref;
//    ActionButton addOrder;
    private static final String TAG = "HomeActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarSetter statbarsetter = new statusBarSetter();
        getWindow().setStatusBarColor(Color.WHITE);
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(HomeActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.parseColor("#47D4AE"));
        }
        llm = new LinearLayoutManager(this);
        roAdapter = new RecentOrderAdapter(HomeActivity.this);
        CallWebPageTask task = new CallWebPageTask();
        task.applicationContext = HomeActivity.this;
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String urlGetRecentOrder = URL_GETRECENTORDERLIST + "?nim="+pref.getString("nim",null);
        task.execute(new String[] { urlGetRecentOrder });
    }
    //Method untuk Mengirimkan data keserver
    public String getRequest(String Url){
        String sret;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(Url);
        try{
            HttpResponse response = client.execute(request);
            sret= request(response);
        }catch(Exception ex){
            sret= "Failed Connect to server!";
        }
        return sret;
    }
    //Method untuk Menerima data dari server
    public static String request(HttpResponse response){
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }

    private class CallWebPageTask extends AsyncTask<String, Void, String> {
        protected Context applicationContext;

        @Override
        protected void onPreExecute() {
            ld = new LoadingDialog();
            ld.show(getSupportFragmentManager(),"defg");
        }

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            response = getRequest(urls[0]);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            JSONObject jObject = null;
            try {
                JSONArray newsJsonArray = new JSONArray(result);
                for (int i = 0; i < newsJsonArray.length(); i++) {
                    RecentOrder ro = new RecentOrder(newsJsonArray.getJSONObject(i));
                    roList.add(0,ro);
                }
                roAdapter.setList(roList);
                roAdapter.notifyDataSetChanged();
                if(roList.isEmpty()){
                    setContentView(R.layout.home_empty_layout);
                    shortName = findViewById(R.id.profile_text);
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                    shortName.setText(pref.getString("shortName",null));
                    addFood = findViewById(R.id.add_menu);
                    addFood.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
                            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(!dataSnapshot.child("statusorder").exists() || dataSnapshot.child("statusorder").getValue().toString().equals("close")){
                                        AlertDialog ad = new AlertDialog();
                                        ad.show(getSupportFragmentManager(),"alert");
                                    }else{
                                        Intent i = new Intent(HomeActivity.this,MenuActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    });
                }else{
                    setContentView(R.layout.home_layout);
                    shortName = findViewById(R.id.profile_text);
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                    shortName.setText(pref.getString("shortName",null));
                    addFood = findViewById(R.id.add_menu);
                    addFood.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
                            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(!dataSnapshot.child("statusorder").exists() || dataSnapshot.child("statusorder")
                                            .getValue().toString().equals("close")){
                                        AlertDialog ad = new AlertDialog();
                                        ad.show(getSupportFragmentManager(),"alert");
                                    }else{
                                        Toast.makeText(applicationContext, dataSnapshot.child("statusorder")
                                                .getValue().toString(), Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(HomeActivity.this,MenuActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    });
                    recentOrderList = findViewById(R.id.recent_order_list);
                    recentOrderList.setLayoutManager(llm);
                    recentOrderList.setAdapter(roAdapter);

                    Log.i(TAG, "onPostExecute: "+roAdapter.RecentOrderList.size());
                }
                ld.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
