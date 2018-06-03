package com.example.odoo.minimalproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText nim;
    private DatabaseReference mRef;
    boolean isThemed;
    CustomDialog cd;
    PopupWindow pw;
    String nimContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        statusBarSetter statbarsetter = new statusBarSetter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView moveBtn = findViewById(R.id.move_button);
        nim = findViewById(R.id.inputNim);
        getWindow().setStatusBarColor(Color.WHITE);
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(MainActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.parseColor("#47D4AE"));
        }
        moveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
//                lottie.setVisibility(View.VISIBLE);
//                lottie.playAnimation();
//                mRef = FirebaseDatabase.getInstance().getReference().getRoot().child("users").child(nim.getText().toString());
//                mRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        lottie.cancelAnimation();
//                        lottie.setVisibility(View.INVISIBLE);
//                        nimContainer = nim.getText().toString();
//                        if(!dataSnapshot.exists() || nimContainer.isEmpty()){
//                            String toastText = "NIM tidak terdaftar di database.";
//                            Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
//                        }else {
//                            mahasiswa = dataSnapshot.getValue(mhs.class);
//                            cd = new CustomDialog(MainActivity.this);
//                            cd.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                            cd.setContentView(R.layout.identify_dialog);
//                            lottie1 = cd.findViewById(R.id.check_animation);
//                            lottie1.setRepeatCount(1);
//                            lottie1.playAnimation();
//                            cd.name = cd.findViewById(R.id.output);
//                            cd.changeDefaultName(mahasiswa.getNama());
//                            cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                            cd.setCancelable(true);
//                            cd.show();
//                            cd.directToHome = cd.findViewById(R.id.directToHome);
//                            cd.directToHome.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
//                                    startActivity(i);
//                                }
//                            });
//                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                });
            }
            });
            }
    }
