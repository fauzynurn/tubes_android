package com.example.odoo.minimalproject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;


public class AddOrder{
    public static final String URL_ADDORDER = "http://laniary-accountabil.000webhostapp.com/android/addOrder.php";
    public static final String URL_GETLATESTDATE = "http://laniary-accountabil.000webhostapp.com/android/getLatestDate.php";
    public static final String URL_ADDDETAILORDER = "http://laniary-accountabil.000webhostapp.com/android/addDetailOrder.php";
    String getLatestDateUrl = URL_GETLATESTDATE + "?nim=161511049";
    private String nim = "161511049";
    private List<Menu> menuList = new ArrayList<>();
    private int totalHarga;
    private String status = "Waiting";
    private Context c;
    private Context menuActivityContext;
    FragmentManager fw;
    String latestDate;
    private static final String TAG = "AddOrder";

    public AddOrder(Context c, List<Menu> mList, int totalHarga, FragmentManager fm, Context a) {
        this.c = c;
        this.menuActivityContext = a;
        fw = fm;
        menuList = mList;
        this.totalHarga = totalHarga;

        String addOrderUrl = URL_ADDORDER + "?nim=" + nim + "&totalHarga=" + this.totalHarga + "&status=" + status;
        CallWebPageTask task1 = new CallWebPageTask();
        task1.applicationContext = c.getApplicationContext();
        task1.execute(new String[]{addOrderUrl});
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

    private class CallWebPageTask extends AsyncTask<String, Void, String> {
        protected Context applicationContext;
        LoadingDialog ld;

        @Override
        protected void onPreExecute() {
            ld = new LoadingDialog();
            ld.show(fw,"defg");
        }

        @Override
        protected String doInBackground(String... urls) {
            //1. Insert di tabel pesanan
            //2. Lakukan request ambil timestamp terbaru
            String response = "";
            String dateResponse = "";
            String dateResult = "";
            response = getRequest(urls[0]);
            dateResponse = getRequest(getLatestDateUrl);
            JSONObject j = null;
            try {
                j = new JSONObject(dateResponse);
                JSONArray newsJsonArray = j.getJSONArray("Date");
                dateResult = newsJsonArray.getJSONObject(0).getString("tglpesan");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return dateResult;
        }

        @Override
        protected void onPostExecute(String result) {
            //Kondisi timestamp terbaru harus sudah ada(tidak null). Disini bisa dilakukan loop insert ke
            //tabel detilpesanan
            DatabaseController dController = new DatabaseController();
            for(Menu menu : menuList){
                CallWebPageTask1 task2 = new CallWebPageTask1();
                task2.applicationContext = c.getApplicationContext();
                result = result.replaceAll(" ","+");
                String addDetailOrderUrl = URL_ADDDETAILORDER + "?nim=" + nim + "&tglpesan=" + result + "&idmenu=" + menu.id;
                task2.execute(new String[]{addDetailOrderUrl});
            }
            dController.getOrderFromMysql();
        }
    }

    private class CallWebPageTask1 extends AsyncTask<String, Void, String> {
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
        }
    }
}