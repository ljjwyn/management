package com.instructional.system.management.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ljjwyn
 */
@Configuration
@MapperScan("com.baomidou.cloud.service.*.mapper*")
public class MybatisPlusConfig {

    /**动态表名配置，注意使用ThreadLocal。这里配置的是scoresRecord表，用于区别多次考试成绩的检索*/
    public static ThreadLocal<String> myTableNameScoresRecord = new ThreadLocal<>();

    /**动态表名配置，注意使用ThreadLocal。这里配置的是scores表*/
    public static ThreadLocal<String> myTableNameScores = new ThreadLocal<>();

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 配置动态表名插件
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(new HashMap<String, TableNameHandler>(2) {{
            put("scores_record", ( sql, tableName) -> myTableNameScoresRecord.get());
            put("scores", ( sql, tableName) -> myTableNameScores.get());
        }});
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
