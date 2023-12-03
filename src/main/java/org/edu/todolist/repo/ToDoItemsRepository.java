package org.edu.todolist.repo;

import org.edu.todolist.entity.ToDoItems;


import java.util.List;

public interface ToDoItemsRepository {

	void save(ToDoItems toDoItem);
	List<ToDoItems> findByUserName(String userName);
	ToDoItems findById(int id);
	void delete(ToDoItems toDoItem);
}
