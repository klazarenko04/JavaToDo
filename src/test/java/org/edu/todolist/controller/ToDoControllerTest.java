package org.edu.todolist.controller;

import org.edu.todolist.entity.ToDoItems;
import org.edu.todolist.repo.FileToDoItemsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ToDoControllerTest {

    @Mock
    private FileToDoItemsRepository toDoItemRepo;

    @InjectMocks
    private ToDoController toDoController;


    @Test
    void testDeleteItemPage() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(toDoController).build();

        ToDoItems expectedItem = new ToDoItems();
        expectedItem.setId(1);
        when(toDoItemRepo.findById(anyInt())).thenReturn(expectedItem);

        mockMvc.perform(post("/deleteitem")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        org.mockito.Mockito.verify(toDoItemRepo).delete(expectedItem);
    }
}
