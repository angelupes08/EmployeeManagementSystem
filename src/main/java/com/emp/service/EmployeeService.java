package com.emp.service;

import com.emp.entity.Employee;
import com.emp.payload.DepartmentJobDto;
import com.emp.payload.EmployeeDto;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    public Employee createEmployee(EmployeeDto employeeDto,String department,String job,Long managerId);

    public EmployeeDto getEmployeeDetails();

    public EmployeeDto getOtherEmployeeDetails(Long empId);

    public Employee getLoggedInUser();

    public DepartmentJobDto getDepartmentandJobDetails();

    public DepartmentJobDto updateDepartmentandJobDetails(DepartmentJobDto departmentJobDto,Long id);

    public EmployeeDto updateEmployeeInfo(EmployeeDto employeeDto,Long id);
}
