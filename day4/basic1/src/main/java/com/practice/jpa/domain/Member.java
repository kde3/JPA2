package com.practice.jpa.domain;

import com.practice.jpa.type.MemberGender;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private int age;
    @Enumerated(EnumType.STRING)
    private MemberGender memberGender;
}
