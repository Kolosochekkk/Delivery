package com.courcework.delivery.controller;

import com.courcework.delivery.exception.RestaurantNotFoundException;
import com.courcework.delivery.model.Restaurant;
import com.courcework.delivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurant")
    public Restaurant newRestaurant(@RequestBody Restaurant newRestaurant){return restaurantService.createRestaurant(newRestaurant);}

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PutMapping("/restaurant/{id}")
    public Restaurant updateRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable Long id) throws RestaurantNotFoundException {
        return restaurantService.updateRestaurant(newRestaurant,id);
    }

    @DeleteMapping("/restaurant/{id}")
    public String deleteRestaurant(@PathVariable Long id) throws RestaurantNotFoundException {
        return restaurantService.deleteRestaurant(id);
    }
}
