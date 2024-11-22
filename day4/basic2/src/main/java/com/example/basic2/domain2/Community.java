package com.example.basic2.domain2;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_community")
@Data
public class Community {
    @Id @GeneratedValue
    @Column(name = "community_id")
    private Long id;
    private String title;
    private String content;

    @OneToMany
    @JoinColumn(name = "community_id")
    private List<Comment> comment = new ArrayList<>(); // 명시적 초기화. 빈 리스트로 초기화하기 위함.
}
