package com.example.odoo.minimalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by odoo on 6/11/18.
 */

public class DetailOrderActivity extends AppCompatActivity {
    //Masuk kesini, data yang dibutuhin :
    // 1. Timestamp order
    // 2. Total harga
    // 3. List menu yang bakal masuk ke adapter
    RecyclerView detailOrderRecycler;
    public static final String URL_GETDETAILORDER = "http://laniary-accountabil.000webhostapp.com/android/getDetailOrder.php";
    boolean isThemed;
    TextView totalPrice,numOfItems,date,time;
    String rawDate;
    DetailOrderAdapter dOrderAdapter;
    List<Menu> detailOrderList = new ArrayList<>();
    ImageView backBtn;
    Intent intent;
    Bundle extras;
    SharedPreferences pref;

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DetailOrderActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order_layout);
        totalPrice = findViewById(R.id.price);
        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailOrderActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        numOfItems = findViewById(R.id.num_of_items);
        date = findViewById(R.id.date_data);
        time = findViewById(R.id.time_data);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        statusBarSetter statbarsetter = new statusBarSetter();
        getWindow().setStatusBarColor(Color.WHITE);
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(DetailOrderActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.parseColor("#47D4AE"));
        }
        intent = getIntent();
        extras = intent.getExtras();
        detailOrderRecycler = findViewById(R.id.detail_order_recycler);
        dOrderAdapter = new DetailOrderAdapter(detailOrderList,this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        detailOrderRecycler.setLayoutManager(llm);
        detailOrderRecycler.setAdapter(dOrderAdapter);
        rawDate = extras.getString("encodedDate");
        String encodedDate = rawDate.replaceAll(" ","+");;
        String url1 = URL_GETDETAILORDER + "?nim="+pref.getString("nim",null)+"&tglorder="+encodedDate;
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
        LoadingDialog ld;
        @Override
        protected void onPreExecute() {
            ld = new LoadingDialog();
            ld.show(getSupportFragmentManager(),"abcd");
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
                    Menu menuName = new Menu(newsJsonArray.getJSONObject(i));
                    detailOrderList.add(menuName);
                }
                totalPrice.setText(extras.getString("totalPrice"));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                Date d = null;
                try {
                    d = format.parse(rawDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                time.setText(new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(d.getTime()));
                date.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(d.getTime()));
                numOfItems.setText(String.valueOf(dOrderAdapter.menuList.size() + " items"));
                dOrderAdapter.notifyDataSetChanged();
                ld.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
