package com.emp.service;

import com.emp.entity.Jobs;
import com.emp.payload.DepartmentDto;
import com.emp.payload.JobDto;
import com.emp.repository.JobRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    JobRepo jobRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<JobDto> getJobDetails() {

        List<Jobs> jobs = jobRepo.findAll();

        return jobs.stream().map((element) -> modelMapper.map(element, JobDto.class)).toList();
    }
}
