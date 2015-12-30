package com.tw.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    private MyBatisUtil() {
    }

    static {
        String resource = "mybatis-config.xml";

        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            Properties properties = new Properties();
            properties.put("db.url", System.getenv().getOrDefault("DATABASE", "jdbc:mysql://localhost:3307/ke_tsu?user=mysql&password=mysql") + "&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
