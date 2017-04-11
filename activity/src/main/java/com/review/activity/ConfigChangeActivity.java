package com.review.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 横竖屏切换
 * 1、显示不同的布局
 * 取名相同的layout，横屏的布局放在layout-land下
 * 2、进入页面时(调用onCreate)加载布局 ，横竖屏layout中相同的id的控件能够使用，不同的id只能在onCreate加载的layout中使用。
 */

public class ConfigChangeActivity extends Activity {
    private TextView textView1;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configchange);
        System.out.println("------onCreate");
        textView1 = (TextView) findViewById(R.id.tv1);
        btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        System.out.println("btn2=" + btn2);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("------------onConfigurationChanged,,orientation=" + newConfig.orientation);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            System.out.println("-----竖屏,textView=" + textView1);
            textView1.setText("切换到竖屏");
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            System.out.println("-----横屏,textView=" + textView1);
            textView1.setText("切换到横屏");
            findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ConfigChangeActivity.this, "点击", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
