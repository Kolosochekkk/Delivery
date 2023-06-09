package com.courcework.delivery.repository;

import com.courcework.delivery.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {
    List<Dish> findByRestaurantId(Long restaurantId);
}
