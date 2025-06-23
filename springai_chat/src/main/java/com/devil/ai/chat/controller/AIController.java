package com.devil.ai.chat.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * ClassName：AIController
 *
 * @author: Devil
 * @Date: 2025/6/21
 * @Description:
 * @version: 1.0
 */
@RestController
public class AIController {
    @Autowired
    public ChatClient chatClient;

    //角色预设，非流式
    @GetMapping("/chatai")
    public String chatAi(@RequestParam String msg){
        return chatClient.prompt().user( msg).call().content();
    }

    //流式
    @GetMapping(value = "/chataiStream",produces="text/html;charset=UTF-8")
    public Flux<String> chatAiStream(@RequestParam String msg){
        return chatClient.prompt().user( msg).stream().content();
    }
}
