package com.guy.login.Service;

import com.guy.login.domain.TaskUser;

import java.util.List;

public interface TaskService {
    void saveTask(TaskUser task);
    TaskUser findOneById(Long id);
    List<TaskUser> findAll();
    List<TaskUser> findCustomByTitle(String title);
}
