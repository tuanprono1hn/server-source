package com.tuanla.springserver.util;

import com.squareup.okhttp.*;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.json.JSONObject;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class ApiClient {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ApiClient.class);
    private static final int CONNECT_TIMEOUT = 60000;
    private static final int REQUEST_TIMEOUT = 60000;
    private static final int SOCKET_TIMEOUT = 60000;
    private static final int MAX_TOTAL_CONNECTIONS = 200;
    private static final int DEFAULT_KEEP_ALIVE_TIME_MILLIS = 20 * 1000;
    private static final int CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS = 30;

    public static void main(String[] args) throws Exception {
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
        System.out.println(okHttpPostBasicAuth(url, user, pwd, newjs));
        //System.out.println(okHttpGet("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT"));
    }

    public static OkHttpClient getOKClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        client.setWriteTimeout(REQUEST_TIMEOUT, TimeUnit.MILLISECONDS);
        client.setReadTimeout(SOCKET_TIMEOUT, TimeUnit.MILLISECONDS);
        return client;
    }

    public static String okHttpGet(String url) throws Exception {
        OkHttpClient client = getOKClient();
        //MediaType mediaType = MediaType.parse("text/plain");
        //RequestBody body = RequestBody.create(mediaType, "");
        //RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String okHttpGetBasicAuth(String url, String username, String password) throws Exception {
        OkHttpClient client = getOKClient();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("GET", body)
                .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(
                        (username + ":" + password).getBytes(StandardCharsets.UTF_8)))
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String okHttpPost(String url, JSONObject jbody) throws Exception {
        OkHttpClient client = getOKClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jbody.toString());
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String okHttpPostBasicAuth(String url, String username, String password, JSONObject jbody) throws Exception {
        OkHttpClient client = getOKClient();
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

    public static HttpResponse httpPost(String strUrl, JSONObject jsonPost) throws Exception {
        HttpPost post = new HttpPost(strUrl);
        StringEntity postingString;
        postingString = new StringEntity(jsonPost.toString(), "UTF-8");
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json;charset=UTF-8");
        return httpClient().execute(post);
    }

    public static HttpResponse httpPostBasicAuth(String strUrl, String username, String password, JSONObject jsonPost) throws Exception {
        HttpResponse response = null;
        String auth = username + ":" + password;
        byte[] encodedAuth = org.apache.commons.codec.binary.Base64.encodeBase64(
                auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        HttpPost post = new HttpPost(strUrl);
        post.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        StringEntity postingString;
        postingString = new StringEntity(jsonPost.toString(), "UTF-8");
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json;charset=UTF-8");
        return httpClient().execute(post);
    }

    public static HttpResponse httpPostBasicAuth(String strUrl, String username, String password, String request) throws Exception {
        HttpResponse response = null;
        String auth = username + ":" + password;
        byte[] encodedAuth = org.apache.commons.codec.binary.Base64.encodeBase64(
                auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        HttpPost post = new HttpPost(strUrl);
        post.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        StringEntity postingString;
        postingString = new StringEntity(request, "UTF-8");
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json;charset=UTF-8");
        return httpClient().execute(post);
    }

    public static PoolingHttpClientConnectionManager poolingConnectionManager() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            HostnameVerifier allowAllHosts = new NoopHostnameVerifier();
            sslsf = new SSLConnectionSocketFactory(createSslCustomContext(), allowAllHosts);
        } catch (Exception e) {
            e.printStackTrace();
            //LOGGER.error("Pooling Connection Manager Initialisation failure because of " + e.getMessage(), e);
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();

        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);
        poolingConnectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        poolingConnectionManager.setDefaultMaxPerRoute(MAX_TOTAL_CONNECTIONS);
        return poolingConnectionManager;
    }

    public static SSLContext createSslCustomContext() throws KeyStoreException, IOException, NoSuchAlgorithmException,
            CertificateException, KeyManagementException, UnrecoverableKeyException {
        SSLContext sslcontext = SSLContexts.custom().build();
        return sslcontext;
    }

    public static CloseableHttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        return HttpClients.custom().setDefaultRequestConfig(requestConfig)
                .setConnectionManager(poolingConnectionManager())
                .build();
    }

    public static CloseableHttpClient httpClient(String username, String password) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        return HttpClients.custom().setDefaultRequestConfig(requestConfig).setDefaultCredentialsProvider(provider)
                .setConnectionManager(poolingConnectionManager())
                .build();
    }
}
