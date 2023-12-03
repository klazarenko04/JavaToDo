package org.edu.todolist.entity;


public class ToDoItems {

	public int id;
	public String userName;
	public String Description;


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getId() {
		return id;
	}
	public void setId( int id ) {
		this.id = id;
	}


	
}
