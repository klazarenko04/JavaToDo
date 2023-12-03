package org.edu.todolist.model;

import org.edu.todolist.entity.ToDoItems;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoItemsTest {

    @Test
    void testGetId() {

        ToDoItems toDoItems = new ToDoItems();
        int id = 1;
        toDoItems.setId(id);


        int retrievedId = toDoItems.getId();


        assertEquals(id, retrievedId);
    }

    @Test
    void testSetId() {

        ToDoItems toDoItems = new ToDoItems();
        int id = 1;


        toDoItems.setId(id);

        assertEquals(id, toDoItems.getId());
    }

    @Test
    void testGetUserName() {

        ToDoItems toDoItems = new ToDoItems();
        String userName = "testUser";
        toDoItems.setUserName(userName);


        String retrievedUserName = toDoItems.getUserName();

        assertEquals(userName, retrievedUserName);
    }

    @Test
    void testSetUserName() {
        ToDoItems toDoItems = new ToDoItems();
        String userName = "testUser";

        toDoItems.setUserName(userName);

        assertEquals(userName, toDoItems.getUserName());
    }

    @Test
    void testGetDescription() {
        ToDoItems toDoItems = new ToDoItems();
        String description = "Test task";
        toDoItems.setDescription(description);

        String retrievedDescription = toDoItems.getDescription();

        assertEquals(description, retrievedDescription);
    }

    @Test
    void testSetDescription() {
        ToDoItems toDoItems = new ToDoItems();
        String description = "Test task";

        toDoItems.setDescription(description);

        assertEquals(description, toDoItems.getDescription());
    }
}
