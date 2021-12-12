package fr.lernejo.navy_battle;


import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;



class Server  {

    //port par défaut
    int port = 8000;

    Server(String new_port) {
        this.port = Integer.parseInt(new_port);
    }

    public void create() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 1);
        server.createContext("/ping", new CallHandler());
        server.start();
    }



}
