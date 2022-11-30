package com.example.study;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;


public class TcpIpMultichatServer {
    HashMap clients;

    TcpIpMultichatServer(){
        clients = new HashMap();
        Collections.synchronizedMap(clients);//동기화 작업
    }
    public void start(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
