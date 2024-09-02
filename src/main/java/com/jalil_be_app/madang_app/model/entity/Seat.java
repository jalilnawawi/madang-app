package com.jalil_be_app.madang_app.model.entity;

import com.jalil_be_app.madang_app.model.enums.SeatCategory;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "seat")
public class Seat extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "count_tables")
    private int countTables;

    @Column(name = "count_chairs")
    private int countChairs;

    @Column(name = "count_flowers")
    private int countFlowers;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    private SeatCategory category;
}
