package com.review.service;

import android.os.Binder;
import android.util.Log;

/**
 * @author 张全
 */

public class MyBinder extends Binder {
    final String TAG="MyService";
    public void showToast() {
        Log.d(TAG, "MyBinder showToast");
    }
}
