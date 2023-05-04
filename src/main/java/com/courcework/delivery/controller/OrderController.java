package com.courcework.delivery.controller;

import com.courcework.delivery.exception.OrderNotFoundException;
import com.courcework.delivery.exception.RestaurantNotFoundException;
import com.courcework.delivery.model.Order;
import com.courcework.delivery.model.Restaurant;
import com.courcework.delivery.model.User;
import com.courcework.delivery.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }


    @PostMapping(value = "/order/{userId}")
    public Order addOrder(@RequestBody Order newOrder,
                        @PathVariable Long userId) throws RestaurantNotFoundException {
        User user = userService.getUserById(userId);
        //Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        newOrder.setUser(user);
       // newOrder.setRestaurant(restaurant);
        return orderService.addOrder(newOrder);
    }



    @DeleteMapping("/order/{id}")
    public String deleteOrder(@PathVariable Long id) throws OrderNotFoundException {
        return orderService.deleteOrder(id);
    }

    @GetMapping("/orders/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @PutMapping("/order/{id}")
    public Order updateOrder(@RequestBody Order newOrder, @PathVariable Long id) throws OrderNotFoundException {
        return orderService.update(newOrder, id);
    }
}
