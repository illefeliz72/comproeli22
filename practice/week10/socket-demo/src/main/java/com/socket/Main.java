package com.socket;

import com.socket.models.Task;
import com.socket.services.NetworkService;

public class Main {
    public static void main(String[] args) {
        NetworkService ns = new NetworkService();
        String host = "jsonplaceholder.typicode.com";
        int port = 80;
        String path = "/todos/1";

        String response = ns.fetchData(host, port, path);
        Gson gson = new Gson();
        Task task = gson.fromJson(response, classOfT: Task.class);

        System.out.println(task);
    }
} 