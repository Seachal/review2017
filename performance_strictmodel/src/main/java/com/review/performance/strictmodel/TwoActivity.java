package com.review.performance.strictmodel;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author 张全
 */

public class TwoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String data=null;
        for(int i=0;i<1000;i++){
            data=data+"->i";
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程开始休眠");
                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程停止休眠");
            }
        }).start();
    }
}
