package com.guy.login.repository;

import com.guy.login.domain.TaskUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface TaskUserRepository extends JpaSpecificationExecutor<TaskUser>, JpaRepository<TaskUser,Long>, CrudRepository<TaskUser,Long> {

}
