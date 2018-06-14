package com.example.odoo.minimalproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
 * Created by odoo on 6/11/18.
 */

public class DetailOrderActivity extends AppCompatActivity {
    RecyclerView detailOrderRecycler;
    public static final String URL_GETDETAILORDER = "http://192.168.100.8/android/getDetailOrder.php";
    boolean isThemed;
    String rawDate;
    DetailOrderAdapter dOrderAdapter;
    List<String> detailOrderList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order_layout);
        statusBarSetter statbarsetter = new statusBarSetter();
        getWindow().setStatusBarColor(Color.WHITE);
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(DetailOrderActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.parseColor("#47D4AE"));
        }
        detailOrderRecycler = findViewById(R.id.detail_order_recycler);
        dOrderAdapter = new DetailOrderAdapter(detailOrderList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        detailOrderRecycler.setLayoutManager(llm);
        detailOrderRecycler.setAdapter(dOrderAdapter);
        Intent intent = getIntent();
        rawDate = (String) intent.getExtras().get("encodedDate");
        String encodedDate = rawDate.replaceAll(" ","+");;
        String url1 = URL_GETDETAILORDER + "?nim=161511049&tglorder="+encodedDate;
        CallWebPageTask task1 = new CallWebPageTask();
        task1.execute(new String[]{url1});
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
                str.append(line);
            }
            in.close();
            result = str.toString();

        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }

    //Class CallWebPageTask untuk implementasi class AscyncTask
    private class CallWebPageTask extends AsyncTask<String, Void, String> {

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
                JSONArray newsJsonArray = jObject.getJSONArray("Detailorder");
                for (int i = 0; i < newsJsonArray.length(); i++) {
                    String menuName = newsJsonArray.getJSONObject(i).getString("namamenu");
                    detailOrderList.add(menuName);
                }
                dOrderAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
