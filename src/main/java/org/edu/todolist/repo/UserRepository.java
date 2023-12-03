package org.edu.todolist.repo;

import org.edu.todolist.entity.UserCredentials;



public interface UserRepository {
    void save(UserCredentials userCredentials);
    UserCredentials findByEmail(String email);
}


