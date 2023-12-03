package org.edu.todolist.model;

import org.edu.todolist.entity.UserCredentials;
import org.edu.todolist.repo.FileUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileUserRepositoryTest {

    private static final String TEST_FILE_PATH = "test_users.txt";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        FileUserRepository fileUserRepository = new FileUserRepository(TEST_FILE_PATH);

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail("test@example.com");
        userCredentials.setFirstName("John");
        userCredentials.setLastName("Doe");
        userCredentials.setPassword("securePassword");

        fileUserRepository.save(userCredentials);

        try (BufferedReader br = new BufferedReader(new FileReader(TEST_FILE_PATH))) {
            String line = br.readLine();
            assertNotNull(line);
            String[] parts = line.split(",");
            assertEquals(4, parts.length);
            assertEquals("test@example.com", parts[0]);
            assertEquals("John", parts[1]);
            assertEquals("Doe", parts[2]);
            assertEquals("securePassword", parts[3]);
        } catch (IOException e) {
            fail("Exception not expected", e);
        }
    }

    @Test
    void testFindByEmail() {

        FileUserRepository fileUserRepository = new FileUserRepository(TEST_FILE_PATH);

        createTestFile();

        UserCredentials foundUser = fileUserRepository.findByEmail("test@example.com");

        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("John", foundUser.getFirstName());
        assertEquals("Doe", foundUser.getLastName());
        assertEquals("securePassword", foundUser.getPassword());
    }

    private void createTestFile() {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            fileWriter.write("test@example.com,John,Doe,securePassword\n");
            fileWriter.write("another@example.com,Jane,Doe,anotherPassword\n");
        } catch (IOException e) {
            fail("Exception not expected", e);
        }
    }
}
