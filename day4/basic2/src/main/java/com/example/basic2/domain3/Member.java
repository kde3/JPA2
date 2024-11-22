package com.example.basic2.domain3;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_member")
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String loginId;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Product> products = new ArrayList<>();
}
