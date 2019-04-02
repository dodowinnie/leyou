package com.brandon.leyou.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringCloudApplication
public class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }

    @Bean
    MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("D:\\opt\\tmp");
        return factory.createMultipartConfig();
    }
}
