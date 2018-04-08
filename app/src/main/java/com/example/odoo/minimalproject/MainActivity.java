package com.example.odoo.minimalproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaeger.library.StatusBarUtil;
import com.victor.loading.rotate.RotateLoading;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText nim;
    private DatabaseReference mRef;
    Intent i;
    loading l;
    CustomDialog cd;
    String nimContainer;
    mhs mahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView moveBtn = findViewById(R.id.move_button);
        nim = findViewById(R.id.inputNim);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        moveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
            }
//                mahasiswa = new mhs();
//                l = new loading(MainActivity.this, "Hello");
//                l.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                l.setCancelable(true);
//                l.show();
//                l.playLoading();
//                mRef = FirebaseDatabase.getInstance().getReference().getRoot().child("users").child(nim.getText().toString());
//                mRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        l.hide();
//                        nimContainer = nim.getText().toString();
//                        if(!dataSnapshot.exists() || nimContainer.isEmpty()){
//                            String toastText = "NIM tidak terdaftar di database.";
//                            Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
//                        }else {
//                            mahasiswa = dataSnapshot.getValue(mhs.class);
//                            cd = new CustomDialog(MainActivity.this);
//                            cd.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                            cd.setContentView(R.layout.identify_dialog);
//                            cd.name = cd.findViewById(R.id.output);
//                            cd.changeDefaultName(mahasiswa.getNama());
//                            cd.cancel = cd.findViewById(R.id.cancel);
//                            cd.go = cd.findViewById(R.id.directToHome);
//                            cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                            cd.cancel.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    cd.hide();
//                                }
//                            });
//                            cd.go.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
//                                    startActivity(i);
//                                }
//                            });
//                            cd.setCancelable(true);
//                            cd.show();
//                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                });
//            }
//        });
            });
    }
}
