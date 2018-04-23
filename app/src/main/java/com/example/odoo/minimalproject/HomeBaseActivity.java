package com.example.odoo.minimalproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odoo on 4/21/18.
 */

public class HomeBaseActivity extends AppCompatActivity{
    SmartTabLayout MyTab;
    ViewPager MyPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_base_layout);

        MyTab = findViewById(R.id.view_page_tab);
        MyPage = findViewById(R.id.MyPage);

        SetUpViewPager(MyPage);
        MyTab.setViewPager(MyPage);
    }

    public void SetUpViewPager (ViewPager viewpage){
        MyViewPageAdapter Adapter = new MyViewPageAdapter(getSupportFragmentManager());

        Adapter.AddFragmentPage(new AwalFragment(), "Home");
        Adapter.AddFragmentPage(new MenuFragment(), "Menu");
        Adapter.AddFragmentPage(new FavouritesActivity(), "Favourites");
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
            return 3;
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
