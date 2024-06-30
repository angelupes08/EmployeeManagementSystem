package com.emp.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class JobDto {

    private Long jobId;

    private String jobTitle;

    private Double minSalary;

    private Double maxSalary;

}
