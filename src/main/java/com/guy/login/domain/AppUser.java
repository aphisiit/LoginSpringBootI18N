package com.guy.login.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @NotEmpty(message = "*Please provide an email")
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty(message = "*Please provide your name")
    private String name;

    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "active")
    private int actives;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AppRole> roles;

}
