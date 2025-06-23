package com.devil.ai.chat.controller;

import org.springframework.ai.chat.client.ChatClient;
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
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "msg",defaultValue = "你是谁") String msg){
        return chatClient.prompt() //提示词
                .user(msg) //用户输入信息
                .call() //请求大模型
                .content(); //返回文本
    }
}
