package com.jalil_be_app.madang_app.model.entity;

import com.jalil_be_app.madang_app.model.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Image.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id")
    private Image image;

    private Float rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
