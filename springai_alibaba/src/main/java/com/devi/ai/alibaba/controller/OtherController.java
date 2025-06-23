package com.devi.ai.alibaba.controller;

import com.alibaba.cloud.ai.dashscope.api.DashScopeImageApi;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageModel;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * ClassName：OtherController
 *
 * @author: Devil
 * @Date: 2025/6/23
 * @Description:
 * @version: 1.0
 */
@RestController
public class OtherController {
    @Autowired
    private DashScopeImageModel imageModel;

    @GetMapping("/image")
    public void getImage(@RequestParam String msg,
                         HttpServletResponse response){
        ImageResponse imageResponse = imageModel.call(
                new ImagePrompt(
                        msg,
                        DashScopeImageOptions.builder()
                                .withModel(DashScopeImageApi.DEFAULT_IMAGE_MODEL)
                                .withN(1)
                                .withHeight(1024)
                                .withWidth(1024)
                                .build()
                )
        );
        //获取生成的图形地址
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        //在浏览器输出
        try {
            URL url = URI.create(imageUrl).toURL();
            InputStream in = url.openStream();
            response.setHeader("Content-Type", MediaType.IMAGE_PNG_VALUE);
            response.getOutputStream().write(in.readAllBytes());
            response.getOutputStream().flush();
        }catch (Exception e) {
        }
    }
}
