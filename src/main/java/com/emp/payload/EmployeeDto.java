package com.emp.payload;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDto {


    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String phoneNumber;

    private String email;

    private String password;

}
