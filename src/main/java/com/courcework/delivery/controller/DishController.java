package com.courcework.delivery.controller;

import com.courcework.delivery.exception.DishNotFoundException;
import com.courcework.delivery.exception.RestaurantNotFoundException;
import com.courcework.delivery.model.Dish;
import com.courcework.delivery.model.Restaurant;
import com.courcework.delivery.service.DishService;
import com.courcework.delivery.service.FileUploadUtil;
import com.courcework.delivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class DishController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @PostMapping(value = "/restaurant/{restaurantId}", consumes = { "multipart/form-data" })
    public Dish addDishToRestaurant(@RequestParam("photo") MultipartFile photo,
                                    @RequestPart("dish") Dish newDish,
                                    @PathVariable Long restaurantId) throws IOException, RestaurantNotFoundException {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        newDish.setRestaurant(restaurant);
        return dishService.createDish(newDish, photo);
    }

    @GetMapping("/dishes")
    public List<Dish> getAllDishes(@RequestParam(required = false) Long restaurantId) {
        if (restaurantId != null) {
            return dishService.getDishesByRestaurantId(restaurantId);
        } else {
            return dishService.getAllDishes();
        }
    }

    @GetMapping("/dish/{id}")
    Dish getDishById(@PathVariable Long id) throws DishNotFoundException {
        return dishService.getDishById(id);}

    @PutMapping(value = "/dish/{dishId}", consumes = { "multipart/form-data" })
    public Dish updateDish(@PathVariable Long dishId,
                           @RequestParam("photo") MultipartFile photo,
                           @RequestPart("dish") Dish updatedDish) throws IOException, DishNotFoundException {
        Dish dish = dishService.updateDish(dishId, updatedDish, photo);
        return dish;
    }


    @DeleteMapping("/dish/{id}")
    public String deleteDish(@PathVariable Long id) throws DishNotFoundException {
        return dishService.deleteDish(id);
    }
}
