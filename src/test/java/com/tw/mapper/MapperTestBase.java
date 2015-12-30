package com.tw.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;

public class MapperTestBase {
    protected SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
        sqlSession.close();
    }
}
