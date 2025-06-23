package com.devil.ai.function.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName：AIConfig
 *
 * @author: Devil
 * @Date: 2025/6/21
 * @Description:
 * @version: 1.0
 */
@Configuration
public class AIConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder  builder){
        return builder.defaultSystem("你是王浩，北园的黑道之王")
                .build();
    }
}
