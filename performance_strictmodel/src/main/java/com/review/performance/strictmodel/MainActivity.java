package com.review.performance.strictmodel;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView tv_memoryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_memoryInfo = (TextView) findViewById(R.id.tv_memory);
        findViewById(R.id.btn_rw).setOnClickListener(this);
        findViewById(R.id.btn_leak).setOnClickListener(this);
        findViewById(R.id.btn_fun).setOnClickListener(this);

        ViewServer.get(this).addWindow(this);

        memoryInfo();
    }

    private void memoryInfo() {
        StringBuffer infoBuilder = new StringBuffer();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = activityManager.getMemoryClass();
        int largeMemoryClass = activityManager.getLargeMemoryClass();
        infoBuilder.append("memoryClass=" + memoryClass).append("MB\n");
        infoBuilder.append("largeMemoryClass=" + largeMemoryClass).append("MB\n");


        long freeMemory = Runtime.getRuntime().freeMemory();  //APP已申请内存中剩余的大小，当内存不足时，会继续申请
        long totalMemory = Runtime.getRuntime().totalMemory(); //APP当前已申请的内存大小
        long maxMemory = Runtime.getRuntime().maxMemory(); //一般等价于memoryClass

        infoBuilder.append("freeMemory=" + Formatter.formatFileSize(this, freeMemory)).append("\n");
        infoBuilder.append("totalMemory=" + Formatter.formatFileSize(this, totalMemory)).append("\n");
        infoBuilder.append("maxMemory=" + Formatter.formatFileSize(this, maxMemory)).append("\n");

        tv_memoryInfo.setText(infoBuilder.toString());
    }


    @Override
    protected void onResume() {
        super.onResume();
        memoryInfo();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rw:
                File file = new File(Environment.getExternalStorageDirectory(), "detect.txt");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write("文字内容".getBytes());
                    fos.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_leak:
                startActivity(new Intent(this, TwoActivity.class));
                break;
            case R.id.btn_fun:
//                Debug.startMethodTracing("myTrace2");
                fun();
//                Debug.stopMethodTracing();
                break;
        }
    }

    private void fun() {
        long start = System.currentTimeMillis();
        memoryInfo();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        System.out.println(bitmap);
        String data=null;
        for(int i=0;i<1000;i++){
            data+="->"+i;
        }
        Toast.makeText(this,"完毕",Toast.LENGTH_SHORT).show();
        long dur = System.currentTimeMillis() - start;
        System.out.println("dur="+dur);
    }
}
