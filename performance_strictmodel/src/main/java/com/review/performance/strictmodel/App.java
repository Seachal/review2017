package com.review.performance.strictmodel;

import android.app.Application;
import android.os.StrictMode;

/**
 * @author 张全
 */

public class App extends Application {
    @Override
    public void onCreate() {

        if (BuildConfig.DEBUG) {
            /**
             * 针对线程的相关策略
             * 线程策略主要用于检测磁盘IO和网络访问
             */
            StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .detectAll()
                    .penaltyLog() //penaltyLog()表示将警告输出到LogCat
                    .penaltyDialog()
                    .build();
            StrictMode.setThreadPolicy(threadPolicy);

            /**
             *  针对VM的相关策略
             *  虚拟机策略主要用于检测内存泄漏现象
             */
            StrictMode.VmPolicy vmPolicy = new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build();
            StrictMode.setVmPolicy(vmPolicy);

            /*
             * penaltyLog：表示将警告输出到LogCat
             * penaltyDeath：一旦StrictMode消息被写到LogCat后应用就会崩溃
             */

            /*
            -----------暂停监测-----------
            如果在程序运行中无法避免的会违反StrictMode中的一些定义好的策略，而我们又希望能够暂时忽略这些策略的监视，
            我们可以使用permitXXXXX方法来暂停这些内容的监测，在做完需要忽略的监测之后，再起用监测，代码如下所示：
            StrictMode.ThreadPolicy old = StrictMode.getThreadPolicy();
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(old)
                    .permitDiskWrites() //允许在主线程中写入硬盘
                    .build());
            //doSomethingWriteToDisk();  //执行硬盘写入操作
            StrictMode.setThreadPolicy(old); //恢复之前的策略
             */
        }
        super.onCreate();
    }
}
