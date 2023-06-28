package com.lmx.project.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.lmx.project.common.ErrorCode;
import com.lmx.project.exception.BusinessException;
import com.lmx.project.model.entity.ChatModel;
import com.lmx.project.util.ChatGptUntil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("chat")
public class ChatGptController {

    @Resource
    private ChatGptUntil chatGptUntil;





    @PostMapping(produces = "text/event-stream;charset=UTF-8")
    public void GetChar(String chatModelList, HttpServletResponse response) throws IOException {
        if (!StringUtils.isNotBlank(chatModelList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Gson gson = new Gson();
        List<ChatModel> chatModels = (List<ChatModel>) gson.fromJson(chatModelList, new TypeToken<List<ChatModel>>() {
        }.getRawType());

        if (chatModelList == null || chatModelList.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/event-stream");
        chatGptUntil.getRespost(chatModels, response);
    }
}
