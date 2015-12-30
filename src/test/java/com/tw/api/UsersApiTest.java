package com.tw.api;

import com.tw.domain.Role;
import com.tw.domain.TestHelper;
import com.tw.domain.User;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class UsersApiTest extends ApiTestBase {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_create_user() throws Exception {
        Map<String, Object> user = new HashMap<>();
        user.put("role", Role.USER_ADMIN.toString());
        when(session.get("user")).thenReturn(user);

        final User expectedUser = TestHelper.normalUser(1L);
        when(userRepository.createUser(any())).thenReturn(expectedUser);

        Form form = new Form();
        form.param("name", "Liu Yu");
        form.param("role", Role.NORMAL_USER.toString());

        Response response = target("/users").request().post(Entity.form(form));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), notNullValue());
    }

    @Test
    public void should_get_user_by_id() throws Exception {
        final User user = TestHelper.normalUser(1L);
        when(userRepository.findUserById(eq(1L))).thenReturn(user);

        final Response response = target("/users/1").request().get();
        assertThat(response.getStatus(), is(200));
        Map map = response.readEntity(Map.class);
        assertThat(map.get("name"), is(user.getName()));
    }
}
