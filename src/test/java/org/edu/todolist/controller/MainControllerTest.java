package org.edu.todolist.controller;

import org.edu.todolist.dto.LoginRequestData;
import org.edu.todolist.repo.FileUserRepository;
import org.edu.todolist.repo.ToDoItemsRepository;
import org.edu.todolist.service.MyUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;

import static org.mockito.Mockito.*;

class MainControllerTest {

    @InjectMocks
    private MainController mainController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private MyUserDetailsService userDetailsService;

    @Mock
    private FileUserRepository userRepository;

    @Mock
    private ToDoItemsRepository todoitemrepo;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoginPage() {
        String result = mainController.loginPage();
    }

    @Test
    void testLoginUser() {

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));


        SecurityContextHolder.setContext(mock(SecurityContext.class));

        LoginRequestData loginRequestData = new LoginRequestData();
        loginRequestData.setEmail("test@example.com");
        loginRequestData.setPassword("password");
        String result = mainController.loginUser(loginRequestData);

        SecurityContextHolder.clearContext();
    }

    @Test
    void testShowRegister() {
        String result = mainController.showRegister();

    }

}
