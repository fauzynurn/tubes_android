package com.example.odoo.minimalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidviewhover.BlurLayout;

/**
 * Created by odoo on 5/16/18.
 */

public class MenuItemHolder extends RecyclerView.ViewHolder {
    public ImageView roundedImage;

    public MenuItemHolder(Context c, View view){
        super(view);
        roundedImage = view.findViewById(R.id.rounded_image);

    }
}
