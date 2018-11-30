package com.guy.login.repository;

import com.guy.login.domain.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {

    AppRole findByRole(String role);

}
