package com.lmx.project.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.lmx.project.common.ErrorCode;
import com.lmx.project.exception.BusinessException;
import com.lmx.project.model.entity.ChatModel;
import com.lmx.project.util.ChatGptUntil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("chat")
public class ChatGptController {

    @Resource
    private ChatGptUntil chatGptUntil;

    @PostMapping
    public void GetChar(String chatModelList, HttpServletResponse response) throws IOException {
        if (!StringUtils.isNotBlank(chatModelList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Gson gson = new Gson();
        List<ChatModel> chatModels = (List<ChatModel>) gson.fromJson(chatModelList, new TypeToken<List<ChatModel>>() {
        }.getRawType());

//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Cache-Control", "no-cache");

        chatGptUntil.getRespost(chatModels, response);

    }
}
