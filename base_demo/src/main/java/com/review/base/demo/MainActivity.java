package com.review.base.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void matrixCursor(View view) {
        startActivity(new Intent(this, CursorAdapterActivity.class));
    }

    public void runtimeExec(View view) {
        startActivity(new Intent(this,RuntimeExecActivity.class));
    }

    public void inflateView(View view) {
        startActivity(new Intent(this, LayoutInflaterActivity.class));
    }
}
