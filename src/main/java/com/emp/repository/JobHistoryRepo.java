package com.emp.repository;

import com.emp.entity.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepo extends JpaRepository<JobHistory,Long> {
}
