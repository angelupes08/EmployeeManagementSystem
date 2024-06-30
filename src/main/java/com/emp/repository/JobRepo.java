package com.emp.repository;

import com.emp.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepo extends JpaRepository<Jobs,Long> {

    public Optional<Jobs> findByJobTitle(String jobTitle);
}
