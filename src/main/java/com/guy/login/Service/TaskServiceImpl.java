package com.guy.login.Service;

import com.guy.login.domain.TaskUser;
import com.guy.login.repository.TaskUserRepository;
import com.guy.login.repository.TaskUserRepository_Custom;
import com.guy.login.specification.SearchCriteria;
import com.guy.login.specification.TaskSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskUserRepository taskRepository;

    @Autowired
    private TaskUserRepository_Custom taskRepository_custom;

    @Override
    public void saveTask(TaskUser task) {
        taskRepository.save(task);
    }

    @Override
    public List<TaskUser> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public TaskUser findOneById(Long id) {
        TaskSpecification taskSpecification = new TaskSpecification(new SearchCriteria("id",":",id.toString()));
        return taskRepository.findOne(taskSpecification).get();
    }

    @Override
    public List<TaskUser> findCustomByTitle(String title) {
        return taskRepository_custom.findTaskByTitle(title);
    }
}
