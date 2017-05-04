package com.review.base.demo;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zhangquan on 17/5/2.
 */

public class RuntimeExecActivity extends Activity {
    private static String path = "/Users/zhangquan/Library/Android/sdk/platform-tools ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String wifiIp = getWifiIp();
        System.out.println("ip=" + wifiIp);
        //        restartADB();
//        getWIFIIP();
//        adbTcpip();
//        adbWificonnect("");
    }

    private String getWifiIp() {
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        int ipAddress = connectionInfo.getIpAddress();
        return intToIp(ipAddress);

    }

    private String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    private static void restartADB() {
        try {
            Runtime.getRuntime().exec("adb kill-server");
            Runtime.getRuntime().exec("adb start-server");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getWIFIIP() {

        try {
            Process process = Runtime.getRuntime().exec("adb shell");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean adbTcpip() {
        try {
            Process process = Runtime.getRuntime().exec("adb tcpip 5555");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if (line.contains("error")) {
                    return false;
                }
                return true;
            }


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean adbWificonnect(String ipAddress) {
        boolean connected = false;
        try {
            Process process = Runtime.getRuntime().exec("adb connect " + ipAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            String message = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if (line.contains("connected")) {
                    connected = true;
                }
                message = line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
