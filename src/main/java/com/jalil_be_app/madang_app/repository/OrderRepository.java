package com.jalil_be_app.madang_app.repository;

import com.jalil_be_app.madang_app.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findFirstByUserIdAndCompletedFalse(UUID userId);
}
