package com.guy.login.Service;

import com.guy.login.domain.Task;
import com.guy.login.repository.TaskRepository;
import com.guy.login.repository.TaskRepository_Custom;
import com.guy.login.specification.SearchCriteria;
import com.guy.login.specification.TaskSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskRepository_Custom taskRepository_custom;

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findOneById(Long id) {
        TaskSpecification taskSpecification = new TaskSpecification(new SearchCriteria("id",":",id.toString()));
        return taskRepository.findOne(taskSpecification).get();
    }

    @Override
    public List<Task> findCustomByTitle(String title) {
        return taskRepository_custom.findTaskByTitle(title);
    }
}
