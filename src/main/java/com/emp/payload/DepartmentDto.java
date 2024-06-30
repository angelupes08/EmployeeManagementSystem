package com.emp.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DepartmentDto {

    private Long departmentId;

    private String departmentName;
}
