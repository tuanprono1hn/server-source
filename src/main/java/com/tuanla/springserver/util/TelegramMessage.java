package com.tuanla.springserver.util;

import com.google.gson.Gson;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TelegramMessage {
    public static void sendToTelegram(String msg) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
        String apiToken = "5331971662:AAHGlrzp4mWM8IyUyEz25PJaq5Kh7TxOLGQ";
        String chatId = "-1001867127542";

        urlString = String.format(urlString, apiToken, chatId, msg);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String doGet(String param, String username, String password, JSONObject body) throws Exception {
        String strCoin = "";
        try {
            //URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol=" + apiUrl);
            HttpGet httpGet = new HttpGet("https://api.binance.com/api/v3/ticker/price");
            URI uri = new URIBuilder(httpGet.getURI())
                    .addParameter("symbol", param)
                    .build();
            URL url = new URL(uri.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization",
                    "Basic " + Base64.getEncoder().encodeToString(
                            (username + ":" + password).getBytes(StandardCharsets.UTF_8)
                    )
            );

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                Gson gson = new Gson();
                ApiExample.COIN coin = gson.fromJson(output, ApiExample.COIN.class);
                strCoin = coin.getSymbol() + " = " + Float.valueOf(coin.getPrice()).floatValue();
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return strCoin;
        }
    }

    public static void main(String[] args) throws Exception {
        sendToTelegram(doGet("CELOUSDT", "", "", null));
    }
}
