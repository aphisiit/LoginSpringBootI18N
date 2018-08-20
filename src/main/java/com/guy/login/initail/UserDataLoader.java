package com.guy.login.initail;

import com.guy.login.domain.Role;
import com.guy.login.domain.User;
import com.guy.login.repository.RoleRepository;
import com.guy.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder cryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role();
        roleAdmin.setId(1);
        roleAdmin.setRole("ADMIN");
        Role roleUser = new Role();
        roleUser.setId(2);
        roleUser.setRole("USER");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        User userUser = new User();
        userUser.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByRole("USER"))));
        userUser.setActive(1);
        userUser.setPassword(cryptPasswordEncoder.encode("p@ssw0rd"));
        userUser.setEmail("user");
        userUser.setId(1);
        userUser.setName("Aphisit");
        userUser.setLastName("Namracha");

        User userAdmin = new User();
        userAdmin.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByRole("ADMIN"))));
        userAdmin.setActive(1);
        userAdmin.setPassword(cryptPasswordEncoder.encode("p@ssw0rd"));
        userAdmin.setEmail("admin");
        userAdmin.setId(1);
        userAdmin.setName("Sangkom");
        userAdmin.setLastName("Namracha");

        userRepository.save(userUser);
        userRepository.save(userAdmin);
    }
}
