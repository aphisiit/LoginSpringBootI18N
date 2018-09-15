package com.guy.login.initail;

import com.guy.login.domain.Task;
import com.guy.login.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskLoader implements CommandLineRunner {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        Task task1 = new Task();
        task1.setDetail("detail1");
        task1.setTitle("title1");
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setDetail("detail2");
        task2.setTitle("title2");
        taskRepository.save(task2);

        Task task3= new Task();
        task3.setDetail("detail3");
        task3.setTitle("title3");
        taskRepository.save(task3);
    }
}
