package com.tw.domain;

import com.tw.api.util.Routing;

import java.util.HashMap;
import java.util.Map;

public class User implements Record {
    long id;
    String name;
    Role role = Role.NORMAL_USER;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("role", role);
        return map;
    }

    @Override
    public Map<String, Object> toRefJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("uri", Routing.user(id));
        map.put("name", name);
        return map;
    }
}
