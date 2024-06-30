package com.emp.service;

import com.emp.payload.JobDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {

    public List<JobDto> getJobDetails();


}
