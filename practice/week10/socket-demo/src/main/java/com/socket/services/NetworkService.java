package com.socket.services;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkService {
    private String host;
    private int port;

    public NetworkService(String host,int port){
        this.host = host;
        this.port = port;
    }
    public NetworkService() {
    }
    public String fetchData(String host, int port, String path) {
        StringBuilder response = new StringBuilder();
        // socket
        try (Socket socket = new Socket(host, port);
                PrintWriter requestWriter = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            System.out.print("Connected to Server...");
            // send a https req; method,path ,protocol version,host header
            requestWriter.print("GET " + path + "HTTP/1.1");
            requestWriter.print("Host: " + host + "\r\n");
            requestWriter.print("User-Agent: Java/SocketDemo\r\n");
            requestWriter.print("Accept: application/json\r\n");
            requestWriter.print("Connection: close \r\n");
            requestWriter.print("\r\n"); // ends the request header
            System.out.println("\n--- HTTP Response Headers ---");

            String line;
            boolean isBody = false;
            while ((line = responseReader.readLine()) != null) {
                if (line.isEmpty() && !isBody) {
                    isBody = true;
                    System.out.println("---JSON---");
                    continue;
                }
                if (isBody)
                    response.append(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
    public void sendData(String path,String content){
        
    }
}
