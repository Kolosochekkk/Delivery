package com.courcework.delivery.repository;


import com.courcework.delivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface OrderRepository  extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);

}
