package com.ytc.skate;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGen {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/ytc-skate?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";
        String moduleName = "sys";
        String mapperLocation = "/Users/ytccc/code/my/ytc-skate/ytc-skate-record/ytc-skate-record/src/main/resources/mapper/" + moduleName;
        String tables = "x_Trick,x_Trick_Id";


        FastAutoGenerator.create(url, username, password)
            .globalConfig(builder -> {
                builder.author("baomidou") // 设置作者
//                    .enableSwagger() // 开启 swagger 模式
//                    .fileOverride() // 覆盖已生成文件
                    .outputDir("/Users/ytccc/code/my/ytc-skate/ytc-skate-record/ytc-skate-record/src/main/java"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.ytc") // 设置父包名
                    .moduleName(moduleName) // 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
            })
            .strategyConfig(builder -> {
                builder.addInclude(tables) // 设置需要生成的表名
                    .addTablePrefix("x_"); // 设置过滤表前缀
            })
            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }
}
