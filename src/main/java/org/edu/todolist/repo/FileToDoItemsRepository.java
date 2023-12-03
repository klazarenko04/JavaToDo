package org.edu.todolist.repo;

import org.edu.todolist.entity.ToDoItems;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileToDoItemsRepository implements ToDoItemsRepository{
    private String filepath = "toDoItems.txt";

    public FileToDoItemsRepository(String filepath) {
        this.filepath = filepath;
    }

    public FileToDoItemsRepository() {
    }

    @Override
    public void save(ToDoItems toDoItem) {
             try (FileWriter fileWriter = new FileWriter(filepath, true)) {
            fileWriter.write(toDoItem.getId() + ","
                    + toDoItem.getUserName() + ","
                    + toDoItem.getDescription() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
                }
    }

    @Override
    public List<ToDoItems> findByUserName(String userName) {
         List<ToDoItems> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[1].equals(userName)) {
                    ToDoItems toDoItem = new ToDoItems();
                    toDoItem.setId(Integer.parseInt(parts[0]));
                    toDoItem.setUserName(parts[1]);
                    toDoItem.setDescription(parts[2]);
                    items.add(toDoItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
         }
        return items;
    }

    @Override
    public ToDoItems findById(int id) {
        // Реалізація пошуку у файлі за ідентифікатором
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && Integer.parseInt(parts[0]) == id) {
                    ToDoItems toDoItem = new ToDoItems();
                    toDoItem.setId(id);
                    toDoItem.setUserName(parts[1]);
                    toDoItem.setDescription(parts[2]);
                    return toDoItem;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Обробка помилок
        }
        return null;
    }

    @Override
    public void delete(ToDoItems toDoItem) {
           try {
            File inputFile = new File(filepath);
            File tempFile = new File("tempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = toDoItem.getId() + "," + toDoItem.getUserName() + "," + toDoItem.getDescription();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));

            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
                 }
    }
}
