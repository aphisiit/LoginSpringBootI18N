package com.guy.login.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Task {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String detail;
}
