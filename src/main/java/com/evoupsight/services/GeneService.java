package com.evoupsight.services;

import com.evoupsight.TspCloud;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Hashtable;

/**
 * Created by evoup on 16-6-10.
 * 业务线程
 */
public class GeneService extends Thread {
    DataOutputStream dos;
    InputStreamReader isr;

    public GeneService(Socket socket) {
        try {
            isr = new InputStreamReader(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void run() {
        //Code
        System.out.println("running");
        try {
            while (true) {
                // 客户端传递的城市数据，如A_B_C_D_E_F_G
                BufferedReader br = new BufferedReader(isr);
                String data;
                data = br.readLine();
                System.out.println("data from client:" + data);
                Hashtable cityTable = new Hashtable();
                if (data!=null && !data.isEmpty()) {
                    // 下划线分割client传递的城市代码
                    String[] cities = data.split("_");
                    // 城市代码存到HashTable里
                    for (int i=0; i<cities.length;i++) {
                        // 去重
                        if (!cityTable.containsKey(cities[i])) {
                            cityTable.put(String.valueOf(cities[i].charAt(0)), String.valueOf(cities[i].charAt(0)));
                        }
                    }
                }
                // 建立云计算，取得计算结果
                if (cityTable.size()>0) {
                    String res = new TspCloud(cityTable).doCloudOpertion();
                    dos.writeBytes(res + "\r\n");
                } else {
                    dos.writeBytes("data not correct\r\n");
                }
                isr.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
