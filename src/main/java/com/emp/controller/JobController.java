package com.emp.controller;

import com.emp.payload.DepartmentDto;
import com.emp.payload.JobDto;
import com.emp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/job")
    public ResponseEntity<List<JobDto>> getJobDetails(){

        return new ResponseEntity<List<JobDto>>(jobService.getJobDetails(), HttpStatus.OK);
    }


}
