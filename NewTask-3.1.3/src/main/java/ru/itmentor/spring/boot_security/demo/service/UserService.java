package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService{  //extends UserDetailsService
    List<User> getUsers();

    Set<Role> getRoles();

    User getUserById(Long id);

    User addUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
//    public Optional<User> findByUsername(String username);

    public User findByUsername(String username);

//    User findUserByName(String name);



}