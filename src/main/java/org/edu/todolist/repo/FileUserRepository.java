package org.edu.todolist.repo;

import org.edu.todolist.entity.UserCredentials;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileUserRepository implements UserRepository {

    private String filepath = "users.txt";

    public FileUserRepository(String filePath) {
        this.filepath = filePath;
    }

    public FileUserRepository() {
    }

    @Override
    public void save(UserCredentials userCredentials) {
        try (FileWriter fileWriter = new FileWriter(filepath, true)) {
            fileWriter.write(userCredentials.getEmail() + ","
                    + userCredentials.getFirstName() + ","
                    + userCredentials.getLastName() + ","
                    + userCredentials.getPassword() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserCredentials findByEmail(String email) {
            try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].equals(email)) {
                    UserCredentials userCredentials = new UserCredentials();
                    userCredentials.setEmail(parts[0]);
                    userCredentials.setFirstName(parts[1]);
                    userCredentials.setLastName(parts[2]);
                    userCredentials.setPassword(parts[3]);
                    return userCredentials;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
                 }

        return null;
    }


}

