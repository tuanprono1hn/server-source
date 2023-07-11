package com.tuanla.springserver.util;

import com.squareup.okhttp.*;
import org.json.JSONObject;

public class ChatGPT {
    public static final OkHttpClient client = ApiClient.getOKClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws Exception {
        String strTest = "{\n" +
                "    \"id\": \"chatcmpl-7ZkjvUGmGniu3oLnmqOyEefRCxGvn\",\n" +
                "    \"object\": \"chat.completion\",\n" +
                "    \"created\": 1688755503,\n" +
                "    \"model\": \"gpt-3.5-turbo-0613\",\n" +
                "    \"choices\": [\n" +
                "        {\n" +
                "            \"index\": 0,\n" +
                "            \"message\": {\n" +
                "                \"role\": \"assistant\",\n" +
                "                \"content\": \"1. Spring Boot là gì và giới thiệu về đặc điểm của nó?\\n2. Tại sao lại sử dụng Spring Boot trong phát triển ứng dụng?\\n3. Sự khác biệt giữa Spring Boot và Spring Framework là gì?\\n4. Spring Boot Starter là gì và cách sử dụng nó?\\n5. Làm thế nào để tạo một ứng dụng web đơn giản bằng Spring Boot?\\n6. Dependency Injection được sử dụng như thế nào trong Spring Boot?\\n7. Cách cấu hình cơ sở dữ liệu trong Spring Boot là gì?\\n8. Hiểu về Spring Boot Actuator và những chức năng nổi bật của nó?\\n9. Làm thế nào để tạo các API RESTful trong Spring Boot?\\n10. Các bước để triển khai ứng dụng Spring Boot?\\n11. Tại sao chúng ta nên sử dụng Spring Boot DevTools?\\n12. Làm thế nào để cấu hình tính năng bảo mật trong ứng dụng Spring Boot?\\n13. Làm thế nào để tạo một ứng dụng di động bằng Spring Boot?\\n14. Spring Boot hỗ trợ những dạng testing nào?\\n15. Làm thế nào để tạo một ứng dụng microservices bằng Spring Boot?\"\n" +
                "            },\n" +
                "            \"finish_reason\": \"stop\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"usage\": {\n" +
                "        \"prompt_tokens\": 25,\n" +
                "        \"completion_tokens\": 373,\n" +
                "        \"total_tokens\": 398\n" +
                "    }\n" +
                "}";
        JSONObject jTest = new JSONObject(strTest);
        String output = jTest.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
//        System.out.println(output);
//        TelegramMessage.send("1. Spring Boot là gì và giới thiệu về đặc điểm của nó?");
        String input = "are you ok?";
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
            JSONObject jTest = new JSONObject(response.body().string());
            String reply = jTest.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
//            System.out.println(reply);
            TelegramMessage.send("OpenAI:");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw ex;
        }
    }
}
