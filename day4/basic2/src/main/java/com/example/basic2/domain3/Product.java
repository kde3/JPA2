package com.example.basic2.domain3;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;
    private String name;
    private int price;

    @ManyToOne @JoinColumn(name = "member_id")
    private Member member;
}
