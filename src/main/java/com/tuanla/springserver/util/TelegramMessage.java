package com.tuanla.springserver.util;

import com.google.gson.Gson;
import com.tuanla.springserver.entity.other.Coin;
import com.tuanla.springserver.entity.other.TelegramResponse;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TelegramMessage {
    private static final String binanceApiUrl = "https://api.binance.com/api/v3/ticker/price";
    private static final String telegramGroupInfo = "https://api.telegram.org/bot%s/getUpdates";
    private static final String telegramApiUrl = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&parse_mode=%s&text=%s";
    private static final String telegramBotToken = "5331971662:AAHGlrzp4mWM8IyUyEz25PJaq5Kh7TxOLGQ";
    private static final String telegramChatId = "-1001867127542";
    private static final String parseMode = "MarkDown";

    public static void send(String msg) {
        String urlString = String.format(telegramApiUrl, telegramBotToken, telegramChatId, parseMode, msg);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            is.close();
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
                Coin coin = gson.fromJson(output, Coin.class);
                strCoin = coin.getSymbol() + " = " + Float.valueOf(coin.getPrice());
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strCoin;
    }

    public static String doGet(String... str) throws Exception {
        StringBuilder msg = new StringBuilder();
        try {
            URL url = new URL(binanceApiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> data = mapper.readValue(sb.toString(), new TypeReference<List<Map<String, Object>>>() {
            });
            for (Map map : data) {
                for (String s : str) {
                    if (s.toLowerCase(Locale.ROOT).equals(map.get("symbol").toString().toLowerCase(Locale.ROOT))) {
                        msg.append(map.get("symbol").toString()).append(" ").append("`").append(Float.valueOf(map.get("price").toString())).append("`").append("%0A");
                    }
                }
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg.toString();
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
            System.out.println(StringUtils.stripToNull(response.getResultArr().get(0).getMessage().get(0).getChat().getId()));
            System.out.println(StringUtils.stripToNull(output));
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
        }
        return output;
    }

    public static void main(String[] args) throws Exception {
//        getGroupId(telegramGroupInfo, telegramBotToken);
        send(doGet("CELOUSDT", "BTCUSDT"));

//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 11);
//        calendar.set(Calendar.MINUTE, 16);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        Date dateSchedule = calendar.getTime();
//        long period = 24 * 60 * 60 * 1000;
//
//        TimerTask myTask = new TimerTask() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                TelegramMessage.send(doGet("CELOUSDT", "BTCUSDT"));
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(myTask, dateSchedule, period);
    }
}
