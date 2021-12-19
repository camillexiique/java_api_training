package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;


import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GameStartHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")){
            try {
                if(checkJSONFormat(exchange.getRequestBody())){
                    sendJSONForm(exchange);
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ErrorHandler("Bad Request", exchange);
        }
        else{
            ErrorHandler("Not Found", exchange);
        }
    }

    private boolean checkJSONFormat(InputStream message_input) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject json_string = (JSONObject)jsonParser.parse(
            new InputStreamReader(message_input, "UTF-8"));

        String id = json_string.getString("id");
        String url = json_string.getString("url");
        String message = json_string.getString("message");
        if( id != null && url != null && message != null)
        {
            return true;
        }
        return false;
    }

    private void sendJSONForm(HttpExchange exchange) throws IOException {
        int port = exchange.getHttpContext().getServer().getAddress().getPort();
        JSONObject next_body = new JSONObject();
        next_body.put("id", "001");
        next_body.put("url", "http://localhost:"+port);
        next_body.put("message", "Hello Hell");
        exchange.sendResponseHeaders(202, next_body.toString().length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(next_body.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void ErrorHandler(String error, HttpExchange exchange) throws IOException {
        if (error.equals("Bad Request")){
            exchange.sendResponseHeaders(400, error.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(error.getBytes());
            }
        }
        else if (error.equals("Not Found")){
            exchange.sendResponseHeaders(404, error.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(error.getBytes());
            }
        }
    }
}
