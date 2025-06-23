package com.devil.ai.chat.controller;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName：ChatModelController
 *
 * @author: Devil
 * @Date: 2025/6/21
 * @Description:
 * @version: 1.0
 */
@RestController
public class ChatModelController {
    @Autowired
    private ChatModel chatModel;

    @GetMapping("/chatmodel01")
    public String chatModel01(@RequestParam String msg) {
        return chatModel.call(msg);
    }

    @GetMapping("/chatmodel02")
    public String chatModel02(@RequestParam String msg) {
        ChatResponse chatResponse = chatModel.call(new Prompt(
                msg,
                ChatOptions.builder()
                        .model("deepseek-chat")
                        .temperature(0.8)
                        .build()
        ));
        String content = chatResponse.getResult().getOutput().getContent();
        return content;
    }

    @GetMapping("/prompt")
    public String prompt(@RequestParam("name")
                             String name,
                         @RequestParam("voice")
                             String voice){
        //用户输入信息
        String userText= """
            给我推荐北京的至少三种美食
            """;
        UserMessage userMessage = new UserMessage(userText);

        //系统提示信息
        String systemText= """
            你是一个美食咨询助手，可以帮助人们查询美食信息。
            你的名字是{name},
            你应该用你的名字和{voice}的饮食习惯回复用户的请求。
            """;
        //使用PromptTemplate设置信息
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);

        //替换占位符
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", name, "voice", voice));

        //调用chatModel方法
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        List<Generation> results = chatModel.call(prompt).getResults();
        return results.stream().map(x -> x.getOutput().getContent()).collect(Collectors.joining(""));
    }
}
