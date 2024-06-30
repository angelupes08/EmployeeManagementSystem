package com.emp.repository;

import com.emp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department,Long> {

    public Optional<Department> findByDepartmentName(String name);


}
