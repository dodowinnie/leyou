package com.brandon.leyou.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableZuulProxy
public class LyApiGateWay {

    public static void main(String[] args) {
        SpringApplication.run(LyApiGateWay.class, args);
    }
}
