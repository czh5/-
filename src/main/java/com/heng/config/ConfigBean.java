package com.heng.config;

import com.heng.core.CoreProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author heng
 * @Date 2023/3/17 19:22
 * @version 1.0
 *
 * 全局Bean配置类
 */
@Configuration
public class ConfigBean {

    /** 指定问题question及字典的txt模板所在的根目录*/
    @Value("${rootDirPath}")
    private String rootDirPath;

    @Bean
    public CoreProcessor modelProcess() throws Exception {
        return new CoreProcessor(rootDirPath);
    }

}

