package com.courcework.delivery.controller;


import com.courcework.delivery.exception.DishNotFoundException;
import com.courcework.delivery.exception.RestaurantNotFoundException;
import com.courcework.delivery.model.Dish;
import com.courcework.delivery.model.Restaurant;
import com.courcework.delivery.service.DishService;
import com.courcework.delivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class DishController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @PostMapping("/restaurant/{restaurantId}/dish")
    public Dish addDishToRestaurant(@RequestBody Dish newDish, @PathVariable Long restaurantId) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        newDish.setRestaurant(restaurant);
        return dishService.createDish(newDish);
    }

    @GetMapping("/dishes")
    public List<Dish> getAllDishes() { return dishService.getAllDishes(); }

    @PutMapping("/dish/{id}")
    public Dish updateDish(@RequestBody Dish newDish, @PathVariable Long id) throws DishNotFoundException {
        return dishService.updateDish(newDish,id);
    }

    @DeleteMapping("/dish/{id}")
    public String deleteDish(@PathVariable Long id) throws DishNotFoundException {
        return dishService.deleteDish(id);
    }
}
