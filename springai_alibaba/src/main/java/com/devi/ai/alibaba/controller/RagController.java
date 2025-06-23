package com.devi.ai.alibaba.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName：RagController
 *
 * @author: Devil
 * @Date: 2025/6/23
 * @Description:
 * @version: 1.0
 */
@RestController
public class RagController {
    @Autowired
    private ChatClient chatClient;

    @Autowired
    private VectorStore vectorStore;

    @GetMapping("/rag")
    public String rag(@RequestParam String input){
        String content = chatClient.prompt()
                .user(input)
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .call()
                .content();
        return content;
    }
}
