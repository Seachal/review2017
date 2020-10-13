package com.review.receiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends Activity implements View.OnClickListener {
    LocalBroadcastManager localBroadcastManager;
    LocalReceiver localReceiver;
    static String ACTION = "com.intent.action.test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        findViewById(R.id.btn_sendBrocast).setOnClickListener(this);
        findViewById(R.id.btn_sendLocalBrocast).setOnClickListener(this);
        findViewById(R.id.btn_permission).setOnClickListener(this);


        //注册本地广播
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);


        int taskId = getTaskId();
        System.out.println("MainActivity: taskId="+taskId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendBrocast:
                Intent intent = new Intent(ACTION);
                intent.putExtra("msg", "hello receiver.");
                sendOrderedBroadcast(intent, null);
                break;
            case R.id.btn_sendLocalBrocast:
                Intent intent2 = new Intent(ACTION);
                localBroadcastManager.sendBroadcast(intent2);
                break;
            case R.id.btn_permission:
                Intent intent1 = new Intent();
                intent1.setClassName("com.review2017", "com.review2017.PermissionActivity");
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
}

