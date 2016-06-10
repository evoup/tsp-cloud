package com.evoupsight.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by evoup on 16-6-10.
 */
public class TspService extends Thread {

    DataInputStream dis;
    DataOutputStream dos;

    public TspService(Socket socket) {
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void run() {
        //Code
        System.out.println("running");
    }


}
