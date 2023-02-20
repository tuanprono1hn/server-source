package com.tuanla.springserver.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/image/folow")
public class HttpCilent extends HttpServlet {
    private HttpClient client = HttpClientBuilder.create().build();
    private final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0";

    public static String START_PROCESS_URL = "/app/authentication";
    static String username = "";
    static String password = "";
    static String base_url = "";

    static {
        System.out.println("Hello user...");
        GetPropertyValues prop = new GetPropertyValues();
        try {
            base_url = prop.getPropValues("activiti.app.url");
            username = prop.getPropValues("activiti.app.j_username");
            password = prop.getPropValues("activiti.app.j_password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void isLogin() throws ClientProtocolException, IOException {
        String urlLogin = base_url + START_PROCESS_URL;
        HttpPost post = new HttpPost(urlLogin);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        // Set Param
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("j_username", username));
        urlParameters.add(new BasicNameValuePair("j_password", password));
        urlParameters.add(new BasicNameValuePair("_spring_security_remember_me", "true"));
        urlParameters.add(new BasicNameValuePair("submit", "Login"));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        HttpResponse response = client.execute(post);
    }

    public String getProcessefinitions() throws ClientProtocolException, IOException {
        String url = base_url + "/app/rest/process-definitions?latest=true";
        isLogin();
        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    public String getServiceClassbyProcessefinitions(String processdefinitionkey)
            throws ClientProtocolException, IOException {
        String url = base_url + "/app/rest/allservicetask?processdefinitionkey=" + processdefinitionkey;
        isLogin();
        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String processdefinitionkey = request.getParameter("processdefinitionkey");
        String url = base_url + "/app/custom/processdefinitionkey/" + processdefinitionkey + "/diagram";
        isLogin();
        HttpGet requestActiviti = new HttpGet(url);
        requestActiviti.addHeader("User-Agent", USER_AGENT);
        requestActiviti.addHeader("User-Agent", USER_AGENT);
        HttpResponse responseActiviti = client.execute(requestActiviti);
        InputStream resource = responseActiviti.getEntity().getContent();
        response.setContentType("image/png");
        response.getOutputStream().write((IOUtils.toByteArray(resource)));
    }

}
