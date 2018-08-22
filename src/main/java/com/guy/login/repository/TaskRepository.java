package com.guy.login.repository;

import com.guy.login.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends JpaSpecificationExecutor<Task>, JpaRepository<Task,Long> {

}
