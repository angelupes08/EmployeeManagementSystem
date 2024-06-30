package com.emp.controller;

import com.emp.entity.Employee;
import com.emp.payload.DepartmentJobDto;
import com.emp.payload.EmployeeDto;
import com.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @PostMapping("/register")//this will create a user
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employee,
                                                   @RequestParam(value = "department") String department
            ,@RequestParam(value = "job") String job,@RequestParam(value = "managerId") Long managerId)

    {

        EmployeeDto e = employeeService.createEmployee(employee,department,job,managerId);

        return new ResponseEntity<>(e,HttpStatus.CREATED);
    }

    @PutMapping("/update/departmentandjob/{id}")
    public ResponseEntity<DepartmentJobDto> updateDepartmentAndJob(@RequestBody DepartmentJobDto departmentJobDto,
                                                                   @PathVariable Long id){

        return new ResponseEntity<DepartmentJobDto>(employeeService.updateDepartmentandJobDetails(departmentJobDto,id),HttpStatus.OK);
    }

    @PutMapping("De")
    public ResponseEntity<EmployeeDto> updateEmployeeInfo(@RequestBody EmployeeDto employeeDto,
                                                          @PathVariable Long id){

        return new ResponseEntity<EmployeeDto>(employeeService.updateEmployeeInfo(employeeDto,id),HttpStatus.OK);
    }

    @GetMapping("/getDetails")
    public ResponseEntity<EmployeeDto> getUserDetails(){

        return new ResponseEntity<EmployeeDto>(employeeService.getEmployeeDetails(), HttpStatus.OK);
    }

    @GetMapping("/getDetails/{id}")
    public ResponseEntity<EmployeeDto> getOtherUserDetails(@PathVariable Long id){

        return new ResponseEntity<EmployeeDto>(employeeService.getOtherEmployeeDetails(id),HttpStatus.OK);
    }

    @GetMapping("/getDetails/departmentAndJob")
    public ResponseEntity<DepartmentJobDto> getDepartmentAndJobName(){

        return new ResponseEntity<DepartmentJobDto>(employeeService.getDepartmentandJobDetails(),HttpStatus.OK);
    }
}
