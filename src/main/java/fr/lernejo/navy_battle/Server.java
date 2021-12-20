package fr.lernejo.navy_battle;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;



class Server  {

    //port par défaut
    int port = 8000;
    String url = "";
    String adversary_url = "";

    Server(String new_port) {
        this.port = Integer.parseInt(new_port);
        this.adversary_url = "http://localhost:"+new_port;
    }

    public void create() throws IOException, InterruptedException {
        final HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);

        server.createContext("/ping", new CallHandler());
        server.createContext("/api/game/start", new GameStartHandler());
        

        server.setExecutor(Executors.newFixedThreadPool(1));
        server.start();
        if (!url.equals("")){
            post();
        }

    }

    public void get_url(String url){
        this.url = url;

    }

    private void post() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest post = HttpRequest.newBuilder()
            .uri(URI.create(this.adversary_url + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"69\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"Hi John\"}"))
            .build();
        try {
            client.send(post, HttpResponse.BodyHandlers.ofString());
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }

}
