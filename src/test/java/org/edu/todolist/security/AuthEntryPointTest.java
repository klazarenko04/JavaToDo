package org.edu.todolist.security;

import org.edu.todolist.authExceptionHandler.AuthEntryPoint;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthEntryPointTest {

    @Test
    void commenceShouldRedirectToLogin() throws Exception {
        AuthEntryPoint authEntryPoint = new AuthEntryPoint();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException authException = new AuthenticationException("Authentication failed") {};

        authEntryPoint.commence(request, response, authException);

        assertEquals("/login", response.getRedirectedUrl());
        assertEquals(302, response.getStatus());
    }
}
