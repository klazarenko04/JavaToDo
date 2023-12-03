package org.edu.todolist.model;

import org.edu.todolist.entity.UserCredentials;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserCredentialsTest {

    @Test
    void testGetEmail() {

        UserCredentials userCredentials = new UserCredentials();
        String email = "test@example.com";
        userCredentials.setEmail(email);

        String retrievedEmail = userCredentials.getEmail();

        assertEquals(email, retrievedEmail);
    }

    @Test
    void testSetEmail() {
        UserCredentials userCredentials = new UserCredentials();
        String email = "test@example.com";

        userCredentials.setEmail(email);

        assertEquals(email, userCredentials.getEmail());
    }

    @Test
    void testGetFirstName() {
        UserCredentials userCredentials = new UserCredentials();
        String firstName = "John";
        userCredentials.setFirstName(firstName);

        String retrievedFirstName = userCredentials.getFirstName();

        assertEquals(firstName, retrievedFirstName);
    }

    @Test
    void testSetFirstName() {
        UserCredentials userCredentials = new UserCredentials();
        String firstName = "John";

        userCredentials.setFirstName(firstName);

        assertEquals(firstName, userCredentials.getFirstName());
    }

    @Test
    void testGetLastName() {
        UserCredentials userCredentials = new UserCredentials();
        String lastName = "Doe";
        userCredentials.setLastName(lastName);

        String retrievedLastName = userCredentials.getLastName();

        assertEquals(lastName, retrievedLastName);
    }

    @Test
    void testSetLastName() {
        UserCredentials userCredentials = new UserCredentials();
        String lastName = "Doe";

        userCredentials.setLastName(lastName);

        assertEquals(lastName, userCredentials.getLastName());
    }

    @Test
    void testGetPassword() {
        UserCredentials userCredentials = new UserCredentials();
        String password = "securePassword";
        userCredentials.setPassword(password);

        String retrievedPassword = userCredentials.getPassword();

        assertEquals(password, retrievedPassword);
    }

    @Test
    void testSetPassword() {
        UserCredentials userCredentials = new UserCredentials();
        String password = "securePassword";

        userCredentials.setPassword(password);

        assertEquals(password, userCredentials.getPassword());
    }
}
