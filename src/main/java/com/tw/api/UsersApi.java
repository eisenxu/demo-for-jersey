package com.tw.api;

import com.tw.api.exception.NotFoundException;
import com.tw.api.util.BodyParser;
import com.tw.api.util.Routing;
import com.tw.domain.User;
import com.tw.domain.UserRepository;
import com.tw.session.core.Session;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/users")
public class UsersApi {
    private boolean badRequest(Map<String, Object> formParams) {
        if (formParams.get("name") == null || formParams.get("role") == null) {
            return true;
        } else {
            return false;
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewUser(@Context Session session,
                                  @Context UserRepository userRepository,
                                  Form form) {
        Map<String, Object> formParams = BodyParser.parse(form.asMap());
        if (badRequest(formParams)) {
            return Response.status(400).build();
        }

        final User user = userRepository.createUser(formParams);
        return Response.created(Routing.user(user.getId())).build();
    }

    @Path("/{userId}")
    public UserApi getUser(@PathParam("userId") long userId,
                           @Context UserRepository userRepository) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new NotFoundException();
        }
        return new UserApi(user);
    }
}
