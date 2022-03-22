package ru.example.docker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.docker.dto.UserDTO;
import ru.example.docker.service.UserService;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_PATH)
public class UserController {
    protected static final String BASE_PATH = "/user";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void addPerson(@RequestBody UserDTO user){
        userService.addUser(user);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable long id){
        return userService.getUser(id);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/update")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }

}
