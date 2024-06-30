package com.emp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;

    @Column
    private double amount;




}
