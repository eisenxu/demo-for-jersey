package com.tw.domain;

public class TestHelper {

    public static User normalUser(long id) {
        User user = new User();
        user.id = id;
        user.name = "name";
        user.role = Role.NORMAL_USER;
        return user;
    }
}
