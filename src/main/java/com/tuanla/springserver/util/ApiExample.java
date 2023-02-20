package com.tuanla.springserver.util;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ApiExample {
    public static void main(String[] args) throws Exception {
        //doGet("CELOUSDT", "", "", null);
        String url = "http://scn4.dmp.mds:8869/api/v2/ads/sendsms";
        String user = "admin";
        String pwd = "abc54321";
        JSONObject newjs = new JSONObject();
        newjs.put("brandname", "999");
        newjs.put("isdn", "907939668");
        newjs.put("message", "(ND) Dịch vụ ClipTV mời bạn truy cập https://vip.cliptv.vn/holy , đón xem phim bộ ăn khách Trung Quốc \\\"Mùa hè của hồ ly\\\". Đừng quên bạn được MobiFone MIỄN PHÍ hoàn toàn data 3G/4G thoải mái xem và sử dụng dịch vụ. Chi tiết LH 9090. Chúc bạn xem phim vui vẻ!");
        newjs.put("proc_inst_id", "12345678900");
        newjs.put("campaign_id", "999");
        newjs.put("campaign_code", "cltv_202207");
        newjs.put("execution_id", "999");
        System.out.println(newjs.toString());
//        postWithJson(url, user, pwd, newjs.toString());
        System.out.println(okHttpPost(url, user, pwd, newjs));
        //doPostJson("http://scn4.dmp.mds:8869/api/v2/ads/sendsms", "admin", "abc54321", newjs);
    }

    private static String okHttpPost(String url, String username, String password, JSONObject jbody) throws Exception {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jbody.toString());
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(
                        (username + ":" + password).getBytes(StandardCharsets.UTF_8)))
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void doGet(String param, String username, String password, JSONObject body) throws Exception {
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
                COIN coin = gson.fromJson(output, COIN.class);
                System.out.println("COIN: " + coin.toString());
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doPost(String apiUrl, String username, String password, JSONObject body) {
        try {

            URL url = new URL("http://localhost:8080/RESTfulExample/json/product/post");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"qty\":100,\"name\":\"iPad 4\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void postWithJson(String url, String username, String password, String json) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(username, password);
        httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println(response.getStatusLine());
        System.out.println(response.getEntity());
        client.close();
    }

    public class COIN {
        private String symbol;
        private String price;

        public COIN() {
        }

        public COIN(String symbol, String price) {
            this.symbol = symbol;
            this.price = price;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "COIN{" +
                    "symbol='" + symbol + '\'' +
                    ", price='" + price + '\'' +
                    '}';
        }
    }
}
