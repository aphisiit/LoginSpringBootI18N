package com.guy.login.initail;

import com.guy.login.domain.TaskUser;
import com.guy.login.repository.TaskUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskUserLoader implements CommandLineRunner {

    @Autowired
    TaskUserRepository taskUserRepository;

    @Override
    public void run(String... args) throws Exception {
        TaskUser taskUser1 = new TaskUser();
        taskUser1.setDetail("detail1");
        taskUser1.setTitle("title1");
        taskUserRepository.save(taskUser1);

        TaskUser taskUser2 = new TaskUser();
        taskUser2.setDetail("detail2");
        taskUser2.setTitle("title2");
        taskUserRepository.save(taskUser2);

        TaskUser taskUser3= new TaskUser();
        taskUser3.setDetail("detail3");
        taskUser3.setTitle("title3");
        taskUserRepository.save(taskUser3);
    }
}
