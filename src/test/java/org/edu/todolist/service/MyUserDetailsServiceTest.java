package org.edu.todolist.service;

import org.edu.todolist.entity.UserCredentials;
import org.edu.todolist.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        String email = "test@example.com";
        String password = "securePassword";

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail(email);
        userCredentials.setPassword(password);

        when(mockUserRepository.findByEmail(email)).thenReturn(userCredentials);

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());

        verify(mockUserRepository, times(1)).findByEmail(email);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        String email = "nonexistent@example.com";
        when(mockUserRepository.findByEmail(email)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> myUserDetailsService.loadUserByUsername(email));

        verify(mockUserRepository, times(1)).findByEmail(email);
    }

    @Test
    void testGetUserNameFromPrincipal() {
        // Arrange
        String email = "test@example.com";
        String firstName = "John";
        String lastName = "Doe";

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail(email);
        userCredentials.setFirstName(firstName);
        userCredentials.setLastName(lastName);

        when(mockUserRepository.findByEmail(email)).thenReturn(userCredentials);

        String userName = myUserDetailsService.getUserNameFromPrincipal(email);

        assertEquals(firstName + " " + lastName, userName);

        verify(mockUserRepository, times(1)).findByEmail(email);
    }

    @Test
    void testGetUserNameFromPrincipal_UserNotFound() {
        String email = "nonexistent@example.com";
        when(mockUserRepository.findByEmail(email)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> myUserDetailsService.getUserNameFromPrincipal(email));

        verify(mockUserRepository, times(1)).findByEmail(email);
    }
}
