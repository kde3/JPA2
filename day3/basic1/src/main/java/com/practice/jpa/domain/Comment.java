package com.practice.jpa.domain;

import com.practice.jpa.type.CommentStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TBL_COMMENT")
public class Comment {
    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String writer;
    @Enumerated(value = EnumType.STRING)
    private CommentStatus commentStatus;
}
