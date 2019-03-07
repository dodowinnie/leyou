package com.brandon.leyou.item.service;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringCloudApplication
@MapperScan("com.brandon.leyou.item.service.mapper")
public class LyService {

    public static void main(String[] args) {
        SpringApplication.run(LyService.class, args);
    }
}
