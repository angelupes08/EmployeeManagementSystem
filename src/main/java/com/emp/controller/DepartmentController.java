package com.emp.controller;

import com.emp.payload.DepartmentDto;
import com.emp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {


    @Autowired
    DepartmentService departmentService;

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentDto>> getDepartmentDetails(){

        return new ResponseEntity<List<DepartmentDto>>(departmentService.getDepartmentDetails(), HttpStatus.OK);
    }
}
