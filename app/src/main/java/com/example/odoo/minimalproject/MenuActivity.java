package com.example.odoo.minimalproject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.nex3z.notificationbadge.NotificationBadge;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;

/**
 * Created by odoo on 5/16/18.
 */

public class MenuActivity extends AppCompatActivity{
    public RecyclerView menuRecycler;
    SmartTabLayout MyTab;
    List<Menu> myCart = new ArrayList<>();
    ImageView backBtn;
    ImageView cartIcon;
    FoodFragment fFragment;
    DrinkFragment dFragment;
    EditText search;
    CartAdapter cAdapter;
    BottomSheetDialog bsd;
    private static final String TAG = "MenuActivity";
    ViewPagerConfig MyPage;
    boolean isThemed;

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MenuActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        statusBarSetter statbarsetter = new statusBarSetter();
        getWindow().setStatusBarColor(Color.WHITE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(MenuActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.parseColor("#47D4AE"));
        }
        search = findViewById(R.id.search_edit_text);
        cAdapter = new CartAdapter(myCart);
        bsd = new BottomSheetDialog(MenuActivity.this);
        bsd.setFragmentManager(getSupportFragmentManager());
        bsd.setCartAdapter(cAdapter);
        cartIcon = findViewById(R.id.cart_icon);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsd.show(getSupportFragmentManager(),"abcd");
            }
        });
        MyTab = findViewById(R.id.view_page_tab);
        MyPage = findViewById(R.id.MyPage);
        MyPage.disableScroll(false);
        SetUpViewPager(MyPage);
        MyTab.setViewPager(MyPage);
        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void SetUpViewPager (ViewPager viewpage){
        MyViewPageAdapter Adapter = new MyViewPageAdapter(getSupportFragmentManager());
        fFragment = new FoodFragment(this,cAdapter);
        dFragment = new DrinkFragment(this,cAdapter);
        Adapter.AddFragmentPage(fFragment, "Foods");
        Adapter.AddFragmentPage(dFragment,"Drinks");
        //We Need Fragment class now

        viewpage.setAdapter(Adapter);

    }

    public class MyViewPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> MyFragment = new ArrayList<>();
        private List<String> MyPageTittle = new ArrayList<>();

        public MyViewPageAdapter(FragmentManager manager){
            super(manager);
        }

        public void AddFragmentPage(Fragment Frag, String Title){
            MyFragment.add(Frag);
            MyPageTittle.add(Title);
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return MyPageTittle.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
