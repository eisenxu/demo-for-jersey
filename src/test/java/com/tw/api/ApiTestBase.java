package com.tw.api;

import com.tw.api.exception.NotFoundException;
import com.tw.domain.UserRepository;
import com.tw.session.core.Session;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ApiTestBase extends JerseyTest {
    @Mock
    UserRepository userRepository;

    @Mock
    Session session;

    @Override
    protected Application configure() {
        return new ResourceConfig().packages("com.tw.api")
                .register(NotFoundException.class)
                .register(
                        new AbstractBinder() {
                            @Override
                            protected void configure() {
                                bind(userRepository).to(UserRepository.class);
                                bind(session).to(Session.class);
                            }
                        }
                );
    }
}
