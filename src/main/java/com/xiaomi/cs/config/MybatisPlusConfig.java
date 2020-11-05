package com.xiaomi.cs.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author l
 * @create 2020-11-05-11:09
 */
@Configuration
@MapperScan("com.xiaomi.cs.mapper")
public class MybatisPlusConfig {
}
