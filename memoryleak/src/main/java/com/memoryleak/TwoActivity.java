package com.memoryleak;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by zhangquan on 17/5/2.
 */

public class TwoActivity extends Activity {
    private TextView textView;
    private int timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = (TextView) findViewById(R.id.text);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf(timer++));
                handler.postDelayed(this, 1000);
            }
        });
    }


}
