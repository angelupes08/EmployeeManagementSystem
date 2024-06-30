package com.emp.service;

import com.emp.entity.*;
import com.emp.exceptions.ItemAlreadyExistsException;
import com.emp.exceptions.ResourceNotFoundException;
import com.emp.payload.DepartmentJobDto;
import com.emp.payload.EmployeeDto;
import com.emp.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private JobHistoryRepo jobHistoryRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JobHistoryService jobHistoryService;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto,String department,String job,Long managerId) {

        Optional<Employee> emp = employeeRepo.findByEmail(employeeDto.getEmail());

        if(emp.isPresent()){
            throw new ItemAlreadyExistsException("An account already exists with given email"+employeeDto.getEmail());
        }

        Employee employee = this.modelMapper.map(employeeDto,Employee.class);

        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

        employee.setCreatedDate(new Date());
        employee.setUpdatedDate(new Date());

        employee.setManager(employeeRepo.findById(managerId).orElseThrow(()->new ResourceNotFoundException("No manager with the id found")));

        employee.setDepartment(departmentRepo.findByDepartmentName(department).orElseThrow(()->new ResourceNotFoundException("Department does not exist")));
        employee.setJobs(jobRepo.findByJobTitle(job).orElseThrow(()->new ResourceNotFoundException("Job does not exist")));

        Role userRole = roleRepo.findByName("ROLE_NORMAL")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        employee.getRole().add(userRole);

        Employee savedEmployee = employeeRepo.save(employee);

        jobHistoryService.createJobHistory(employee,job,department);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeDetails() {

        Employee employee = employeeRepo.findById(getLoggedInUser().getEmpId()).orElseThrow(()->new ResourceNotFoundException("No such user exits"));

        return this.modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public EmployeeDto getOtherEmployeeDetails(Long empId) {

        Employee employee = employeeRepo.findById(empId).orElseThrow(()->new ResourceNotFoundException("No such user exists"));

        return this.modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public Employee getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        return employeeRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("No such user exits"));
    }

    @Override
    public DepartmentJobDto getDepartmentandJobDetails() {

        Employee employee = employeeRepo.findById(getLoggedInUser().getEmpId()).orElseThrow(()->new ResourceNotFoundException("User does not exist"));

        DepartmentJobDto departmentJobDto = new DepartmentJobDto();

        departmentJobDto.setDepartmentName(employee.getDepartment().getDepartmentName());
        departmentJobDto.setJobName(employee.getJobs().getJobTitle());

        return departmentJobDto;
    }

    @Override
    public DepartmentJobDto updateDepartmentandJobDetails(DepartmentJobDto departmentJobDto,Long id) {

        Employee employee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User does not exist"));

        if (departmentJobDto.getDepartmentName() != null) {
            Department department = departmentRepo.findByDepartmentName(departmentJobDto.getDepartmentName())
                    .orElseThrow(() -> new ResourceNotFoundException("Department does not exist"));

            employee.setDepartment(department);
        }

        if (departmentJobDto.getJobName() != null) {
            Jobs job = jobRepo.findByJobTitle(departmentJobDto.getJobName())
                    .orElseThrow(() -> new ResourceNotFoundException("Job does not exist"));
            employee.setJobs(job);
        }

        employeeRepo.save(employee);

        DepartmentJobDto updatedDto = new DepartmentJobDto();
        updatedDto.setDepartmentName(employee.getDepartment().getDepartmentName());
        updatedDto.setJobName(employee.getJobs().getJobTitle());

        return updatedDto;
    }

    @Override
    public EmployeeDto updateEmployeeInfo(EmployeeDto employeeDto,Long id) {

        Employee employee = employeeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User does not exist"));

        employee.setFirstName(employeeDto.getFirstName()!=null?employeeDto.getFirstName(): employee.getFirstName());
        employee.setPhoneNumber(employeeDto.getPhoneNumber()!=null?employeeDto.getPhoneNumber(): employee.getPhoneNumber());
        employee.setFirstName(employeeDto.getLastName()!=null?employeeDto.getLastName(): employee.getLastName());

        Employee savedEmployee = employeeRepo.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }
}
