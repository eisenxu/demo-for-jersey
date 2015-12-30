package com.tw.mapper;

import com.tw.domain.Role;
import com.tw.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserMapperTest extends MapperTestBase {
    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void should_create_a_new_user() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Zhang Xiang");
        map.put("role", Role.NORMAL_USER.toString());
        int affectedRow = userMapper.createUser(map);
        assertThat(affectedRow, is(1));
    }

    @Test
    public void should_find_user_by_id() throws Exception {
        String name = "Zhang Xiang";
        long userId = 1234l;

        Map<String, Object> map = new HashMap<>();
        map.put("id", userId);
        map.put("name", name);
        map.put("role", Role.NORMAL_USER);
        userMapper.createUser(map);

        User user = userMapper.findUserById((Long) map.get("id"));
        assertThat(user.getName(), is(name));
        assertThat(user.getId(), is(userId));
        assertThat(user.getRole(), is(Role.NORMAL_USER));
    }

    @Test
    public void should_find_user_by_name() throws Exception {
        String name = "Zhang Xiang";
        long userId = 1234l;

        Map<String, Object> map = new HashMap<>();
        map.put("id", userId);
        map.put("name", name);
        map.put("role", Role.NORMAL_USER);
        userMapper.createUser(map);

        User user = userMapper.findUserByName(name);
        assertThat(user.getName(), is(name));
        assertThat(user.getId(), is(userId));
        assertThat(user.getRole(), is(Role.NORMAL_USER));
    }
}
