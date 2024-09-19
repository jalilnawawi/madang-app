package com.jalil_be_app.madang_app.repository;

import com.jalil_be_app.madang_app.model.entity.Seat;
import com.jalil_be_app.madang_app.model.enums.SeatCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {
    Seat findByCategory(SeatCategory category);
}
