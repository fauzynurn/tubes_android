package com.example.odoo.minimalproject;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by odoo on 4/28/18.
 */

public class ViewPagerConfig extends ViewPager{
    private Boolean isDisabled = false;
    public ViewPagerConfig(Context context) {
        super(context);
    }
    public ViewPagerConfig(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return isDisabled ? false : super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isDisabled ? false : super.onTouchEvent(event);
    }

    public void disableScroll(Boolean disable){
        this.isDisabled = disable;
    }
}
