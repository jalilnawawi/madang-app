package com.jalil_be_app.madang_app.repository;

import com.jalil_be_app.madang_app.model.entity.Product;
import com.jalil_be_app.madang_app.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    Optional<Restaurant> findByUserId(UUID userId);
    List<Restaurant> getRestaurantByUserId(UUID userId);

    @Query(value = "select * from restaurant r\n" +
            "where lower(r.name) LIKE lower(concat('%', :searchText, '%'))", nativeQuery = true)
    List<Restaurant> searchRestaurant(@Param("searchText") String searchText);
}
