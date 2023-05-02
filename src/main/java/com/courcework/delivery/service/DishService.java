package com.courcework.delivery.service;

import com.courcework.delivery.exception.DishNotFoundException;
import com.courcework.delivery.model.Dish;
import com.courcework.delivery.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DishService {

    @Autowired
    private DishRepository dishRepository;


    public Dish createDish(Dish dish, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        dish.setPhoto(fileName);

        Dish savedDish = dishRepository.save(dish);

        String uploadDir = "dish-photo/" + savedDish.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);

        return savedDish;
    }


    public Dish save(Dish object) {
        return dishRepository.save(object);
    }


    public List<Dish> getAllDishes(){
        return dishRepository.findAll();
    }

    public List<Dish> getDishesByRestaurantId(Long restaurantId) {
        return dishRepository.findByRestaurantId(restaurantId);
    }


    public Dish getDishById(Long id) throws DishNotFoundException {
        return dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException(id));
    }


    public Dish updateDish(Long dishId, Dish updatedDish, MultipartFile file) throws IOException, DishNotFoundException {
        Dish existingDish = getDishById(dishId);
        existingDish.setName(updatedDish.getName());
        existingDish.setPrice(updatedDish.getPrice());
        existingDish.setRestaurant(updatedDish.getRestaurant());

        // Обновляем фото, если оно было изменено
        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            existingDish.setPhoto(fileName);

            String uploadDir = "dish-photo/" + existingDish.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }

        return dishRepository.save(existingDish);
    }


    public String deleteDish(Long id) throws DishNotFoundException {
        if (!dishRepository.existsById(id)) {
            throw new DishNotFoundException(id);
        }
        dishRepository.deleteById(id);
        return "Блюдо с id " + id + " было успешно удалено";
    }
}
