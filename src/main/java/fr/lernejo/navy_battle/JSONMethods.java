package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;


import java.io.*;

public class JSONMethods {
    public boolean checkJSONFormat(InputStream message_input) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(message_input, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject json_string = new JSONObject(responseStrBuilder.toString());
        String id = json_string.getString("id");
        String url = json_string.getString("url");
        String message = json_string.getString("message");
        if( id != null && url != null && message != null)
        {
            return true;
        }
        return false;}

    public void sendJSONForm(HttpExchange exchange) throws IOException {
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
}
