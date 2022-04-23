package com.example.modul6_exam.entity;

import com.example.modul6_exam.entity.enums.Category;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "code", unique = true, updatable = false)
    private UUID code = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private double price;
    @Enumerated(EnumType.STRING)
    private Category category;
}