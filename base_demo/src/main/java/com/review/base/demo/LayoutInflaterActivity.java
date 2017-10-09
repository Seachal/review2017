package com.review.base.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by zhangquan on 17/5/5.
 */

public class LayoutInflaterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inflater_act);

    }

    public void inflateView(View view) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflater.Factory factory = layoutInflater.getFactory();
        LayoutInflater.Factory2 factory2 = layoutInflater.getFactory2();
        System.out.println("factory=" + factory);
        System.out.println("factory2=" + factory2);
        View contentView = layoutInflater.inflate(R.layout.inflater_layout, null);


    }
}
