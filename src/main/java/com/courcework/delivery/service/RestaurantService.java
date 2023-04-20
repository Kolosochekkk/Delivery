package com.courcework.delivery.service;

import com.courcework.delivery.exception.RestaurantNotFoundException;
import com.courcework.delivery.model.Restaurant;
import com.courcework.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(Long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public Restaurant updateRestaurant(Restaurant newRestaurant, Long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .map(user -> {
                    user.setName(newRestaurant.getName());
                    user.setPhone(newRestaurant.getPhone());
                    user.setAddress(newRestaurant.getAddress());
                    user.setStars(newRestaurant.getStars());
                    return restaurantRepository.save(user);
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
