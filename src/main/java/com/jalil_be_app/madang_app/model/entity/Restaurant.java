package com.jalil_be_app.madang_app.model.entity;

import com.jalil_be_app.madang_app.model.enums.RestaurantCategory;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private RestaurantCategory category;

    private Float rating;
}
