package com.tw.domain;

import java.util.Map;

public interface UserRepository {
    User createUser(Map<String, Object> map);

    User findUserById(long userId);
}
