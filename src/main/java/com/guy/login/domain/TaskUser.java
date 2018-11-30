package com.guy.login.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TaskUser {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String title;
    private String detail;
}
