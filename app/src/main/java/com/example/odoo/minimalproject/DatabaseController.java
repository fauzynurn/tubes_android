package com.example.odoo.minimalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

//Class ini fungsinya untuk mengambil data pesanan beserta detil pesanannya dari mysql
//dan mengirimnya ke firebase atau dengan kata lain sebagai penghubung kedua database.

public class DatabaseController {
    public UserOrder userOrder;
    public List<Menu> firebaseList = new ArrayList<>();
    public static final String URL_GETORDER = "http://laniary-accountabil.000webhostapp.com/android/getOrder.php";
    private DatabaseReference mRef;
    private static final String TAG = "DatabaseController";
    SharedPreferences pref;
    Context context;

    public DatabaseController(Context c, List<Menu> mList){
        context = c;
        firebaseList = mList;
        pref = context.getSharedPreferences("MyPref", 0);
    }

    public void getOrderFromMysql(){
        String getOrderUrl = URL_GETORDER + "?nim="+pref.getString("nim",null);
        CallWebPageTask task1 = new CallWebPageTask();
        task1.execute(new String[]{getOrderUrl});
    }

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
            JSONObject j = null;
            List<Menu> mList = new ArrayList<>();
            try {
                j = new JSONObject(result);
                Log.i(TAG, "onPostExecute: "+result);
                JSONArray detailOrderList = j.getJSONArray("detailpesanan");
                for(int i=0; i<detailOrderList.length(); i++){
                    Menu menu = new Menu(detailOrderList.getJSONObject(i));
                    mList.add(menu);
                }
                userOrder = new UserOrder(j,mList);
                writeDataToFirebase();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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

    public void writeDataToFirebase(){
        mRef = FirebaseDatabase.getInstance().getReference();
        mRef.child("orderlist").child(userOrder.getNama())
                .child("totalharga").setValue(String.valueOf(userOrder.getTotalPrice())+"k");
        for(int i=0;i<firebaseList.size();i++){
            mRef.child("orderlist").child(userOrder.getNama())
                    .child("detailpesanan")
                    .child(String.valueOf(i))
                    .setValue(firebaseList.get(i).meal + "." + firebaseList.get(i).jenis);
        }
    }
}
