package com.review.aidl.client;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.review.aidl.client.bean.Person;

/**
 * @author 张全
 */

public class MyAIDLService extends Service {
    public final static String TAG = "MyService";

    /**
     * MyAIDL.Stub
     */
    private  MyAIDL.Stub binder = new MyAIDL.Stub() {
        @Override
        public String getInfor(String s) throws RemoteException {
            Log.d(TAG, "MyAIDLService s="+s);
            return "我是 aidl 接口返回的字符串";
        }

        @Override
        public String greet(Person person) throws RemoteException {
            Log.d(TAG, "MyAIDLService person="+person+",this="+this);
            return "greet接口调用成功";
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyAIDLService onBind");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyAIDLService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyAIDLService onStartCommand");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyAIDLService onDestroy");
    }
}
