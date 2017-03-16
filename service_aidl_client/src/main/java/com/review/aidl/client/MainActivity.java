package com.review.aidl.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.review.aidl.client.bean.Person;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_bindService).setOnClickListener(this);
        findViewById(R.id.btn_callService).setOnClickListener(this);
        findViewById(R.id.btn_unbindService).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bindService:
                /**
                 * 测试与本应用内其他进程中的Service绑定
                 */
                Intent intent = new Intent(this, MyAIDLService.class);
                bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_callService:
                if(null!=myAIDL){
                    Person person = new Person();
                    person.setId(1);
                    person.setName("张三");
                    person.setGender("男");
                    try {
                        myAIDL.greet(person);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_unbindService:
                myAIDL=null;
                unbindService(serviceConnection);
                break;
        }
    }

    MyAIDL myAIDL;
    ServiceConnection  serviceConnection= new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("MyService","onServiceConnected,service="+service);
            //service=android.os.BinderProxy
             myAIDL = MyAIDL.Stub.asInterface(service);
            Log.d("MyService","onServiceConnected,myAIDL="+myAIDL);
            try {
                String info = myAIDL.getInfor("hello world");
                Log.d("MyService","onServiceConnected,info="+info);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("MyService","onServiceDisconnected");
        }
    };
}
