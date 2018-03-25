package com.example.odoo.minimalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by odoo on 3/23/18.
 */

public class layoutActivity extends AppCompatActivity{
    ArrayList<mhs> listmhs;
    private static final String TAG = "layoutActivity";
    private DatabaseReference mRef;
    TextView nim,nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        new statusBarConfig().StatusBarLightMode(this);
        mRef = FirebaseDatabase.getInstance().getReference().child("users");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listmhs = new ArrayList<mhs>();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    mhs mahasiswa = new mhs();
                    mahasiswa = messageSnapshot.getValue(mhs.class);
                    listmhs.add(mahasiswa);
                }
                layoutAdapter la = new layoutAdapter(layoutActivity.this, listmhs);
                ListView listView = findViewById(R.id.id_list);
                listView.setAdapter(la);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
