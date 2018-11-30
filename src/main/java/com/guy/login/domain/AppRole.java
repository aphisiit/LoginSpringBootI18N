package com.guy.login.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String role;
}
