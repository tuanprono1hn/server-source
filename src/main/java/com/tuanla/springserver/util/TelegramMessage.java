package com.tuanla.springserver.util;

import com.google.gson.Gson;
import com.tuanla.springserver.entity.other.TelegramResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TelegramMessage {
    private static final String binanceApiUrl = "https://api.binance.com/api/v3/ticker/price";
    private static final String telegramGroupInfo = "https://api.telegram.org/bot%s/getUpdates";
    private static final String telegramApiUrl = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
    private static final String telegramBotToken = "5331971662:AAHGlrzp4mWM8IyUyEz25PJaq5Kh7TxOLGQ";
    private static final String telegramChatId = "-1001867127542";

    public static void send(String msg) {
        String urlString = String.format(telegramApiUrl, telegramBotToken, telegramChatId, msg);

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
            HttpGet httpGet = new HttpGet(binanceApiUrl);
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

    private static String getGroupId(String strUrl, String token) throws Exception {
        TelegramResponse response = new TelegramResponse();
        String urlString = String.format(strUrl, token);
        String output = null;
        
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            output = sb.toString();
            Gson gson = new Gson();
            response = gson.fromJson(output, TelegramResponse.class);
            System.out.println(response.getResultArr().get(0).getMessage().get(0).getChat().getId());
            br.close();
            
//            while ((output = br.readLine()) != null) {
//                System.out.println(br.readLine());
//                Gson gson = new Gson();
//                response = gson.fromJson(output, TelegramResponse.class);
//
////                JSONObject jsonObject = new JSONObject(output);
////                response = jsonObject.getJSONArray("message").getJSONObject(2).getJSONObject("chat").get("id").toString();
//            }

            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return output;
        }
    }

    public static void main(String[] args) throws Exception {
        getGroupId(telegramGroupInfo, telegramBotToken);
        send(doGet("BTCUSDT", "", "", null));
    }
}
