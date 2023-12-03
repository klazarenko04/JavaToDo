package org.edu.todolist.model;

import org.edu.todolist.entity.ToDoItems;
import org.edu.todolist.repo.FileToDoItemsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileToDoItemsRepositoryTest {

    private static final String TEST_FILE_PATH = "test_toDoItems.txt";

    private FileToDoItemsRepository fileToDoItemsRepository;

    @BeforeEach
    void setUp() {
        fileToDoItemsRepository = new FileToDoItemsRepository(TEST_FILE_PATH);
    }

    @Test
    void testSave() {

        ToDoItems toDoItem = new ToDoItems();
        toDoItem.setId(1);
        toDoItem.setUserName("testUser");
        toDoItem.setDescription("Test task");

        fileToDoItemsRepository.save(toDoItem);

        try (BufferedReader br = new BufferedReader(new FileReader(TEST_FILE_PATH))) {
            String line = br.readLine();
            assertNotNull(line);
            String[] parts = line.split(",");
            assertEquals(3, parts.length);
            assertEquals("1", parts[0]);
            assertEquals("testUser", parts[1]);
            assertEquals("Test task", parts[2]);
        } catch (IOException e) {
            fail("Exception not expected", e);
        }
    }

    @Test
    void testFindByUserName() {
        createTestFile();

        List<ToDoItems> items = fileToDoItemsRepository.findByUserName("testUser");

        assertEquals(1, items.size());
        ToDoItems foundItem = items.get(0);
        assertEquals(1, foundItem.getId());
        assertEquals("testUser", foundItem.getUserName());
        assertEquals("Test task", foundItem.getDescription());
    }

    @Test
    void testFindById() {

        createTestFile();

        ToDoItems foundItem = fileToDoItemsRepository.findById(1);


        assertNotNull(foundItem);
        assertEquals(1, foundItem.getId());
        assertEquals("testUser", foundItem.getUserName());
        assertEquals("Test task", foundItem.getDescription());
    }

    @Test
    void testDelete() {
        // Arrange
        createTestFile();
        ToDoItems toDoItem = new ToDoItems();
        toDoItem.setId(1);
        toDoItem.setUserName("testUser");
        toDoItem.setDescription("Test task");

        // Act
        fileToDoItemsRepository.delete(toDoItem);

        try (BufferedReader br = new BufferedReader(new FileReader(TEST_FILE_PATH))) {
            String line = br.readLine();
            assertNull(line);
        } catch (IOException e) {
            fail("Exception not expected", e);
        }
    }

    private void createTestFile() {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            fileWriter.write("1,testUser,Test task\n");
        } catch (IOException e) {
            fail("Exception not expected", e);
        }
    }
}
