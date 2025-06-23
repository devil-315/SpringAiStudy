package com.devil.ai.ollama.controller;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName：OllamaController
 *
 * @author: Devil
 * @Date: 2025/6/23
 * @Description:
 * @version: 1.0
 */
@RestController
public class OllamaController {

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @GetMapping("/ollama")
    public String ollama(@RequestParam String msg){
        String result = ollamaChatModel.call(msg);
        return result;
    }
}
