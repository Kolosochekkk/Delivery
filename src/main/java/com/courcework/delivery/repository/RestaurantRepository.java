package com.courcework.delivery.repository;

import com.courcework.delivery.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface RestaurantRepository  extends JpaRepository<Restaurant,Long> {


}
