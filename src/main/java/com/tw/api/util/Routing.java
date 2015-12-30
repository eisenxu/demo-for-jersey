package com.tw.api.util;

import java.net.URI;

import static javax.ws.rs.core.UriBuilder.fromUri;

public class Routing {
    public static URI user(long userId) {
        return fromUri("/users/{userId}").build(userId);
    }
}

