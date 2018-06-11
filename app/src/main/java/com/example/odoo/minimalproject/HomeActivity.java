package com.example.odoo.minimalproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public static final String URL_GETRECENTORDERLIST = "http://192.168.100.8/android/getRecentOrderList.php";
    RecyclerView recentOrderList;
    List<RecentOrder> roList = new ArrayList<>();
    private RecentOrderAdapter roAdapter;
//    ActionButton addOrder;
    private static final String TAG = "HomeActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        statusBarSetter statbarsetter = new statusBarSetter();
        getWindow().setStatusBarColor(Color.WHITE);
        addFood = findViewById(R.id.add_menu);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,MenuActivity.class);
                startActivity(i);
            }
        });
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(HomeActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.parseColor("#47D4AE"));
        }
        CallWebPageTask task = new CallWebPageTask();
        task.applicationContext = HomeActivity.this;
        task.execute(new String[] { URL_GETRECENTORDERLIST });
        recentOrderList = findViewById(R.id.recent_order_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recentOrderList.setLayoutManager(llm);
        roAdapter = new RecentOrderAdapter(roList,HomeActivity.this);
        recentOrderList.setAdapter(roAdapter);
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
                jObject = new JSONObject(result);
                JSONArray newsJsonArray = jObject.getJSONArray("Pesanan");
                for (int i = 0; i < newsJsonArray.length(); i++) {
                    RecentOrder ro = new RecentOrder(newsJsonArray.getJSONObject(i));
                    roList.add(0,ro);
                }
                roAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
