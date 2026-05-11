package com.artvantage.network;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WebDataModule {
    public static String getDailyInspiration() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.quotable.io/random"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            
            return jsonObject.get("content").getAsString() + " — " + jsonObject.get("author").getAsString();
        } catch (Exception e) {
            return "Keep it up bro Your art matters.";
        }
    }
}