package org.edu.todolist.model;

import org.edu.todolist.dto.LoginRequestData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginRequestDataTest {

    @Test
    void testGetEmail() {
        LoginRequestData loginRequestData = new LoginRequestData();
        String email = "test@example.com";
        loginRequestData.setEmail(email);

        String retrievedEmail = loginRequestData.getEmail();

        assertEquals(email, retrievedEmail);
    }

    @Test
    void testSetEmail() {

        LoginRequestData loginRequestData = new LoginRequestData();
        String email = "test@example.com";

        loginRequestData.setEmail(email);

        assertEquals(email, loginRequestData.getEmail());
    }

    @Test
    void testGetPassword() {
        LoginRequestData loginRequestData = new LoginRequestData();
        String password = "securePassword";
        loginRequestData.setPassword(password);

        String retrievedPassword = loginRequestData.getPassword();
        assertEquals(password, retrievedPassword);
    }

    @Test
    void testSetPassword() {
        LoginRequestData loginRequestData = new LoginRequestData();
        String password = "securePassword";

        loginRequestData.setPassword(password);

        assertEquals(password, loginRequestData.getPassword());
    }
}
