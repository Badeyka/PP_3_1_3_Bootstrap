package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.annotation.PostConstruct;

@Service
public class TestUsersForDatabase {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public TestUsersForDatabase(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @PostConstruct
    public void init() {

        User admin = new User("Admin", "Adminov", 33, "admin@mail.ru", "admin");
        User user = new User("User", "Userov", 22, "user@mail.ru", "user");

        Role roleAdmin = new Role(1L, "ROLE_ADMIN");
        Role roleUser = new Role(2L, "ROLE_USER");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        userService.save(admin, "ADMIN");
        userService.save(user, "USER");
    }
}
