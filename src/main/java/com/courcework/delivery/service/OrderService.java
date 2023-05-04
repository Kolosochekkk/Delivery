package com.courcework.delivery.service;


import com.courcework.delivery.exception.OrderNotFoundException;
import com.courcework.delivery.model.Order;
import com.courcework.delivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public String deleteOrder(Long id) throws OrderNotFoundException {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        orderRepository.deleteById(id);
        return "Корзина с id " + id + " была успешно удалена";
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order update(Order newOrder, Long id) throws OrderNotFoundException {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setTotal(newOrder.getTotal());
                    order.setStatus(newOrder.getStatus());
                    order.setUser(newOrder.getUser());
                    //order.setRestaurant(newOrder.getRestaurant());

                    return orderRepository.save(order);

                }).orElseThrow(() -> new OrderNotFoundException(id));
    }
}
