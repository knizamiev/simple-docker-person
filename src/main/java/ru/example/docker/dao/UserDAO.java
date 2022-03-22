package ru.example.docker.dao;

import ru.example.docker.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User person);
    
    User findUser(Long id);

    List<User> findAllUsers();

    void updateUser(User user);

    void deleteUser(long id);
}
