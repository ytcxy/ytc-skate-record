package com.ytc.skate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ytc.skate.*.mapper")
public class YtcSkateRecordApplication {

    public static void main(String[] args) {
        SpringApplication.run(YtcSkateRecordApplication.class, args);
    }

}
