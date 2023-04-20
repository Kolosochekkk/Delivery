package com.courcework.delivery.service;

import com.courcework.delivery.exception.DishNotFoundException;
import com.courcework.delivery.exception.RestaurantNotFoundException;
import com.courcework.delivery.model.Dish;
import com.courcework.delivery.model.Restaurant;
import com.courcework.delivery.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public List<Dish> getAllDishes(){
        return dishRepository.findAll();
    }

    public Dish getDishById(Long id) throws DishNotFoundException {
        return dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException(id));
    }

    public Dish updateDish(Dish newDish, Long id) throws DishNotFoundException {
        return dishRepository.findById(id)
                .map(user -> {
                    user.setName(newDish.getName());
                    user.setPrice(newDish.getPrice());
                    return dishRepository.save(user);
                }).orElseThrow(() -> new DishNotFoundException(id));
    }

    public String deleteDish(Long id) throws DishNotFoundException {
        if (!dishRepository.existsById(id)) {
            throw new DishNotFoundException(id);
        }
        dishRepository.deleteById(id);
        return "Блюдо с id " + id + " было успешно удалено";
    }
}
