package com.example.basic2.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TBL_USER")
@Data
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private int age;
}
