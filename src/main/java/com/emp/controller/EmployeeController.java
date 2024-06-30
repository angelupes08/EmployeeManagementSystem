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

    //This will allow the admin to register employees to the system
    @PostMapping("/register")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employee,
                                                   @RequestParam(value = "department") String department
            ,@RequestParam(value = "job") String job,@RequestParam(value = "managerId") Long managerId)

    {

        EmployeeDto e = employeeService.createEmployee(employee,department,job,managerId);

        return new ResponseEntity<>(e,HttpStatus.CREATED);
    }

    //This will allow the admin to update an employee’s Department and Job details in the system
    @PutMapping("/update/departmentandjob/{id}")
    public ResponseEntity<DepartmentJobDto> updateDepartmentAndJob(@RequestBody DepartmentJobDto departmentJobDto,
                                                                   @PathVariable Long id){

        return new ResponseEntity<DepartmentJobDto>(employeeService.updateDepartmentandJobDetails(departmentJobDto,id),HttpStatus.OK);
    }

    //This will allow the admin to update an employee’s Info in the system
    @PutMapping("/update/empinfo/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeInfo(@RequestBody EmployeeDto employeeDto,
                                                          @PathVariable Long id){

        return new ResponseEntity<EmployeeDto>(employeeService.updateEmployeeInfo(employeeDto,id),HttpStatus.OK);
    }

    //This will allow the admin to see his/her details
    @GetMapping("/getDetails")
    public ResponseEntity<EmployeeDto> getUserDetails(){

        return new ResponseEntity<EmployeeDto>(employeeService.getEmployeeDetails(), HttpStatus.OK);
    }

    //This will allow the admin to see other employee’s basic details like office email id, phoneNumber etc.
    @GetMapping("/getDetails/{id}")
    public ResponseEntity<EmployeeDto> getOtherUserDetails(@PathVariable Long id){

        return new ResponseEntity<EmployeeDto>(employeeService.getOtherEmployeeDetails(id),HttpStatus.OK);
    }

    //This will allow the admin to see his/her department and job details.
    @GetMapping("/getDetails/departmentAndJob")
    public ResponseEntity<DepartmentJobDto> getDepartmentAndJobName(){

        return new ResponseEntity<DepartmentJobDto>(employeeService.getDepartmentandJobDetails(),HttpStatus.OK);
    }
}
