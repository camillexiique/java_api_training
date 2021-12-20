package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ErrorHandler {

    public void bad_request(HttpExchange exchange) throws IOException {
        String error = "Bad request";
        exchange.sendResponseHeaders(400, error.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(error.getBytes());
        }
    }

    public void not_found(HttpExchange exchange) throws IOException {
        String error = "Not found";
        exchange.sendResponseHeaders(404, error.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(error.getBytes());
        }
    }
}

