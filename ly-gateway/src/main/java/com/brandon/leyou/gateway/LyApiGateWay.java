package com.brandon.leyou.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringCloudApplication
@EnableZuulProxy
public class LyApiGateWay {

    public static void main(String[] args) {
        SpringApplication.run(LyApiGateWay.class, args);
    }

    @Bean
    MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("D:\\opt\\tmp");
        return factory.createMultipartConfig();
    }
}
