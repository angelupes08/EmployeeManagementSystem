package com.emp.service;

import com.emp.payload.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    public List<DepartmentDto> getDepartmentDetails();
}
