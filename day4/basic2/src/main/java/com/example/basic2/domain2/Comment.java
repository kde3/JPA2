package com.example.basic2.domain2;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_comment")
@Data
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String content;
}
