package com.tw.domain;

import com.tw.mapper.UserMapper;

import javax.inject.Inject;
import java.util.Map;

public class DefaultUserRepository implements UserRepository {
    @Inject
    UserMapper userMapper;

    @Override
    public User createUser(Map<String, Object> map) {
        userMapper.createUser(map);
        return userMapper.findUserById((Long) map.get("id"));
    }

    @Override
    public User findUserById(long userId) {
        return userMapper.findUserById(userId);
    }
}
