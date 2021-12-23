package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FireHandler implements HttpHandler {
    final String adversary_url = "http://localhost:8000";




    @Override
    public void handle(HttpExchange exchange) throws IOException {
        int nb_ship = 10;
        Boolean shipleft = true;
        String consquence = "miss";
        if (exchange.getRequestMethod().equals("GET")){
        String cell = get_cell(exchange);
        if (is_hit(cell)){
            consquence = "hit";
            if (is_sunk(cell)){
                consquence = "sunk";
                nb_ship -= 1;
                shipleft = shipLeft(nb_ship);
            }}

        }
        try {
            post(consquence, shipleft);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}


    private boolean is_hit(String cell){
//TODO
        return false;
    }

    private boolean is_sunk(String cell){
//TODO
        return false;
    }

    private boolean shipLeft(int nb_ship){

        if (nb_ship == 0){

            return false;
        }
        return true;
    }

    private String get_cell(HttpExchange exchange) throws IOException {
        String cell;
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject json_string = new JSONObject(responseStrBuilder.toString());
        cell = json_string.getString("cell");
        return cell;
    }

    private void post(String consquence, Boolean shipleft) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest post = HttpRequest.newBuilder()
            .uri(URI.create(this.adversary_url + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"consequence\":\""+consquence+"\", \"shipLeft\":\""+shipleft+"\"}"))
            .build();
        try {
            client.send(post, HttpResponse.BodyHandlers.ofString());
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
