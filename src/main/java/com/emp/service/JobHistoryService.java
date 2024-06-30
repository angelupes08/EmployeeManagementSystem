package com.emp.service;

import com.emp.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface JobHistoryService {

    public void createJobHistory(Employee e, String jobName, String deptName);
}
