package com.hydra.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zxp.esclientrhl.annotation.EnableESTools;

/**
 * @author : Hydra
 * @date: 2023/2/6 9:06
 * @version: 1.0
 */
@SpringBootApplication
@EnableESTools(basePackages = {"com.hydra.spider.*.repository"},
        entityPath = {"com.hydra.spider.*.model"})
public class SpiderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class,args);
    }

}
