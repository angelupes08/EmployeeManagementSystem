package com.emp.repository;

import com.emp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {

    public Optional<Role> findByName(String name);
}
