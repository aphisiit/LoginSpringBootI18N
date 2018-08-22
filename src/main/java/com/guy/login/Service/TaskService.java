package com.guy.login.Service;

import com.guy.login.domain.Task;

import java.util.List;

public interface TaskService {
    void saveTask(Task task);
    List<Task> findAll();
}
