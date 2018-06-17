package com.example.odoo.minimalproject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * Created by odoo on 6/2/18.
 */

public class DrinkFragment extends Fragment {
    RecyclerView drinkRecycler;
    MenuAdapter ma;
    List<Menu> dList = new ArrayList<>();
    Context context;
    CartAdapter ca;
    public static final String URL_GETDRINKLIST = "http://laniary-accountabil.000webhostapp.com/android/getDrinkList.php";

    public DrinkFragment(){

    }

    @SuppressLint("ValidFragment")
    public DrinkFragment(Context c, CartAdapter ca) {
        context = c;
        this.ca = ca;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageTree = inflater.inflate(R.layout.drink_fragment, container, false);
        drinkRecycler = PageTree.findViewById(R.id.drink_list);
        CallWebPageTask task = new CallWebPageTask();
        task.applicationContext = this.getContext();
        task.execute(new String[] { URL_GETDRINKLIST });
        ma = new MenuAdapter(context,dList,ca);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        drinkRecycler.setLayoutManager(mLayoutManager);
        drinkRecycler.setItemAnimator(new DefaultItemAnimator());
        drinkRecycler.setAdapter(ma);
        return PageTree;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        private ProgressDialog dialog;
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
                JSONArray newsJsonArray = jObject.getJSONArray("Menu");
                for (int i = 0; i < newsJsonArray.length(); i++) {
                    Menu menu = new Menu(newsJsonArray.getJSONObject(i));
                    dList.add(menu);
                }
                ma.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public MenuAdapter getMenuAdapter(){
        return ma;
    }
}
