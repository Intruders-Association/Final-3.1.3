package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Set<Role> getRoles() {
        return roleRepository.findAllRoles();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    @Transactional
    public User addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User user) {
        User updatedUser = userRepository.getById(id);
        if (updatedUser != null) {
            updatedUser.setName(user.getUsername());
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            updatedUser.setPassword(encodedPassword);
            updatedUser.setRoleSet(user.getRoleSet());
            userRepository.save(updatedUser);
        }
        return updatedUser;

    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }




}