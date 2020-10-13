package com.review.fragment.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.Fragment;
import androidx.core.app.FragmentActivity;

/**
 * @author 张全
 */

public class LifeCycleAct extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        getSupportFragmentManager().beginTransaction().add(new LifeCycleFragment(), "LifeCycleFragment").commit();
    }

    public static class LifeCycleFragment extends Fragment{
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            //当Fragment与Activity发生关联时调用。
            System.out.println("onAttach");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            System.out.println("onCreate");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            System.out.println("onCreateView");
            //创建该Fragment的视图
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            //当Activity的onCreate方法返回时调用
            System.out.println("onActivityCreated");
        }

        @Override
        public void onStart() {
            super.onStart();
            System.out.println("onStart");
        }

        @Override
        public void onResume() {
            super.onResume();
            System.out.println("onResume");
        }

        @Override
        public void onPause() {
            super.onPause();
            System.out.println("onPause");
        }

        @Override
        public void onStop() {
            super.onStop();
            System.out.println("onStop");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            //与onCreateView想对应，当该Fragment的视图被移除时调用
            System.out.println("onDestroyView");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            System.out.println("onDestroy");
        }
        @Override
        public void onDetach() {
            super.onDetach();
            //与onAttach相对应，当Fragment与Activity关联被取消时调用
            System.out.println("onDetach");
        }

    }
}
