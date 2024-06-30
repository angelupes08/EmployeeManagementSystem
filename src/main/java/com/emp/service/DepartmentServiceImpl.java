package com.emp.service;

import com.emp.entity.Department;
import com.emp.payload.DepartmentDto;
import com.emp.repository.DepartmentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DepartmentDto> getDepartmentDetails() {

        List<Department> departments = departmentRepo.findAll();

        DepartmentDto departmentDto = new DepartmentDto();

        return departments.stream().map((element) -> modelMapper.map(element, DepartmentDto.class)).toList();

    }
}
