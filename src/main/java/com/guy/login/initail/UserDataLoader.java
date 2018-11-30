package com.guy.login.initail;

import com.guy.login.domain.AppRole;
import com.guy.login.domain.AppUser;
import com.guy.login.repository.AppRoleRepository;
import com.guy.login.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder cryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        AppRole roleAdmin = new AppRole();
        roleAdmin.setId(1);
        roleAdmin.setRole("ADMIN");
        AppRole roleUser = new AppRole();
        roleUser.setId(2);
        roleUser.setRole("USER");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        AppUser userUser = new AppUser();
        userUser.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByRole("USER"))));
        userUser.setActives(1);
        userUser.setPassword(cryptPasswordEncoder.encode("p@ssw0rd"));
        userUser.setEmail("user");
        userUser.setId(1);
        userUser.setName("Aphisit");
        userUser.setLastName("Namracha");

        AppUser userAdmin = new AppUser();
        userAdmin.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByRole("ADMIN"))));
        userAdmin.setActives(1);
        userAdmin.setPassword(cryptPasswordEncoder.encode("p@ssw0rd"));
        userAdmin.setEmail("admin");
        userAdmin.setId(1);
        userAdmin.setName("Sangkom");
        userAdmin.setLastName("Namracha");

        userRepository.save(userUser);
        userRepository.save(userAdmin);
    }
}
