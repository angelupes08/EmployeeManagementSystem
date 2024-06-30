package com.emp.service;


import com.emp.entity.Employee;
import com.emp.entity.JobHistory;
import com.emp.exceptions.ResourceNotFoundException;
import com.emp.repository.DepartmentRepo;
import com.emp.repository.JobHistoryRepo;
import com.emp.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JobHistoryServiceImpl implements JobHistoryService {

    @Autowired
    JobHistoryRepo jobHistoryRepo;

    @Autowired
    JobRepo jobRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    @Override
    public void createJobHistory(Employee e, String jobName, String deptName) {

        JobHistory jobHistory = new JobHistory();

        jobHistory.setEmployee(e);
        jobHistory.setJobs(jobRepo.findByJobTitle(jobName).orElseThrow(()->new ResourceNotFoundException("No jobs found")));
        jobHistory.setDepartment(departmentRepo.findByDepartmentName(deptName).orElseThrow(()->new ResourceNotFoundException("No departments found")));
        jobHistory.setStartDate(new Date());

        jobHistoryRepo.save(jobHistory);
    }
}
