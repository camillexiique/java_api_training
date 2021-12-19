package fr.lernejo.navy_battle;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;



class Server  {

    //port par défaut
    int port = 8000;

    Server(String new_port) {
        this.port = Integer.parseInt(new_port);
    }

    public void create() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);
        server.createContext("/ping", new CallHandler());
        server.createContext("/api/game/start", new GameStartHandler());

        server.setExecutor(Executors.newFixedThreadPool(1));
        server.start();
    }



}
