package com.courcework.delivery.service;

import com.courcework.delivery.exception.RestaurantNotFoundException;
import com.courcework.delivery.model.Restaurant;
import com.courcework.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;


import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    public Restaurant newRestaurant(Restaurant restaurant, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        restaurant.setPhoto(fileName);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        String uploadDir = "restaurant-photo/" + savedRestaurant.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);

        return savedRestaurant;
    }

    public Restaurant save(Restaurant object) {
        return restaurantRepository.save(object);
    }

    public Restaurant getRestaurantById(Long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public Restaurant update(Restaurant newRestaurant, Long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(newRestaurant.getName());
                    restaurant.setPhone(newRestaurant.getPhone());
                    restaurant.setAddress(newRestaurant.getAddress());
                    restaurant.setStars(newRestaurant.getStars());
                    restaurant.setPhoto(newRestaurant.getPhoto());

                    return restaurantRepository.save(restaurant);

                }).orElseThrow(() -> new RestaurantNotFoundException(id));
    }



    public String deleteRestaurant(Long id) throws RestaurantNotFoundException {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantNotFoundException(id);
        }
        restaurantRepository.deleteById(id);
        return "Ресторан с id " + id + " был успешно удален";
    }

    
}
