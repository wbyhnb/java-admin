package com.weione.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeGenerator {
    public static void main(String[] args) {
        generate();
    }
    private static void generate() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/weione?serverTimezone=GMT%2b8", "root", "111111")
                .globalConfig(builder -> {
                    builder.author("BYW") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\java\\weione-admin\\src\\main\\java"); // 指定输出目录
                    builder.dateType(DateType.TIME_PACK)   //时间策略
                            .commentDate("yyyy-MM-dd");   //注释日期
                    builder.disableOpenDir(); //禁止打开输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.weione") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .entity("entity")                 //Entity 包名
                            .service("service")             //	Service 包名
                            .serviceImpl("service.impl")    //Service Impl 包名
                            .mapper("mapper")               //Mapper 包名
                            .xml("mapper.xml")              //	Mapper XML 包名
                            .controller("controller")       //Controller 包名
                            .other("config")                //自定义文件包名	输出自定义文件时所用到的包名，如 Mapper、Entity、Mapper XML、Service、Service Impl、Controller等等。
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\java\\weione-admin\\src\\main\\resources\\mapper")); // 设置mapperXml生成路
                })
                .strategyConfig((scanner, builder) -> {
                    builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all"))) // 设置需要生成的表名
                            .addTablePrefix("sys_") // 设置过滤表前缀
                            .entityBuilder()
                            .enableLombok() // 开启lombok支持
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .build()
                            .controllerBuilder()
                            .enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器



                })
                .execute();
    }
    protected static List<String> getTables (String  tables ){
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
