package ru.example.docker.service;

import ru.example.docker.dto.UserDTO;

import java.util.List;

public interface UserService {
    void addUser(UserDTO userDTO);

    UserDTO getUser(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(long id);
}
