package com.emp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column
    private String jobTitle;

    @Column
    private Double minSalary;

    @Column
    private Double maxSalary;

    @OneToMany(mappedBy = "jobs")
    private List<Employee> employees;

    @OneToMany(mappedBy = "jobs")
    private List<JobHistory> jobHistory;


}
