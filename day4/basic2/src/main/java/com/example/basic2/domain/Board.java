package com.example.basic2.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TBL_BOARD")
@Data
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;
    private String title;
    private String content;


    @ManyToOne                      // Board 입장에서는 다대일
    @JoinColumn(name = "USER_ID")   // 외래키로 사용할 컬럼명 설정(디폴트는 [클래스명_id명])
    private User user;
}
