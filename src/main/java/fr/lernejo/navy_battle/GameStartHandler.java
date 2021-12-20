package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;





class GameStartHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ErrorHandler error = new ErrorHandler();
        JSONMethods methods = new JSONMethods();
        if (exchange.getRequestMethod().equals("POST")){

                if(methods.checkJSONFormat(exchange.getRequestBody())){
                    methods.sendJSONForm(exchange);
                    return;
                }
            error.bad_request(exchange);
        } else{error.not_found(exchange);}
    }

}
