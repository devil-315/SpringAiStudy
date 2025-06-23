package com.devil.ai.controller;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName：ChatDeepSeekController
 *
 * @author: Devil
 * @Date: 2025/6/21
 * @Description:
 * @version: 1.0
 */
@RestController
public class ChatDeepSeekController {
    @Autowired
    private OpenAiChatModel chatModel;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "message",defaultValue = "hello")
                        String message){
        String result = chatModel.call(message);
        System.out.println(result);
        return  result;
    }
}
