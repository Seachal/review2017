package com.review.datastorage.sp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.review.datastorage.R;
import com.review.datastorge.sphelper.SPHelper;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 张全
 */

public class SPActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shareference);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_sphelper).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                SPUtil.setString(this, "username", "张三");
                startService(new Intent(this, SPProcessService.class));
                break;
            case R.id.btn_get:
                String password = SPUtil.getString(this, "password");
                System.out.println("password=" + password);
                break;
            case R.id.btn_sphelper:
                SPHelper.init(getApplication());
                break;
        }
    }

    public void saveIntAndBool(View view) {
        long start = System.currentTimeMillis();
        SPHelper.save("a", 100);
        SPHelper.save("z", true);
        long end = System.currentTimeMillis();
        Toast.makeText(this, "takes " + (end - start) + "millis", Toast.LENGTH_SHORT).show();
    }

    public void saveStringAndFloat(View view) {
        long start = System.currentTimeMillis();
        SPHelper.save("b", "aaa");
        SPHelper.save("x", 1.001f);
        long end = System.currentTimeMillis();
        Toast.makeText(this, "takes " + (end - start) + "millis", Toast.LENGTH_SHORT).show();
        ;
    }

    public void saveStringSet(View view) {
        Set<String> set = new HashSet<>();
        set.add("aaa,bbb");
        set.add("ccc");
        long start = System.currentTimeMillis();
        SPHelper.save("c", set);
        long end = System.currentTimeMillis();
        Toast.makeText(this, "takes " + (end - start) + "millis", Toast.LENGTH_SHORT).show();
        ;
    }

    public void clean(View view) {
        long start = System.currentTimeMillis();
        SPHelper.clear();
        long end = System.currentTimeMillis();
        Toast.makeText(this, "takes " + (end - start) + "millis", Toast.LENGTH_SHORT).show();
        ;
    }

    public void getValue(View view) {
        long start = System.currentTimeMillis();

        Map<String, ?> map = SPHelper.getAll();

//        String result1 = "a="+SPHelper.getInt("a",0);
//        String result2 = "b="+SPHelper.getString("b","");
//
//        Set<String> set = SPHelper.getStringSet("c",null);

        long end = System.currentTimeMillis();
        Toast.makeText(this, "takes " + (end - start) + "millis", Toast.LENGTH_SHORT).show();
        ;
        String result = "";
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String k = entry.getKey();
            Object v = entry.getValue();
            if (v instanceof Set) {
                String result3 = "";
                for (String string : (Set<String>) v) {
                    result3 += "\"" + string + "\"";
                    result3 += "    ";
                }
                v = result3;
            }
            result += k + "=" + v + "\n";
        }


        System.out.println(result);
    }
}
