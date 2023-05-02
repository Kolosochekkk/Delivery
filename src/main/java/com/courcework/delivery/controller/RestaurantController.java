package com.courcework.delivery.controller;

import com.courcework.delivery.exception.RestaurantNotFoundException;
import com.courcework.delivery.model.Restaurant;
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
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping(value = "/restaurant", consumes = { "multipart/form-data" })
    public Restaurant newRestaurant(@RequestPart("photo") MultipartFile file,
                                    @RequestPart("restaurant") Restaurant restaurant) throws IOException {
        return restaurantService.newRestaurant(restaurant, file);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurant/{id}")
    Restaurant getRestaurantById(@PathVariable Long id) throws RestaurantNotFoundException {
        return restaurantService.getRestaurantById(id);}


    @PutMapping(value = "/restaurant/{id}", consumes = { "multipart/form-data" })
    public Restaurant updateRestaurant(@PathVariable Long id,
                           @RequestPart(name = "photo", required = false) MultipartFile file,
                           @RequestPart("restaurant") Restaurant restaurant) throws IOException, RestaurantNotFoundException {

        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            restaurant.setPhoto(fileName);
            System.out.println("фото");
            String uploadDir = "restaurant-photo/" + id;
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }

        Restaurant updatedRestaurant = restaurantService.update(restaurant, id);

        return updatedRestaurant;
    }

    @DeleteMapping("/restaurant/{id}")
    public String deleteRestaurant(@PathVariable Long id) throws RestaurantNotFoundException {
        return restaurantService.deleteRestaurant(id);
    }

}
