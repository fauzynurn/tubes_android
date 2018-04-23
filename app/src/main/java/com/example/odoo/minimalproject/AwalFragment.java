package com.example.odoo.minimalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by odoo on 4/21/18.
 */

public class AwalFragment extends Fragment{
    //Constructor default
    public AwalFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageTree = inflater.inflate(R.layout.page_1, container, false);
        return PageTree;
    }
}
