package com.tuanla.springserver.util;

import com.squareup.okhttp.*;
import org.json.JSONObject;

public class ChatGPT {
    public static final OkHttpClient client = ApiClient.getOKClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws Exception {
        String input = "ChatGPT là gì";
        chatGPT(input);
    }

    public static void chatGPT(String input) throws Exception {
        try {
            RequestBody body = RequestBody.create(JSON, "{\r\n  \"model\": \"gpt-3.5-turbo\",\r\n  \"messages\": [\r\n    {\r\n      \"role\": \"user\",\r\n      \"content\": \"" + input + "\"\r\n    }\r\n  ]\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.openai.com/v1/chat/completions")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer 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")
                    .build();
            Response response = client.newCall(request).execute();
            String reply = new JSONObject(response.body().string()).getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
            TelegramMessage.send(reply);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw ex;
        }
    }
}
