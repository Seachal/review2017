package com.review.base.demo;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by zhangquan on 17/4/27.
 */

public class CursorAdapterActivity extends Activity {
    String[] columns = new String[]{"_id", "NAME", "username"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cursor_layout);
        ListView listView = (ListView) findViewById(R.id.listview);

        /**
         * 构造MatrixCursor
         * 如果想得到一个Cursor, 而此时又没有数据库返回一个Cursor，此时可以通过MatrixCursor来返回一个Cursor
         */
        MatrixCursor matrixCursor = new MatrixCursor(columns);
        for (int i = 1; i <= 20; i++) {
            matrixCursor.addRow(new Object[]{i, "jack" + i, i + "" + i + "" + i});
        }

        while(matrixCursor.moveToNext()){
            int id = matrixCursor.getInt(matrixCursor.getColumnIndex(columns[0]));
            String username = matrixCursor.getString(matrixCursor.getColumnIndex(columns[1]));
            String pwd = matrixCursor.getString(matrixCursor.getColumnIndex(columns[2]));
            System.out.println("id"+id+",name="+username+",pwd="+pwd);
        }

        MyCursorAdapter adapter = new MyCursorAdapter(this, matrixCursor, true);
        listView.setAdapter(adapter);
    }

    /**
     * 注意：如果使用CursorAdapter，Cursor必须包含一个 _id ,否则会报错
     * bindView会调用多次
     */
    public class MyCursorAdapter extends CursorAdapter{

        public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        public MyCursorAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            System.out.println("---newView");
            View adapterView = getLayoutInflater().inflate(R.layout.cursor_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mUserNameView= (TextView) adapterView.findViewById(R.id.item_name);
            viewHolder.mPwdView= (TextView) adapterView.findViewById(R.id.item_pwd);
            adapterView.setTag(viewHolder);

            return adapterView;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            System.out.println("---bindView");
            ViewHolder viewHodler = (ViewHolder) view.getTag();
            String username = cursor.getString(cursor.getColumnIndex(columns[1]));
            String pwd = cursor.getString(cursor.getColumnIndex(columns[2]));

            viewHodler.mUserNameView.setText(username);
            viewHodler.mPwdView.setText(pwd);

        }
        private class ViewHolder{
            protected TextView mUserNameView;
            protected TextView mPwdView;
        }
    }
}
