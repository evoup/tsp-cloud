package com.evoupsight;

import com.evoupsight.services.TspService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TSP服务器
 *
 */
public class TspServer
{
    public static void main( String[] args )
    {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("TSP server start at port 8888");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("client connected");
                TspService myService = new TspService(socket);
            }
        } catch (IOException e) {
            System.err.println("TSP server, error:" + e);
        }
    }
}
