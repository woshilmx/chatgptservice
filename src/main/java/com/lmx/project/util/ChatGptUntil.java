package com.lmx.project.util;

import com.google.gson.*;
import com.lmx.project.common.BaseResponse;
import com.lmx.project.common.ResultUtils;
import com.lmx.project.model.entity.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatGptUntil {

    @Value("${openai.token}")
    private String ApiKey;
//    @Value("${proxy.host}")
//    private String host;
//    @Value("${proxy.port}")
//    private int port;
    public static Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();
    //    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//    @Resource
//    private ChatService chatService;

    /**
     * 获取chatgpt的答案
     */
//    @Async
    public void getRespost(List<ChatModel> messagelist, HttpServletResponse response) throws IOException {
//        System.out.println("对话内容" + messagelist.toString());
//        System.out.println(ApiKey + "\n" + host + "\n" + port);
////        建立连接
        String url = "https://api.openai.com/v1/chat/completions";
        HashMap<String, Object> bodymap = new HashMap<>();

        bodymap.put("model", "gpt-3.5-turbo");
        bodymap.put("temperature", 0.7);
//        bodymap.put("stream",true);
        bodymap.put("messages", messagelist);
        bodymap.put("stream", true);
        Gson gson = new Gson();
        String s = gson.toJson(bodymap);
//        System.out.println(s);
        URL url1 = new URL(url);
//        new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port))
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + ApiKey);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("stream", "true");
        conn.setDoOutput(true);
//    写入请求参数
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, Charset.forName("UTF-8")));
        writer.write(s);
        writer.close();
        os.close();

        InputStream inputStream = conn.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
//        System.out.println("开始回答");
        StringBuffer answoer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
//            System.out.println(line);
            line = line.replace("data:", "");
            JsonElement jsonElement = JsonParser.parseString(line);
            if (!jsonElement.isJsonObject()) {
                continue;
            }
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            JsonArray choices = asJsonObject.get("choices").getAsJsonArray();
            if (choices.size() > 0) {
                JsonObject choice = choices.get(0).getAsJsonObject();
                JsonObject delta = choice.get("delta").getAsJsonObject();
                if (delta != null) {
//                    System.out.println(delta);
                    if (delta.has("content")) {
//                        发送消息
                        String content = delta.get("content").getAsString();

//                        System.out.printf("%s", content);
//                        BaseResponse<String> success = ResultUtils.success(content);
//                        String s1 = gson.toJson(content);
//                        HashMap<String, String> stringStringHashMap = new HashMap<>();
//                        stringStringHashMap.put("data", content);
//                        stringStringHashMap.put("event", null);
//                        stringStringHashMap.put("id", null);
//                        stringStringHashMap.put("retry", null);
//                        String s1 = gson.toJson(stringStringHashMap);
                        response.getWriter().write(content);
                        response.getWriter().flush();

//
//                        WebSocket webSocket = new WebSocket();

//                        webSocket.sendMessageByUserId(conversionid, gson.toJson(success));
//                        answoer.append(content);
//                        webSocket.sendOneMessage(userid, success);
//                        webSocket.sendOneMessage(userid, success);
//                      打印在控制台中

                    }
                }
            }

        }
//        String context = answoer.toString();


    }


    public static void main(String[] args) throws IOException {


    }


}
