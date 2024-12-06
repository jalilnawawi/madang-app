package com.jalil_be_app.madang_app.repository;

import com.jalil_be_app.madang_app.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "select * from product p\n" +
            "where lower(p.name) LIKE lower(concat('%', :searchText, '%'))", nativeQuery = true)
    List<Product> searchProduct(@Param("searchText") String searchText);

    List<Product> getProductByRestaurantId(UUID restaurantid);
}
