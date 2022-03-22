package ru.example.docker.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.example.docker.dao.UserDAO;
import ru.example.docker.dto.UserDTO;
import ru.example.docker.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    private final ModelMapper modelMapper;

    @Autowired
    private UserDAO userDAO;

    public UserServiceImpl() {
        this.modelMapper = new ModelMapper();
    }

    private UserDTO toDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User toModel(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userDAO.addUser(modelMapper.map(userDTO, User.class));
    }

    @Override
    public UserDTO getUser(Long id) {
        return modelMapper.map(userDAO.findUser(id), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.findAllUsers().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        userDAO.updateUser(toModel(userDTO));
        return toDto(userDAO.findUser(userDTO.getId()));
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }
}
